package kr.re.dslab.threatmodeling.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "security.resource.file")
public class ResourceProperties {

    private String url;

    private String path;

    private String allowExtension;

}
