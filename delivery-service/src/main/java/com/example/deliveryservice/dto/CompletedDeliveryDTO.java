package com.example.deliveryservice.dto;

import lombok.Data;



@Data
public class CompletedDeliveryDTO {
    private String ladingCode;
    private String postman;
    private String status;

    public CompletedDeliveryDTO(String LadingCode, String Postman, String Status) {
        this.ladingCode = LadingCode;
        this.postman = Postman;
        this.status = Status;
    }
}
