package ltwebct4.controller.admin;

import java.io.File;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import ltwebct4.entity.Category;
import ltwebct4.entity.Video;
import ltwebct4.service.CategoryService;
import ltwebct4.service.VideoService;
import ltwebct4.utils.Constant; 

@Controller
@RequestMapping("admin/videos")
public class VideoController {

    @Autowired VideoService videoService;
    @Autowired CategoryService categoryService;

    @RequestMapping("")
    public String list(Model model, @RequestParam(name = "title", required = false) String title) {
        List<Video> list;
        if (title != null && !title.isEmpty()) {
            list = videoService.findByTitleContaining(title);
        } else {
            list = videoService.findAll();
        }
        model.addAttribute("videos", list);
        model.addAttribute("title", title);
        return "admin/videos/list";
    }

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("video", new Video());
        model.addAttribute("categories", categoryService.findAll());
        return "admin/videos/addOrEdit";
    }

    @GetMapping("edit/{videoId}")
    public String edit(Model model, @PathVariable("videoId") String videoId) {
        Video video = videoService.findById(videoId);
        if (video != null) {
            model.addAttribute("video", video);
            model.addAttribute("categories", categoryService.findAll());
            return "admin/videos/addOrEdit";
        }
        return "redirect:/admin/videos";
    }

    @PostMapping("saveOrUpdate")
    public String saveOrUpdate(Model model, 
                               @ModelAttribute("video") Video video,
                               @RequestParam("categoryId") int categoryId,
                               @RequestParam("imageFile") MultipartFile file) {
        
        Category category = new Category();
        category.setCategoryId(categoryId);
        video.setCategory(category);

        if (!file.isEmpty()) {
            try {
                // Tạo thư mục nếu chưa tồn tại
                File uploadDir = new File(Constant.UPLOAD_DIRECTORY);
                if (!uploadDir.exists()) uploadDir.mkdirs();

                // Tạo tên file ngẫu nhiên
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                
                // Lưu file vào đường dẫn trong Constant
                Path filePath = Paths.get(Constant.UPLOAD_DIRECTORY, fileName);
                Files.write(filePath, file.getBytes());
                
                video.setPoster(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (video.getVideoId() != null) {
                Video oldVideo = videoService.findById(video.getVideoId());
                if (oldVideo != null) video.setPoster(oldVideo.getPoster());
            }
        }

        videoService.save(video);
        return "redirect:/admin/videos";
    }
    
    @GetMapping("delete/{videoId}")
    public String delete(@PathVariable("videoId") String videoId) {
        videoService.deleteById(videoId);
        return "redirect:/admin/videos";
    }
}