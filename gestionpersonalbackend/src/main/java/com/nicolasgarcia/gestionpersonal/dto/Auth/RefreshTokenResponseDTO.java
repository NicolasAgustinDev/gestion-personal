package com.nicolasgarcia.gestionpersonal.dto.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenResponseDTO {
    private String accessToken;
    private String refreshToken;
}
