package com.transaction.Transaction_Routine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.springframework.security.config.Customizer.withDefaults;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {
	
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http
//        .authorizeRequests(authorizeRequests -> 
//            authorizeRequests
//                .requestMatchers("/accounts/**", "/transactions/**").authenticated()
//                .anyRequest().permitAll()
//        )
//        .httpBasic(withDefaults())
//        .csrf(csrf -> csrf.disable());
//    return http.build();
//}
//    
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build());
//        return manager;
//    }
	
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/", "/error", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/oauth2/authorization/github")
                .defaultSuccessUrl("/dashboard", true)
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/").permitAll()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
	

}
