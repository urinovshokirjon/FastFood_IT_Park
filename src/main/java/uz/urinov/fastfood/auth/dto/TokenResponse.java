package uz.urinov.fastfood.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenResponse {
    String accessToken;
    String refreshToken;
}
