package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.config.ResourceProperties;
import kr.re.dslab.threatmodeling.exception.FileUploadFailException;
import kr.re.dslab.threatmodeling.handler.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final FileHandler fileHandler;

    private final ResourceProperties resourceProperties;

    public List<String> saveFiles(List<MultipartFile> multipartFiles, String path) throws FileUploadFailException {
        List<String> urls = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String url = saveFile(multipartFile, path);
            urls.add(url);
        }
        return urls;
    }

    // 파일을 저장하고 저장된 파일의 URL을 반환
    public String saveFile(MultipartFile multipartFile, String path) throws FileUploadFailException {
        String realFilename = fileHandler.saveFile(multipartFile, path);
        return resourceProperties.getUrl() + "/" + realFilename;
    }

}
