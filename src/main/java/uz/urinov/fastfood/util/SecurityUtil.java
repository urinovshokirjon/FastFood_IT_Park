package uz.urinov.fastfood.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.urinov.fastfood.config.CustomUserDetail;
import uz.urinov.fastfood.exp.AppBadException;
import uz.urinov.fastfood.profile.entity.ProfileEntity;
import uz.urinov.fastfood.profile.enums.ProfileRole;


public class SecurityUtil {

    public static JwtDTO getJwtDTO(String token) {
        String jwt = token.substring(7); // Bearer eyJhb
        JwtDTO dto = JWTUtil.decode(jwt);
        return dto;
    }

    public static JwtDTO getJwtDTO(String token, ProfileRole requiredRole) {
        JwtDTO dto = getJwtDTO(token);
        if(!dto.getRole().equals(requiredRole)){
            throw new AppBadException("Method not allowed");
        }
        return dto;
    }
    public static String getProfileId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail user = (CustomUserDetail) authentication.getPrincipal();
        return user.getProfile().getId();
    }

    public static ProfileEntity getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetail user = (CustomUserDetail) authentication.getPrincipal();
        return user.getProfile();
    }
}
