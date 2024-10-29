package com.example.graphqldemo.global.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final EntryPointUnauthorizedHandler unauthorizedHandler;
    private final SecurityService securityService;

    @Autowired
    public SecurityConfig(JwtProvider jwtProvider, EntryPointUnauthorizedHandler unauthorizedHandler, SecurityService securityService) {
        this.jwtProvider = jwtProvider;
        this.unauthorizedHandler = unauthorizedHandler;
        this.securityService = securityService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // 아래 코드는 기본 보안 설정을 최소화하고, 모든 요청을 허용하는 구조
    @Bean
    SecurityFilterChain springWebFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults())      // 기본 HTTP 인증 방식을 비활서화. 브라우저나 클라이언트가 기본 인증 방식을 통해 사용자 이름과 비밀번호를 입력하도록 요구
                .addFilterBefore(new JwtFilter(jwtProvider,securityService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        exceptionHandling -> exceptionHandling.authenticationEntryPoint(unauthorizedHandler)
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests( request ->
                        request.requestMatchers("/**").permitAll()
                                .anyRequest().authenticated())
                .build();
    }

    @Bean
    public static InMemoryUserDetailsManager userDetailsManager() {
        User.UserBuilder userBuilder = User.builder();
        UserDetails testUser = userBuilder.username("test").password(passwordEncoder().encode("123")).roles("USER").build();
        return new InMemoryUserDetailsManager(testUser);
    }


}
