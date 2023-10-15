package com.example.fetelix.dto.account;

import lombok.Data;

@Data
public class GoogleUserInfoDto {
    private String email;
    private String family_name;
    private String given_name;
    private String id;
    private String locale;
    private String name;
    private String picture;
}
