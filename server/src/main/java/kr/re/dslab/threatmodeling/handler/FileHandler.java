package kr.re.dslab.threatmodeling.handler;

import com.google.common.base.Strings;
import kr.re.dslab.threatmodeling.config.ResourceProperties;
import kr.re.dslab.threatmodeling.exception.FileUploadFailException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileHandler {

    private final ResourceProperties resourceProperties;

    public String saveFile(MultipartFile multipartFile, String path) throws FileUploadFailException {
        String originalFilename = multipartFile.getOriginalFilename();
        if (!validateFilename(originalFilename)) {
            throw new FileUploadFailException("허용되지 않은 파일명 : " + originalFilename);
        }
        String extension = FilenameUtils.getExtension(originalFilename);
        if (!validateExtension(extension)) {
            throw new FileUploadFailException("허용되지 않은 확장자 : " + originalFilename);
        }
        String newFilename = System.nanoTime() + "_" + UUID.randomUUID() + "." + extension;
        String destPath = resourceProperties.getPath() + File.separator + path + File.separator + newFilename;
        log.info("destPath : {}", destPath);
        File file = new File(destPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            String os = System.getProperty("os.name").toLowerCase();
            multipartFile.transferTo(file);
            if (os.contains("win")) {
                file.setReadable(true);
                file.setWritable(false);
                file.setExecutable(false);
            } else {
                Runtime.getRuntime().exec("chmod 400 " + destPath);
            }
        } catch (IOException e) {
            throw new FileUploadFailException("파일 저장 실패", e);
        }
        log.info("file location : {}", path + "/" + newFilename);
        return path + "/" + newFilename;
    }

    private boolean validateExtension(String extension) {
        for (String allowExtension : List.of(resourceProperties.getAllowExtension())) {
            if (allowExtension.equals(extension)) {
                return true;
            }
        }
        return false;
    }

    private boolean validateFilename(String fileName) {
        return !Strings.isNullOrEmpty(fileName);
    }

}