package com.douzone.security.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.douzone.security.vo.UserVo;

// 시큐리티가 /login 주소 요청이 오면 url를 낚아채서 로그인을 진행시킨다
// 로그인이 완료가 되면 session에 시큐리티가 유저의 정보를 넣어준다
// 단 우리가 만드는 세션과 동일한 세션이지만 시큐리티가 가진 세션공간이 있다()
// 그 세션 공간을 키값으로 구분하나?(키값은 Security ContentHolder) 이 카값으로 세션정보를 저장 시킨다
// 이때 이 시큐리티만의 세션에 들어갈수 있는 오브젝트가 정해져있다 => Authentication 타입의 객체
// Authentication 안에는 유저의 정보가 들어가있어야된다.
// 이 User오브젝트의 타입은 => UserDetails 타입의 객체여야함

// Security Session => Authentication 타입의 객체로 유저정보를 저장하고 => 저장할때는 UserDetails 타입이여야 한다
// 나중에 Security Session에서 유저 객체를 꺼내면 Authentication 객체로 기어나오고 이안의 UserDetails 객체를 꺼내면 유저 객체에 접근을 할수 있다
// 그러면 userDetails 객체를 꺼낼수 있는가 밑을 봐라


public class PrincipalDetails implements UserDetails { // UserDetails를 implement를 하면 PrincipalDetails가 UserDetails 와 같은
														// 타입이 되고 Authentication 객체에 담을
														// 수 있게 된다
	private UserVo userVo; // 콤포지션

	// 생성자를 만들어서
	public PrincipalDetails(UserVo userVo) {
		System.out.println(userVo);
		this.userVo = userVo;
	}
	
	// 해당 유저의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
//		user.getUsername(); --> 이타입은 스트링 타입이기 때문에 리턴할수가 없다 시큐리티에서 객체타입을 정해놓았기 때문이다
		Collection<GrantedAuthority> collect = new ArrayList<>(); // 해당 객체의 어레이 리스트를 만들어서
		collect.add(new GrantedAuthority(){
			@Override
			public String getAuthority() {
				System.out.println("여기는 롤 설정");
				return userVo.getRole();
			}
		});
		
		return collect;
	}
	
	// 비밀번호 셋팅
	@Override
	public String getPassword() {
		System.out.println("여기는 비밀번호 설정");
		return userVo.getPassword();
	}

	// 사용자 이름 셋팅
	@Override
	public String getUsername() {
		System.out.println("여기는 이름 설정");
		return userVo.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	
	// 계정 만료 셋팅
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	
	// 계정 활성화여부 비밀번호의 기간이 언제인지 확인 하는것
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		// 우리 사이트에사 1냔동안 로그인을 안하면 휴먼 계정으로 하기로함
		// 현제시간-러긴시간
		
		return true;
	}
	// UserDetails 객체의 설정이 끝이 났다 Authentication 객체 설정을 하러가자
}
