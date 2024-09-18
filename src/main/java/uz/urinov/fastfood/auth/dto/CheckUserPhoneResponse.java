package uz.urinov.fastfood.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.urinov.fastfood.auth.enums.StatusAuth;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckUserPhoneResponse {
    private StatusAuth status;
}
