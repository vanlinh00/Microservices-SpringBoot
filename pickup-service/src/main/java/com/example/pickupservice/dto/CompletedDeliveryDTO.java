package com.example.pickupservice.dto;


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
