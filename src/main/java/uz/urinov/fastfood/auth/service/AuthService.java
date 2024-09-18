package uz.urinov.fastfood.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import uz.urinov.fastfood.auth.dto.*;
import uz.urinov.fastfood.auth.enums.Language;
import uz.urinov.fastfood.auth.enums.StatusAuth;
import uz.urinov.fastfood.exp.AppBadException;
import uz.urinov.fastfood.profile.dto.ProfileCreateDTO;
import uz.urinov.fastfood.profile.dto.ProfileResponseDTO;
import uz.urinov.fastfood.profile.entity.ProfileEntity;
import uz.urinov.fastfood.profile.enums.ProfileRole;
import uz.urinov.fastfood.profile.enums.ProfileStatus;
import uz.urinov.fastfood.profile.repository.ProfileRepository;
import uz.urinov.fastfood.sms.entity.SmsHistoryEntity;
import uz.urinov.fastfood.sms.repasitory.SmsHistoryRepository;
import uz.urinov.fastfood.sms.service.SmsHistoryService;
import uz.urinov.fastfood.sms.service.SmsService;
import uz.urinov.fastfood.util.ApiResponse;
import uz.urinov.fastfood.util.JWTUtil;
import uz.urinov.fastfood.util.MD5Util;
import uz.urinov.fastfood.util.RandomUtil;


import java.util.Locale;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final ProfileRepository profileRepository;
    private final SmsService smsService;
    private final SmsHistoryRepository smsHistoryRepository;
    private final SmsHistoryService smsHistoryService;
    private final ResourceBundleMessageSource rbms;

    // CheckUserPhoneRequest
    public CheckUserPhoneResponse checkUserPhone(CheckUserPhoneRequest dto) {
        CheckUserPhoneResponse response = new CheckUserPhoneResponse();
        Optional<ProfileEntity> optionalProfile = profileRepository.findByPhone(dto.getPhone());

        if (optionalProfile.isEmpty()) {
            response.setStatus(StatusAuth.NOT_FOUND);
            return response;
        }
        ProfileEntity profileEntity = optionalProfile.get();
        if (!profileEntity.getVisible()) {
            response.setStatus(StatusAuth.BLOCKED);
            return response;
        }
        if (profileEntity.getStatus().equals(ProfileStatus.INACTIVE)) {
            response.setStatus(StatusAuth.INACTIVE);
            return response;
        }
        response.setStatus(StatusAuth.ACTIVE);
        return response;

    }

    // Profile registration Sms
    public ApiResponse<String> registrationSms(ProfileCreateDTO dto, Language lang) {
        Optional<ProfileEntity> optionalProfile = profileRepository.findByPhone(dto.getPhone());
        if (optionalProfile.isPresent()) {
            log.warn("Ismi name = {}, phone = {}", dto.getName(), dto.getPhone());
            String message=rbms.getMessage("phone.exists",null, new Locale(lang.name()));
            return new ApiResponse<>( message, 409, true);
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setPassword(MD5Util.getMD5(dto.getPassword()));
        entity.setRole(ProfileRole.valueOf(dto.getRole().name()));

        entity.setStatus(ProfileStatus.INACTIVE);
        profileRepository.save(entity);

        log.info("Ismi name = {}, phone = {}", dto.getName(), dto.getPhone());

        // Sms yuborish methodini chaqiramiz;
        String smsCode = RandomUtil.getRandomSmsCode();
//        String smsCode = "Bu Eskiz dan test";    // TODO: Phone ga code ketadigan qilish kerak;
        smsService.sendSms(dto.getPhone(), smsCode);
        String message=rbms.getMessage("active.do",null, new Locale(lang.name()));
        return new ApiResponse<>( message, 201, false);

    }

    // Profile verifySms
    public ApiResponse<String> verifySms(VerifyDto dto, Language lang) {
        Optional<SmsHistoryEntity> bySmsCodeAndPhone = smsHistoryRepository.findBySmsCodeAndPhone(dto.getSmsCode(), dto.getPhone());
        if (bySmsCodeAndPhone.isEmpty()) {
            String message=rbms.getMessage("password.wrong",null, new Locale(lang.name()));
            return new ApiResponse<>( message, 409, true);
        }
        ProfileEntity entity = checkPhone(dto.getPhone(),lang);
        entity.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(entity);
        String message=rbms.getMessage("active",null, new Locale(lang.name()));
        return new ApiResponse<>( message, 201, false);
    }

    // Resent sms code
    public ApiResponse<String> verificationResendSms(String phone,Language lang) {

        ProfileEntity profileEntity = checkPhone(phone,lang);

        if (!profileEntity.getVisible() || profileEntity.getStatus().equals(ProfileStatus.INACTIVE)) {
            String response=rbms.getMessage("registration.not",null, new Locale(lang.name()));
            throw new AppBadException(response);
        }
        smsHistoryService.checkEmailLimit(profileEntity.getPhone());
        String smsCode = RandomUtil.getRandomSmsCode();
//        String smsCode = "Bu Eskiz dan test";    // TODO: Phone ga code ketadigan qilish kerak;
        smsService.sendSms(profileEntity.getPhone(), smsCode);
        String message=rbms.getMessage("active.do",null, new Locale(lang.name()));
        return new ApiResponse<>( message, 201, false);
    }


    // Profile login
    public ApiResponse<ProfileResponseDTO> loginProfile(LoginDto loginDto, Language lang) {
        String password = MD5Util.getMD5(loginDto.getPassword());
        Optional<ProfileEntity> profileEntityOptional = profileRepository.findByPhoneAndPasswordAndVisibleTrueAndStatusActive(loginDto.getUsername(), password);
        if (profileEntityOptional.isEmpty()) {
            log.warn("Profile phone = {}, password = {},", loginDto.getUsername(), password);
            String response=rbms.getMessage("password.wrong",null, new Locale(lang.name()));
            throw new AppBadException(response);
        }
        ProfileEntity profileEntity = profileEntityOptional.get();
        ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO();
        profileResponseDTO.setId(profileEntity.getId());
        profileResponseDTO.setPhone(profileEntity.getPhone());
        profileResponseDTO.setRole(profileEntity.getRole().toString());
        profileResponseDTO.setStatus(profileEntity.getStatus().toString());
        profileResponseDTO.setDayJwt(JWTUtil.encodeDay(profileEntity.getId(), profileEntity.getPhone(), profileEntity.getRole()));
        return new ApiResponse<>(200, false, profileResponseDTO);

    }

    // Forget User Phone Request
    public ApiResponse<String> forget(CheckUserPhoneRequest dto,Language lang) {

        ProfileEntity profileEntity = checkPhone(dto.getPhone(),lang);
        log.info("Ismi name = {}, phone = {}", profileEntity.getName(), profileEntity.getPhone());

        // Sms yuborish methodini chaqiramiz;
        String message = RandomUtil.getRandomSmsCode();
//        String smsCode = "Bu Eskiz dan test";    // TODO: Phone ga code ketadigan qilish kerak;
        smsService.sendSms(profileEntity.getPhone(), message);
        String response=rbms.getMessage("active.do",null, new Locale(lang.name()));
        return new ApiResponse<>( message, 201, false);
    }

    // Forget User password update Request
    public ApiResponse<String> forgetUpdatePassword(ForgetPasswordDto dto, Language lang) {
        ProfileEntity profileEntity = checkPhone(dto.getPhone(),lang);

        Optional<SmsHistoryEntity> bySmsCodeAndPhone = smsHistoryRepository.findBySmsCodeAndPhone(dto.getSmsCode(), dto.getPhone());
        if (bySmsCodeAndPhone.isEmpty()) {
            String message=rbms.getMessage("message.or.phone.wrong",null, new Locale(lang.name()));
            return new ApiResponse<>( message, 409, true);
        }
        profileEntity.setPassword(MD5Util.getMD5(dto.getNewPassword()));
        profileRepository.save(profileEntity);
        String message=rbms.getMessage("do.not.you.password",null, new Locale(lang.name()));
        return new ApiResponse<>( message, 201, false);
    }

    public ProfileEntity checkPhone(String phone,Language lang) {
        return profileRepository.findByPhoneAndVisibleTrue(phone).orElseThrow(() -> {
            log.warn("Profile not found id : {}", phone);
            String response=rbms.getMessage("item.not.found",null, new Locale(lang.name()));
            throw new AppBadException(response);
        });
    }

}
