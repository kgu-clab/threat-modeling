package kr.re.dslab.threatmodeling.config;

public class SecurityConstants {

    public static final String[] PERMIT_ALL = {
            "/login/**",
            "/static/**",
            "/configuration/ui",
            "/configuration/security",
            "/webjars/**",
            "/error",
            "/"
    };

    public static final String[] PERMIT_ALL_API_ENDPOINTS_GET = {
            "/attacks/{attackId}",
            "/mitigations/{mitigationId}"
    };

    public static final String[] PERMIT_ALL_API_ENDPOINTS_POST = {
            "/files/jsons"
    };

}
