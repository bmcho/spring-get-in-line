package com.bm.getin.config;

import com.bm.getin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityCustomConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(
            AuthenticationManagerBuilder auth,
            PasswordEncoder passwordEncoder,
            AdminService adminService
    ) throws Exception {
        auth.userDetailsService(adminService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/event/**", "/places/**")
                .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                .formLogin()
                    .permitAll()
                    .loginPage("/login")
                    .defaultSuccessUrl("/admin/places")
                .and()
                .logout()
                    .permitAll()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
        ;

        return http.build();
    }
}
