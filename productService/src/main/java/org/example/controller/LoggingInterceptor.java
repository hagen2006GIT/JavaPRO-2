package org.example.controller;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LoggingInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Выводим информацию о запросе
//        log("Запрос ", request.getMethod(), request.getURI(), new String(body, StandardCharsets.UTF_8));

//        request.getHeaders().add("Foo", "bar");

        // Выполняем запрос
        ClientHttpResponse response = execution.execute(request, body);

        // Выводим информацию о полученном ответе
        log("Ответ", response.getStatusCode(), response.getStatusText(), StreamUtils.copyToString(response.getBody(), StandardCharsets.UTF_8));

        return response;
    }

    private void log(String prefix, Object... attributes) {
        // Формируем и выводим лог
        System.out.println(prefix + " " + Arrays.stream(attributes).map(Object::toString).collect(Collectors.joining(" | ")));
    }
}