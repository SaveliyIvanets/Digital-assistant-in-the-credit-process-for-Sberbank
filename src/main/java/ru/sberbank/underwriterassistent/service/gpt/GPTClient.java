package ru.sberbank.underwriterassistent.service.gpt;

import com.google.gson.Gson;
import ru.sberbank.underwriterassistent.dto.GPTRequestDTO;
import ru.sberbank.underwriterassistent.dto.GPTResponseDTO;
import ru.sberbank.underwriterassistent.dto.TokenDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;



public class GPTClient {
    public GPTResponseDTO sendMessage(GPTRequestDTO json) throws IOException {
        String message = new Gson().toJson(json);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(message))
                .uri(URI.create("https://gigachat.devices.sberbank.ru/api/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + getToken())
                .POST(HttpRequest.BodyPublishers.ofString(message))
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        GPTResponseDTO responseDTO = new Gson().fromJson(response.body(), GPTResponseDTO.class);
        return responseDTO;
    }
    private String getToken() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("scope", "GIGACHAT_API_PERS");

        String form = parameters.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .uri(URI.create("https://ngw.devices.sberbank.ru:9443/api/v2/oauth"))
                .header("Authorization", "Bearer YWRmODYzZGYtYTZlOC00ZGYwLWIwMWItYmZjNjZjMjk3MDdkOjc0MGQxZDRmLWM0MDgtNGE0NS05MmE2LTk2ZGIwMDkyZDBiZg==")
                .header("RqUID", UUID.randomUUID().toString())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("scope", "GIGACHAT_API_PERS")
                .build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String targetObject = new Gson().fromJson(response.body(), TokenDTO.class).access_token;
        return targetObject;
    }
}

