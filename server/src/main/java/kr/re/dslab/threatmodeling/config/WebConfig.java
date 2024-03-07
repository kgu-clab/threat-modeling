package kr.re.dslab.threatmodeling.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.re.dslab.threatmodeling.handler.ApiLoggingInterceptor;
import kr.re.dslab.threatmodeling.util.HtmlCharacterEscapes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebConfig implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    private final ApiLoggingInterceptor apiLoggingInterceptor;

    private final ResourceProperties resourceProperties;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("Resource File Mapped : {} -> {}", resourceProperties.getUrl(), resourceProperties.getPath());
        registry
                .addResourceHandler(resourceProperties.getUrl() + "/**")
                .addResourceLocations("file://" + resourceProperties.getPath() + "/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource resource = location.createRelative(resourcePath);
                        if (resource.exists() && resource.isReadable()) {
                            return resource;
                        }
                        throw new FileNotFoundException("Resource not found: " + resourcePath);
                    }
                });
    }

    @Bean
    public MappingJackson2HttpMessageConverter jsonEscapeConverter() {
        ObjectMapper copy = objectMapper.copy();
        copy.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());
        return new MappingJackson2HttpMessageConverter(copy);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLoggingInterceptor);
    }

}
