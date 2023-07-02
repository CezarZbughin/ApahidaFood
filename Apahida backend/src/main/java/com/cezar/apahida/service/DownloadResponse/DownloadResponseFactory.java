package com.cezar.apahida.service.DownloadResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class DownloadResponseFactory {

    public static DownloadResponse getResponse(String format) {
        return switch(format) {
            case "csv" -> new DownloadResponseCsv();
            case "xlsx" -> new DownloadResponseXlsx();
            default -> new DownloadResponse();
        };
    }

}
