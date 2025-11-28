package ltwebct4.controller.admin;

import java.io.File;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;
import ltwebct4.entity.Category;
import ltwebct4.model.CategoryModel;
import ltwebct4.service.CategoryService;
import ltwebct4.utils.Constant;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
    
    @Autowired
    CategoryService categoryService;

    @GetMapping("add")
    public String add(ModelMap model) {
        CategoryModel cateModel = new CategoryModel();
        cateModel.setIsEdit(false);
        model.addAttribute("category", cateModel);
        return "admin/categories/addOrEdit";
    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model, 
            @Valid @ModelAttribute("category") CategoryModel cateModel, 
            BindingResult result,
            @RequestParam("imageFile") MultipartFile file) { 
        
        if(result.hasErrors()) {
            return new ModelAndView("admin/categories/addOrEdit");
        }
        
        Category entity = new Category();
        // Copy dữ liệu từ Model sang Entity (name, code, status, id...)
        BeanUtils.copyProperties(cateModel, entity);
        
        // Xử lý ID
        if(cateModel.getIsEdit() && cateModel.getCategoryId() != null) {
            entity.setCategoryId(cateModel.getCategoryId());
        }

        // Xử lý Upload Ảnh
        if (!file.isEmpty()) {
            try {
                // Tạo thư mục nếu chưa có
                File uploadDir = new File(Constant.UPLOAD_DIRECTORY);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                // Tạo tên file ngẫu nhiên để tránh trùng
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                
                // Lưu file vào C:/upload
                Path filePath = Paths.get(Constant.UPLOAD_DIRECTORY, fileName);
                Files.write(filePath, file.getBytes());
                
                // Gán tên ảnh mới cho entity
                entity.setImages(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Nếu không chọn ảnh mới -> Giữ lại ảnh cũ (khi đang Edit)
            if (cateModel.getIsEdit()) {
                Category oldEntity = categoryService.findById(cateModel.getCategoryId()).orElse(null);
                if (oldEntity != null) {
                    entity.setImages(oldEntity.getImages());
                }
            }
        }

        // Lưu xuống DB
        categoryService.save(entity);
        
        String message = cateModel.getIsEdit() ? "Category is Updated!" : "Category is Saved!";
        model.addAttribute("message", message);
        
        return new ModelAndView("redirect:/admin/categories");
    }

    @RequestMapping("")
    public String list(ModelMap model) {
        List<Category> list = categoryService.findAll();
        model.addAttribute("categories", list);
        return "admin/categories/list";
    }

    @GetMapping("edit/{categoryId}")
    public ModelAndView edit(ModelMap model, @PathVariable("categoryId") int categoryId) {
        Optional<Category> optCategory = categoryService.findById(categoryId);
        CategoryModel cateModel = new CategoryModel();
        
        if(optCategory.isPresent()) {
            Category entity = optCategory.get();
            // Copy ngược từ Entity lên Model để hiển thị trong form
            BeanUtils.copyProperties(entity, cateModel);
            cateModel.setIsEdit(true);
            
            model.addAttribute("category", cateModel);
            return new ModelAndView("admin/categories/addOrEdit", model);
        }
        
        model.addAttribute("message", "Category is not existed!!!!");
        return new ModelAndView("forward:/admin/categories", model);
    }

    @GetMapping("delete/{categoryId}")
    public ModelAndView delete(ModelMap model, @PathVariable("categoryId") int categoryId) {
        try {

            categoryService.deleteById(categoryId);
            model.addAttribute("message", "Category deleted successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Cannot delete category (may contain videos)!");
        }
        return new ModelAndView("redirect:/admin/categories");
    }

    @RequestMapping("search")
    public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {
        List<Category> list = null;
        if (StringUtils.hasText(name)) {
            list = categoryService.findByNameContaining(name);
        } else {
            list = categoryService.findAll();
        }
        model.addAttribute("categories", list);
        return "admin/categories/list"; 
    }
}