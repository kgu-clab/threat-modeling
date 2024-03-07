package kr.re.dslab.threatmodeling.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.re.dslab.threatmodeling.service.AttackService;
import kr.re.dslab.threatmodeling.type.dto.AttackRelatedResponseDto;
import kr.re.dslab.threatmodeling.type.dto.ResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/attacks")
@RequiredArgsConstructor
@Tag(name = "Attack", description = "공격")
public class AttackController {

        private final AttackService attackService;

        @Operation(summary = "Attack 관련 정보(Control, Mitigation, CVE) 조회", description = "ROLE_ANONYMOUS 이상의 권한이 필요함")
        @GetMapping("/{attackId}")
        public ResponseModel getAttack(
                @PathVariable(name = "attackId") String attackId
        ) {
            AttackRelatedResponseDto attackRelatedResponseDto = attackService.getAttackRelatedInfo(attackId);
            ResponseModel responseModel = ResponseModel.builder().build();
            responseModel.addData(attackRelatedResponseDto);
            return responseModel;
        }

}
