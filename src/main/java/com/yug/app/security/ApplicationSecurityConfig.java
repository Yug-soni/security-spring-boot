package com.yug.app.security;

import lombok.AllArgsConstructor;
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
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        CharSequence charSequence = "p";
        UserDetails yug_student = User.builder()
                .username("yug")
                .password(passwordEncoder.encode(charSequence))
                .roles(STUDENT.name()) // ROLE_STUDENT
                .build();

        UserDetails yug_admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode(charSequence))
                .roles(ADMIN.name()) // ROLE_ADMIN
                .build();

        UserDetails yug_trainee = User.builder()
                .username("trainee")
                .password(passwordEncoder.encode(charSequence))
                .roles(ADMIN_TRAINEE.name()) // ROLE_ADMIN_TRAINEE
                .build();

        return new InMemoryUserDetailsManager(
                yug_student,
                yug_admin,
                yug_trainee
        );
    }

}
