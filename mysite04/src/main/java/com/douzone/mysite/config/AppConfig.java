package com.douzone.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.app.DBConfig;
import com.douzone.config.app.MyBaitsConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.douzone.mysite.service","com.douzone.mysite.repository","com.douzone.mysite.aspect"})
@Import({DBConfig.class, MyBaitsConfig.class})
public class AppConfig {
	// 야기는 설정들만 땡겨오는곳
}
