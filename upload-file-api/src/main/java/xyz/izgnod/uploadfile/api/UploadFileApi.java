package xyz.izgnod.uploadfile.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author izgnod
 */
public interface UploadFileApi {

    /**
     * @param file 文件名
     */
    @PostMapping("/upload")
    void uploadFile(@RequestParam("file") MultipartFile file);
}
