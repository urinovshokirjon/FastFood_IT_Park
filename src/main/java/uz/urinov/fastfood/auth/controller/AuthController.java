package uz.urinov.fastfood.auth.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.urinov.fastfood.auth.dto.*;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.auth.service.AuthService;
import uz.urinov.fastfood.profile.dto.ProfileCreateDTO;
import uz.urinov.fastfood.profile.dto.ProfileResponseDTO;
import uz.urinov.fastfood.util.ApiResponse;


@Slf4j
@RestController
@RequestMapping("/auth/mobile/client/v1/account")
@Tag(name = "Auth Controller", description = "Api list for authorization, registration and other ... ")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // CheckUserPhoneRequest (Profileni phone statusini tekshirish)
    @PostMapping("/check")
    public ResponseEntity<CheckUserPhoneResponse> checkUserPhone(@Valid @RequestBody CheckUserPhoneRequest dto) {
        CheckUserPhoneResponse result = authService.checkUserPhone(dto);
        return ResponseEntity.ok().body(result);
    }

    // Profile registration Sms
    @PostMapping("/registration-sms")
    public ResponseEntity<ApiResponse<String>> registrationSms(@Valid @RequestBody ProfileCreateDTO dto,
                                                               @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(authService.registrationSms(dto, lang));
    }

    // Profile verifySms (Sms kode orqali tekshiruvdan o'tkazish)
    @PostMapping("/verifySms")
    public ResponseEntity<ApiResponse<String>> verifySms(@Valid @RequestBody VerifyDto dto,
                                                         @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(authService.verifySms(dto, lang));
    }

    // Resent sms code (Parol esdan chiqib qolganda)
    @PostMapping("/verification/resendSma/{phone}")
    public ResponseEntity<ApiResponse<String>> verificationResendSms(@PathVariable String phone,
                                                                     @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(authService.verificationResendSms(phone, lang));
    }

    // Profile login
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<ProfileResponseDTO>> loginUser(@RequestBody LoginDto loginDto,
                                                                     @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(authService.loginProfile(loginDto, lang));
    }

    // Forget User password Request (Parol esdan chiqib qolganda )
    @PostMapping("/forget")
    public ResponseEntity<ApiResponse<String>> forget(@Valid @RequestBody CheckUserPhoneRequest dto,
                                                      @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(authService.forget(dto, lang));
    }

    // Forget User password update Request (Parol esdan chiqib qolganda yangi parol o'rnatish)
    @PostMapping("/forget-update-password")
    public ResponseEntity<ApiResponse<String>> forgetUpdatePassword(@Valid @RequestBody ForgetPasswordDto dto,
                                                                    @RequestHeader(value = "Accept-Language", defaultValue = "UZ") Language lang) {
        return ResponseEntity.ok(authService.forgetUpdatePassword(dto, lang));
    }


}
