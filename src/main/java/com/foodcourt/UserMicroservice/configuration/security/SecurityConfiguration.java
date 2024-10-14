package com.foodcourt.UserMicroservice.configuration.security;

import com.foodcourt.UserMicroservice.adapters.driven.token.jwt.JwtAuthenticationFilter;
import com.foodcourt.UserMicroservice.adapters.driven.token.util.UserDetailsServiceImpl;
import com.foodcourt.UserMicroservice.configuration.security.util.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.foodcourt.UserMicroservice.configuration.security.util.AuthenticationEntryPointImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationEntryPointImpl authenticationEntryPointImpl;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService, AuthenticationEntryPointImpl authenticationEntryPointImpl) {
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPointImpl = authenticationEntryPointImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(userDetailsService);

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(SecurityConstants.DATA_INITIALIZER_PATH).permitAll()
                        .requestMatchers(SecurityConstants.LOGIN_URL).permitAll()
                        .requestMatchers(SecurityConstants.CREATE_USER_URL).hasAnyRole(SecurityConstants.ADMIN_ROLE,
                                SecurityConstants.OWNER_ROLE)
                        .requestMatchers(SecurityConstants.CUSTOMER_REGISTRATION_URL).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPointImpl))
                .formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
