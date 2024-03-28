package kr.re.dslab.threatmodeling.auth.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import kr.re.dslab.threatmodeling.auth.application.WhitelistService;
import kr.re.dslab.threatmodeling.util.HttpReqResUtil;
import kr.re.dslab.threatmodeling.util.ResponseUtil;
import kr.re.dslab.threatmodeling.util.SwaggerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Slf4j
public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter {

    private final WhitelistService whitelistService;

    public CustomBasicAuthenticationFilter(
            AuthenticationManager authenticationManager,
            WhitelistService whitelistService
    ) {
        super(authenticationManager);
        this.whitelistService = whitelistService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Swagger 요청이 아닌 경우 필터링하지 않음
        String path = request.getRequestURI();
        if (!SwaggerUtil.isSwaggerRequest(path)) {
            chain.doFilter(request, response);
            return;
        }
        // IP 접근 제어 및 사용자 인증
        if (!verifyIpAddressAccess(response)) {
            return;
        }
        // 사용자 인증
        if (!authenticateUserCredentials(request, response)) {
            return;
        }
        super.doFilterInternal(request, response, chain);
    }

    private boolean authenticateUserCredentials(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Basic 인증 헤더가 없는 경우 401 Unauthorized 응답 반환
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Basic ")) {
            response.setHeader("WWW-Authenticate", "Basic realm=\"Please enter your username and password\"");
            ResponseUtil.sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // Basic 인증 헤더의 인증 정보를 추출하여 사용자 인증
        String[] credentials = decodeCredentials(authorizationHeader);
        if (credentials.length < 2) {
            ResponseUtil.sendErrorResponse(response, HttpServletResponse.SC_BAD_REQUEST);
            return false;
        }
        String username = credentials[0];
        String password = credentials[1];
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = getAuthenticationManager().authenticate(authRequest);
        // 사용자 인증 실패 시 401 Unauthorized 응답 반환
        if (authentication == null) {
            ResponseUtil.sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // 사용자 인증 성공 시 SecurityContextHolder에 인증 정보 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }

    private boolean verifyIpAddressAccess(HttpServletResponse response) throws IOException {
        // 클라이언트 IP 주소를 추출하여 IP 접근 제어
        String clientIpAddress = HttpReqResUtil.getClientIpAddressIfServletRequestExist();
        List<String> whitelistIps = whitelistService.loadWhitelistIps();
        // 클라이언트 IP 주소가 허용 목록에 없는 경우 401 Unauthorized 응답 반환
        if (!(whitelistIps.contains(clientIpAddress) || whitelistIps.contains("*"))
        ) {
            log.info("[{}] : 정책에 의해 차단된 IP입니다.", clientIpAddress);
            ResponseUtil.sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    @NotNull
    private static String[] decodeCredentials(String authorizationHeader) {
        // Basic 인증 헤더의 인증 정보를 디코딩하여 사용자 이름과 비밀번호 추출
        String base64Credentials = authorizationHeader.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        return credentials.split(":", 2);
    }

}