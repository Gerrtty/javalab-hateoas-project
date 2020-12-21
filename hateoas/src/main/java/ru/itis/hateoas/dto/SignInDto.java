package ru.itis.hateoas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class SignInDto {

    private String email;
    private String password;

}
