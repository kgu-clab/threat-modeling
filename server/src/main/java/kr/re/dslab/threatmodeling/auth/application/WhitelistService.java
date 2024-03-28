package kr.re.dslab.threatmodeling.auth.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class WhitelistService {

    @Value("${security.swagger.whitelist.enabled}")
    private boolean whitelistEnabled;

    @Value("${security.swagger.whitelist.path}")
    private String whitelistPath;

    public List<String> loadWhitelistIps() {
        // whitelistEnabled가 false인 경우 모든 IP를 허용
        if (!whitelistEnabled) {
            return List.of("*");
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            Path path = Paths.get(whitelistPath);
            // whitelistPath가 존재하지 않는 경우 whitelistPath의 부모 디렉토리를 생성하고 whitelistPath에 defaultContent를 작성
            if (Files.notExists(path)) {
                Files.createDirectories(path.getParent());
                Map<String, List<String>> defaultContent = Map.of("allowedIps", List.of("*"));
                mapper.writeValue(Files.newBufferedWriter(path), defaultContent);
                log.info("Whitelist IP file created at {}", whitelistPath);
            }
            // whitelistPath로부터 whitelist IP를 읽어옴
            Map<String, List<String>> data = mapper.readValue(path.toFile(), new TypeReference<>() {});
            return data.getOrDefault("allowedIps", List.of("*"));
        } catch (IOException e) {
            // whitelistPath를 로드하거나 생성하는 데 실패한 경우 모든 IP를 허용
            log.error("Failed to load or create IP whitelist from path: {}", whitelistPath, e);
            return List.of("*");
        }
    }

}
