package com.example.graphqldemo.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    // 아래 코드는 기본 보안 설정을 최소화하고, 모든 요청을 허용하는 구조
    @Bean
    DefaultSecurityFilterChain springWebFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)                  // csrf 기능 비활성화 POST 요청시 클라이언트 측에서 요청을 위조하는 공격을방지하는 기능을 말함.
                .authorizeHttpRequests(requests -> requests.anyRequest().permitAll())   // 모든 HTTP 요청을 인증 없이 허용한다. 즉, 모든 요청이 별도의 인증이나 인가 절차 없이 허용된다.
                .httpBasic(withDefaults())      // 기본 HTTP 인증 방식을 비활서화. 브라우저나 클라이언트가 기본 인증 방식을 통해 사용자 이름과 비밀번호를 입력하도록 요구
                .build();
    }

    @Bean
    public static InMemoryUserDetailsManager userDetailsManager() {
        User.UserBuilder userBuilder = User.builder();
        UserDetails testUser = userBuilder.username("test").password(passwordEncoder().encode("123")).roles("USER").build();
        return new InMemoryUserDetailsManager(testUser);
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
