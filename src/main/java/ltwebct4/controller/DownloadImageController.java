package ltwebct4.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;
import ltwebct4.utils.Constant;

@Controller
public class DownloadImageController {

    @GetMapping("/image")
    @ResponseBody
    public void index(@RequestParam("fname") String fileName, HttpServletResponse response) throws IOException {
        if (fileName == null || fileName.isEmpty()) {
            return;
        }

        File file = new File(Constant.UPLOAD_DIRECTORY + "/" + fileName);

        if (file.exists()) {
            IOUtils.copy(new FileInputStream(file), response.getOutputStream());
        }
    }
}