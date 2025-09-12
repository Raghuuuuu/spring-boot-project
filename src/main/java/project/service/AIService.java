package project.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import project.model.UserDTO;
import project.repo.UserRepo;

@Service
public class AIService {

	public String getAiResponse(String userInput) {
		try {
			String jsonInput = "{\"prompt\": \"" + userInput + "\"}";

			URL url = new URL("http://localhost:8000/generate");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoOutput(true);

			try (OutputStream os = con.getOutputStream()) {
				os.write(jsonInput.getBytes(StandardCharsets.UTF_8));
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				InputStream responseStream = con.getInputStream();
				try (Scanner scanner = new Scanner(responseStream, StandardCharsets.UTF_8.name())) {
					String responseBody = scanner.useDelimiter("\\A").next();
					return "AI Response: " + responseBody;
				}
			} else {
				con.disconnect();
				return "Error: " + responseCode;
			}
		} catch (Exception e) {
			return e.getMessage();

		}
	}


	public String getAiResponse(UserDTO decryptionRequest) {
		return "Raghu";
	}

}
