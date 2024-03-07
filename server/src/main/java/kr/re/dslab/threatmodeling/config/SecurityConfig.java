package kr.re.dslab.threatmodeling.config;

import kr.re.dslab.threatmodeling.auth.application.WhitelistService;
import kr.re.dslab.threatmodeling.auth.filter.CustomBasicAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final WhitelistService whitelistService;

    private final CorsConfigurationSource corsConfigurationSource;

    private final AuthenticationConfig authenticationConfig;

    private final OpenApiAccountProperties openApiAccountProperties;

    private final OpenApiPatternsProperties openApiPatternsProperties;

    private final ResourceProperties resourceProperties;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = authenticationConfig.authenticationManager();
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .cors(cors ->
                        cors.configurationSource(corsConfigurationSource)
                )
                .authorizeRequests(this::configureRequests)
                .authenticationProvider(authenticationConfig.authenticationProvider())
                .addFilterBefore(
                        new CustomBasicAuthenticationFilter(authenticationManager, whitelistService),
                        UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

    private ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry configureRequests(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {
        return authorizeRequests
                .requestMatchers(openApiPatternsProperties.getPatterns()).hasRole(openApiAccountProperties.getRole())
                .requestMatchers(SecurityConstants.PERMIT_ALL).permitAll()
                .requestMatchers(resourceProperties.getUrl()).permitAll()
                .requestMatchers(HttpMethod.GET, SecurityConstants.PERMIT_ALL_API_ENDPOINTS_GET).permitAll()
                .requestMatchers(HttpMethod.POST, SecurityConstants.PERMIT_ALL_API_ENDPOINTS_POST).permitAll()
                .anyRequest().authenticated();
    }

}