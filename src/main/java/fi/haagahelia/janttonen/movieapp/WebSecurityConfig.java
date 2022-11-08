package fi.haagahelia.janttonen.movieapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	@Autowired
	private UserDetailsService UDS;

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/css/**", "/", "/movie-app/**").permitAll().antMatchers("/admin/**")
				.hasRole("ADMIN").anyRequest().authenticated().and().formLogin()//.loginPage("/login")
				.defaultSuccessUrl("/movie-app", true).permitAll().and().logout().logoutSuccessUrl("/movie-app").permitAll().and().httpBasic();
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(UDS).passwordEncoder(new BCryptPasswordEncoder());
	}
}
