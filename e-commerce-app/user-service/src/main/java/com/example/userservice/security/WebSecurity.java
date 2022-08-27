package com.example.userservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private static final String LOCAL_IP_ADDRESS = "172.30.1.255";  // 본인 IP로 사용

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/user/**").permitAll();
        http.authorizeRequests()
            .antMatchers("/**")
            .hasIpAddress(LOCAL_IP_ADDRESS)
            .and()
            .addFilter(getAuthenticationFilter());

        http.headers().frameOptions().disable();    // for h2-console access
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());

        return authenticationFilter;
    }
}
