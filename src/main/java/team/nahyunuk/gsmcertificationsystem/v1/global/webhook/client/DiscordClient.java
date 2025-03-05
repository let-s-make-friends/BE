package team.nahyunuk.gsmcertificationsystem.v1.global.webhook.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class DiscordClient {

    private final RestTemplate restTemplate;

    @Value("${discord.webhook.url}")
    private String webhookUrl;

    public void sendMessage(String message) {
        restTemplate.postForEntity(webhookUrl, message, String.class);
    }
}
