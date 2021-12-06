package com.douzone.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration 
@EnableWebSecurity 				// 시큐리티 활성화 -> 스프링 시큐리티 필터가 스프링 필터 체인에 등록됨
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	// 이 클래스에 내가 지금 부터 등록할 기본 필터가 등록된다
	// 그러기 위해서는 이 클래스가 웹 시큐리티 어택터가 되어야 하기 때문에
	// 인터페이스를 하나 상속받고
	
	// 비밀번호 암호화 하는 Bean 생성, 해당메서드의 리턴되는 오브젝트를 IOC로 등록해준다
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // Spring Security에서 제공하는 CSRF protection 기능을 일단 정지
		http.authorizeRequests()						// 요청이 들어왔을때	
			.antMatchers("/user/**").authenticated()	// antMatchers 이 url은 인증이 필요하다는뜻
			.antMatchers("/manager/**").access("hasRole('ROLE_ADMIN')or hasRole('ROLE_MANAGER')") // manger 로 들어오는 요청은 권한이 어드민과 미니저인 사용자만 들어오게
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().permitAll()			// 위 세가지의 요청이외에는 모두 권한을 허가하게 만듬
			.and()
			.formLogin()
			.loginPage("/loginForm")												
			.loginProcessingUrl("/login")		// login 주소가 호출이 되면 시큐리티가 낙아채서 대신 로그인을 진행 해준다
			.defaultSuccessUrl("/"); 			// 로그인이 성공하면 기본적으로 내가 지정해준 url로 이동 한다.
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encodePwd());
	
	}
	
	
	
	
	
}
