package com.yanda.hospital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//进行跨域配置
		//前端占用8080 后端占用8888
		//两个端口之间的访问就是跨域
		//要允许8080端口访问8888
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
				.allowCredentials(true)
				.maxAge(3600)
				.allowedHeaders("*");

	}
}
