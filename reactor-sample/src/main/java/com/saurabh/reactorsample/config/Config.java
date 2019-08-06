package com.saurabh.reactorsample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.session.data.redis.config.annotation.web.server.EnableRedisWebSession;

@Configuration
@EnableWebFluxSecurity
@EnableRedisWebSession
@EnableReactiveMethodSecurity
public class Config {

	
	@Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
		 return http
		            .csrf().disable()
					.authorizeExchange().pathMatchers("/login").permitAll()
					.and().authorizeExchange().
					 anyExchange().authenticated()
					.and().formLogin().disable()
					.httpBasic().disable()
		            .build();
    }
	
}
