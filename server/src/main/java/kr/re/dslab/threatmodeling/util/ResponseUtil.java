package kr.re.dslab.threatmodeling.util;

import jakarta.servlet.http.HttpServletResponse;
import kr.re.dslab.threatmodeling.type.dto.ResponseModel;

import java.io.IOException;

public class ResponseUtil {

    public static void sendErrorResponse(HttpServletResponse response, int status) throws IOException {
        ResponseModel responseModel = ResponseModel.builder()
                .success(false)
                .build();
        response.getWriter().write(responseModel.toJson());
        response.setContentType("application/json");
        response.setStatus(status);
    }

}
