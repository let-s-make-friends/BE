package team.nahyunuk.gsmcertificationsystem.v1.global.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Value("${cors.allowedOrigin}")
    private List<String> allowedOrigins;

    @Value("${cors.allowedOriginAdmin}")
    private List<String> allowedOriginsAdmin;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Client
        CorsConfiguration clientConfig = new CorsConfiguration();
        clientConfig.setAllowedOrigins(allowedOrigins);
        clientConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        clientConfig.setAllowedHeaders(List.of("*"));
        clientConfig.setAllowCredentials(true);
        source.registerCorsConfiguration("/api/**", clientConfig);

        // Admin
        CorsConfiguration adminConfig = new CorsConfiguration();
        adminConfig.setAllowedOrigins(allowedOriginsAdmin);
        adminConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        adminConfig.setAllowedHeaders(List.of("*"));
        adminConfig.setAllowCredentials(true);
        source.registerCorsConfiguration("/admin/**", adminConfig);

        return source;
    }
}
