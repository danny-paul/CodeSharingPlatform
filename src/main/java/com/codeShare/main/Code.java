package com.codeShare.main;

import org.apache.tomcat.jni.Local;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Code {
    private LocalDateTime localDT;
    private String code;
    private Path codeSnippetPath;
    private DateTimeFormatter dTF;

    Code() {
        codeSnippetPath = Paths.get("C:\\Users\\danny\\Desktop\\main\\src\\main\\java\\com\\codeShare\\main\\code.txt");
        dTF = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        try {
            if (Files.exists(codeSnippetPath)) { //if code already present in file, adds to object
                String fileContents[] = readAsString(codeSnippetPath.toString()).split("TIME:");
                this.code = fileContents[0];
                fileContents[1] = fileContents[1].trim();
                this.localDT = LocalDateTime.parse(fileContents[1]);
            } else {  //assigns 0 length string when no file
                this.code = "";
                this.localDT = LocalDateTime.now();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public LocalDateTime getLocalDT() {
        return this.localDT;
    }

    public String getCode() {
        return this.code;
    }

    public String getHtmlCode() {
        Path htmlTemplatePath = Paths.get("C:\\Users\\danny\\Desktop\\main\\src\\main\\java\\com\\codeShare\\main\\CurrentCode.html");
        String htmlWithCode = "";
        try {
            htmlWithCode = String.format(readAsString(htmlTemplatePath.toString()), this.code, this.localDT.format(dTF));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return htmlWithCode;
    }

    public void updateCode(String plainCode) {
        this.code = plainCode;
        this.localDT = LocalDateTime.now();
        writeToFile();
    }

    private void writeToFile() {
        String toFile = new String(this.code + "\nTIME:" + this.localDT.format(dTF));
        byte[] byteToString = toFile.getBytes();
        try {
            Files.write(codeSnippetPath, byteToString);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private String readAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}

