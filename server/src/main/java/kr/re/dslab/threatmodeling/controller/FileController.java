package kr.re.dslab.threatmodeling.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.re.dslab.threatmodeling.exception.FileUploadFailException;
import kr.re.dslab.threatmodeling.service.FileService;
import kr.re.dslab.threatmodeling.type.dto.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@Tag(name = "File Upload", description = "파일 업로드")
public class FileController {

    private final FileService fileService;

    @Operation(summary = "JSON 업로드", description = "ROLE_ANONYMOUS 이상의 권한이 필요함")
    @PostMapping(value = "/jsons", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseModel jsonUpload(
            @RequestParam(name = "multipartFile") List<MultipartFile> multipartFiles
    ) throws FileUploadFailException {
        List<String> fileUrls = fileService.saveFiles(multipartFiles, "jsons");
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(fileUrls);
        return responseModel;
    }

}