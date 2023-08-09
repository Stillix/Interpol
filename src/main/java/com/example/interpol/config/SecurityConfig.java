package com.example.interpol.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

   @Autowired
   private DataSource dataSource;

   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.jdbcAuthentication()
              .dataSource(dataSource)
              .usersByUsernameQuery("SELECT login, password FROM users WHERE login = ?")
              .authoritiesByUsernameQuery("SELECT login, role FROM users WHERE login = ?")
              .passwordEncoder(new BCryptPasswordEncoder());
   }

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
              .exceptionHandling()
              .accessDeniedPage("/access-denied");
      return http.build();
   }

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
}