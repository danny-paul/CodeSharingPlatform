package com.codeShare.main;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class MainApplication {
	Code code = new Code();

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@GetMapping("/api/code")
	@JsonAnyGetter
	public Map<String, String> apiCodeResponse() {
		Map<String, String> jsonMap = new HashMap<>();
		jsonMap.put("code", code.getCode());
		jsonMap.put("date", code.getLocalDT().toString());
		return jsonMap;
	}

	@GetMapping("/code")
	public ResponseEntity<String> htmlCodeResponse() {
		String htmlTest = "<html>\n" +
				"<head>\n" +
				"<title>Code</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"<pre id=\"code_snippet\">\n" +
				"%s\n" +
				"</pre>\n" +
				"<span id=\"load_date\">%s</span>\n" +
				"</body>\n" +
				"</html>";
		return ResponseEntity.ok().body(htmlTest);
	}

	@PostMapping("/api/code/new")
	@ResponseBody
	public void updateCode(@RequestParam String xmlCode) {
		code.updateCode(xmlCode);
		apiCodeResponse();
	}

}




