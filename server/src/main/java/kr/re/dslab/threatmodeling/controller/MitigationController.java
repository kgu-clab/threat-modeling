package kr.re.dslab.threatmodeling.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.re.dslab.threatmodeling.service.MitigationService;
import kr.re.dslab.threatmodeling.type.dto.response.MitigationResponseDto;
import kr.re.dslab.threatmodeling.type.dto.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mitigations")
@RequiredArgsConstructor
@Tag(name = "Mitigation", description = "대응")
public class MitigationController {

    private final MitigationService mitigationService;

    @Operation(summary = "Mitigation 관련 정보(Defend) 조회", description = "ROLE_ANONYMOUS 이상의 권한이 필요함")
    @GetMapping("/{mitigationId}")
    public ResponseModel getMitigation(
            @PathVariable(name = "mitigationId") String mitigationId
    ) {
        MitigationResponseDto mitigationResponseDto = mitigationService.getMitigationInfo(mitigationId);
        ResponseModel responseModel = ResponseModel.builder().build();
        responseModel.addData(mitigationResponseDto);
        return responseModel;
    }

}
