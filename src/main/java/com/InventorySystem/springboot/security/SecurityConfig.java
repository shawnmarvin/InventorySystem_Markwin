package com.InventorySystem.springboot.security;

import com.InventorySystem.springboot.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final EmployeeServiceImpl employeeServiceImplements;

    @Autowired
    public SecurityConfig(EmployeeServiceImpl employeeServiceImplements) {
        this.employeeServiceImplements = employeeServiceImplements;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance()).withUser("admin").password("password")
//                .roles("ADMIN");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().and().authorizeRequests()
//                .antMatchers("/**").hasRole("ADMIN").and().csrf().disable()
//                .headers().frameOptions().disable();
        http.csrf().disable().authorizeRequests()
                .antMatchers("/employee/post").permitAll()
                .antMatchers("/**").permitAll().anyRequest().authenticated()
                .and().httpBasic()
        ;
    }

    @Bean
    public DaoAuthenticationProvider doaAuthenticatorProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        provider.setUserDetailsService(employeeServiceImplements);
        return provider;
    }
}
