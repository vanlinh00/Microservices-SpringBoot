package com.example.deliveryservice.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CompletedDeliveryDTO {
    private String ladingCode;
    private String postman;
    private String status;


}
