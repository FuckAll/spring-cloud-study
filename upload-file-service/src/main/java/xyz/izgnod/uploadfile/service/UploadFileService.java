package xyz.izgnod.uploadfile.service;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.izgnod.uploadfile.api.UploadFileApi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author izgnod
 */
@RestController
public class UploadFileService implements UploadFileApi {
    @Override
    public void uploadFile(MultipartFile file) {
        try {
            System.out.println("file.getOriginalFilename() = " + file.getOriginalFilename());
            byte[] bytes = file.getBytes();
            String originalFilename = file.getOriginalFilename();
            Files.write(Paths.get("./" + originalFilename),bytes );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
