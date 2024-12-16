package com.example.ztptodo.infractructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private static final String LOGIN_PAGE = "/login";
    private static final String LOGOUT_URL = "/logout/**";
    private static final String LOGOUT_SUCCESS_URL = "/login?logout";

    private static final String[] PUBLIC_MATCHERS = {
            "/", "/styles/**", "/img/**", "/scripts/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(requests -> requests
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers(PUBLIC_MATCHERS).permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage(LOGIN_PAGE)
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_URL, HttpMethod.GET.name()))
                        .logoutSuccessUrl(LOGOUT_SUCCESS_URL).permitAll()
                );

        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(config -> config.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/img/**",
                "/scripts/**",
                "/styles/**"
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
