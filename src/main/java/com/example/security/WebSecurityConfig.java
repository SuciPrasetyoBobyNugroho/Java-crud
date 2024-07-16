package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.services.AppUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Menonaktifkan proteksi CSRF.
                .authorizeHttpRequests((requests) -> requests // Memulai konfigurasi untuk mengatur izin akses pada
                                                              // permintaan HTTP. Dengan menggunakan lambda
                        .requestMatchers("/api/users/register").permitAll() // Mengizinkan semua permintaan ke endpoint
                                                                            // /api/users/register tanpa autentikasi.
                        .anyRequest().authenticated())// Mengharuskan autentikasi untuk semua permintaan lainnya.
                .httpBasic(); // Menggunakan otentikasi HTTP dasar (basic HTTP authentication).
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
