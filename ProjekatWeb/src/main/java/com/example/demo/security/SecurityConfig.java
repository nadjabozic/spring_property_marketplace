package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
        						.requestMatchers(new AntPathRequestMatcher("/contrLogin/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
                                .requestMatchers(new AntPathRequestMatcher("/prodavac/**")).hasRole("PRODAVAC")
                                .requestMatchers(new AntPathRequestMatcher("/kupac/**")).hasRole("KUPAC")
                                .requestMatchers(new AntPathRequestMatcher("/controllerP/**")).permitAll()
//                                .requestMatchers(new AntPathRequestMatcher("/Projekat/controllerP/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                                .anyRequest().authenticated())
                		.formLogin(form -> form
                        .loginPage("/contrLogin/login").permitAll()
                        .loginProcessingUrl("/contrLogin/login")
                        .defaultSuccessUrl("/contrLogin/glavna", true)
                		.failureUrl("/contrLogin/login-error"))
                		.exceptionHandling(handling -> handling.accessDeniedPage("/zabranjenPristup"))
                .csrf(csrf -> csrf.disable());

		return http.build();
			
	}

	/*
	 * @Bean UserDetailsService userDetailsService() { UserDetails userDetails =
	 * User.withUsername("admin") .password(getPasswordEncoder().encode("123456"))
	 * .roles("MANAGER") .build();
	 * 
	 * return new InMemoryUserDetailsManager(userDetails); }
	 */

	/*
	 * @Bean UserDetailsService customUserDetailsService() { return new
	 * CustomUserDetailService(); }
	 */

	@Bean
	AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		if (userDetailsService == null) {
            // Handle the case of a null user
            throw new UsernameNotFoundException("User not found");
        }
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
