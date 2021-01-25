package com.codeShare.main;

import com.fasterxml.jackson.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTMLDocument;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class MainApplication {
	Code codeObj = new Code();

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@GetMapping(value = "/api/code", produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonAnyGetter
	public Map<String, String> apiCodeResponse() {
		Map<String, String> jsonMap = new HashMap<>();
		jsonMap.put("code", codeObj.getCode());
		jsonMap.put("date", codeObj.getLocalDT().toString());
		return jsonMap;
	}

	@GetMapping(value = "/code", produces = MediaType.TEXT_HTML_VALUE)
	public String htmlCodeResponse() {
		return codeObj.getHtmlCode();
	}

	@PostMapping(value = "/api/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
	@JsonAnyGetter
	public Map<String, String> updateCode(@RequestParam String code) {
		Map<String, String> emptyJson = new HashMap<>();
		emptyJson.put("Process","Success");
		codeObj.updateCode(code);
		return emptyJson;
	}
	@GetMapping(value = "/code/new")
	public String htmlCodeResponseForm() {
		return "";
	}
}




