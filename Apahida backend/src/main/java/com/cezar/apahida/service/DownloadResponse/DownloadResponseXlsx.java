package com.cezar.apahida.service.DownloadResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class DownloadResponseXlsx extends DownloadResponse{

    public ResponseEntity<byte[]> get(byte[] bytes){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "orders.xlsx");
        return ResponseEntity.ok().headers(headers).body(bytes);
    }

}
