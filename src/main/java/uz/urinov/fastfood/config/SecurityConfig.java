package uz.urinov.fastfood.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import uz.urinov.fastfood.util.MD5Util;


@Component
@EnableWebSecurity
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public AuthenticationProvider authenticationProvider() {

        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() { //
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return MD5Util.getMD5(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            .requestMatchers("/auth/mobile/client/v1/account/**").permitAll()
                            .requestMatchers("/v3/api-docs/**","/swagger-ui/**").permitAll()
                            .requestMatchers("/category/adm/**").hasRole("ADMIN")
                            .requestMatchers("/category/lang").permitAll()
                            .requestMatchers("/region/adm/**").hasRole("ADMIN")
                            .requestMatchers("/stadium/owr/**").hasAnyRole("ADMIN","OWNER")
                            .requestMatchers("/field/owr/**").hasAnyRole("ADMIN","OWNER")
                            .requestMatchers("/booking/owr/**").hasAnyRole("ADMIN","OWNER")
                            .requestMatchers("/check/**").hasAnyRole("ADMIN","MODERATOR")
                            .requestMatchers("/region/lang").permitAll()
                            .requestMatchers("/attach/upload").hasAnyRole("ADMIN","MODERATOR","OWNER")
                            .requestMatchers("/attach/download/**").permitAll()



                            .anyRequest()
                            .authenticated();
                });

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);


        return http.build();
    }


}
