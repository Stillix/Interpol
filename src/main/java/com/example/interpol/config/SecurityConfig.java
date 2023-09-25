package com.example.interpol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig {

   @Bean
   protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
      http
              .authorizeHttpRequests()
              .requestMatchers("/", "/registration", "/authorization", "/view/notices").permitAll()
              .requestMatchers("/admin/**").hasRole("ADMIN")
              .requestMatchers("/client/**").hasRole("CLIENT")
              .anyRequest().authenticated()
              .and()
              .formLogin()
              .loginPage("/authorization")
              .permitAll()
              .and()
              .logout()
              .permitAll()
              .logoutSuccessUrl("/authorization?logout=true")
              .and()
              .exceptionHandling();
      return http.build();
   }

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return http
              .authorizeHttpRequests(auth ->
                      auth
                              .requestMatchers("/admin/**").hasRole("ADMIN")
                              .requestMatchers("/", "/registration", "/authorization", "/view/notices").permitAll()
                              .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                              .anyRequest().hasAnyRole("USER", "ADMIN")
              )
              .formLogin(formLogin ->
                      formLogin
                              .loginPage("/login")
                              .permitAll()
              ).logout(logout ->
                      logout
                              .logoutUrl("/logout")
                              .permitAll())
              .build();
   }


//   @Bean
//   protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//      http
//              .authorizeHttpRequests()
//              .requestMatchers("/", "/registration", "/authorization", "/view/notices").permitAll().anyRequest().permitAll();
//      return http.build();
//   }

}
