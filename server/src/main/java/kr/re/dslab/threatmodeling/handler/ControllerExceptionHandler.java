package kr.re.dslab.threatmodeling.handler;

import com.google.gson.stream.MalformedJsonException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.re.dslab.threatmodeling.exception.FileUploadFailException;
import kr.re.dslab.threatmodeling.exception.NotFoundException;
import kr.re.dslab.threatmodeling.type.dto.ResponseModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@RestControllerAdvice(basePackages = "kr.re.dslab.threatmodeling")
@RequiredArgsConstructor
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler({
            StringIndexOutOfBoundsException.class,
            MissingServletRequestParameterException.class,
            MalformedJsonException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class,
            IllegalAccessException.class,
    })
    public ResponseModel badRequestException(HttpServletResponse response, Exception e){
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return makeExceptionResponseModel(false, null);
    }

    @ExceptionHandler({
            AccessDeniedException.class,
            BadCredentialsException.class,
    })
    public ResponseModel unAuthorizeException(HttpServletResponse response, Exception e){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return makeExceptionResponseModel(false, null);
    }

    @ExceptionHandler({
            NullPointerException.class,
            NotFoundException.class,
            NoSuchElementException.class,
            FileNotFoundException.class,
    })
    public ResponseModel notFoundException(HttpServletResponse response, Exception e){
        response.setStatus(HttpServletResponse.SC_OK);
        return makeExceptionResponseModel(true, new ArrayList<>());
    }

    @ExceptionHandler({
            IllegalStateException.class,
            FileUploadFailException.class,
            DataIntegrityViolationException.class,
            IOException.class,
            TransactionSystemException.class,
            SecurityException.class,
            Exception.class
    })
    public ResponseModel serverException(HttpServletRequest request, HttpServletResponse response, Exception e){
        log.warn(e.getMessage());
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return makeExceptionResponseModel(false, null);
    }

    private ResponseModel makeExceptionResponseModel(boolean success, Object message) {
        return ResponseModel.builder()
                .success(success)
                .data(message)
                .build();
    }

}