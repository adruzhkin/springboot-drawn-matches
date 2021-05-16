package com.druzhkin.springbootdrawnmatches.config;

import com.druzhkin.springbootdrawnmatches.jwt.JwtAuthEntryPoint;
import com.druzhkin.springbootdrawnmatches.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final UserDetailsService jwtUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthEntryPoint jwtAuthEntryPoint, UserDetailsService jwtUserDetailsService,
                             JwtRequestFilter jwtRequestFilter) {
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // We don't need CSRF
                .csrf().disable()
                // Don't authenticate these particular requests
                .authorizeRequests().antMatchers("/api/auth", "/api/public/matches/**").permitAll()
                // All other requests need to be authenticated
                .anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()
                // Make sure we use stateless session,
                // session won't be used to store user's state
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
