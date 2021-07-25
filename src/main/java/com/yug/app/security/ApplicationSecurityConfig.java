package com.yug.app.security;

import lombok.AllArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.yug.app.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        CharSequence charSequence = "p";
        UserDetails yug =  User.builder()
                .username("yug")
                .password(passwordEncoder.encode(charSequence))
                .roles(STUDENT.name()) // this stored as ROLE_STUDENT
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode(charSequence))
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(
                yug, admin
        );
    }

}
