package com.montanez.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpUtil {

  public static HttpResponse<String> sendPostRequest(String url, String path, String body) {
    HttpClient httpClient = HttpClient.newHttpClient();

    HttpRequest postRequest = HttpRequest.newBuilder()
        .uri(URI.create(url + "/" + path))
        .POST(HttpRequest.BodyPublishers.ofString(body))
        .build();

    try {
      return httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException ex) {
      ex.printStackTrace();
      return null;
    }
  }

}
