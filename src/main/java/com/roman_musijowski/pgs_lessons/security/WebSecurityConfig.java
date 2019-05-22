package com.roman_musijowski.pgs_lessons.security;

import com.roman_musijowski.pgs_lessons.repositories.UserRepositoryImp;
import com.roman_musijowski.pgs_lessons.security.services.SpringSecUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserRepositoryImp.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpringSecUserDetailsServiceImpl userDetailsService;

    @Autowired
    protected void configureAuthManager(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/users/**").access("hasRole('ADMIN') or hasRole('STUDENT')")
//                .and().httpBasic();



        http.csrf().ignoringAntMatchers("/h2-console").disable()
                .authorizeRequests().antMatchers("/static/css") .permitAll()
//                .and().formLogin().loginPage("/login").permitAll()
                .and().authorizeRequests().antMatchers("/").authenticated()
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/users").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/users").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/users/{id}").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/users/{id}").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.PATCH, "/users/{id}").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.DELETE, "/users/{id}").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/users/student/{id}}").hasAuthority("STUDENT")
                .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/users/editUser").hasAuthority("STUDENT")
                .and().authorizeRequests().antMatchers(HttpMethod.PATCH, "/users/editUser").hasAuthority("STUDENT")
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/lessons").hasAnyAuthority("ADMIN","STUDENT")
                .and().authorizeRequests().antMatchers(HttpMethod.POST, "/lessons").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/lessons/{id}").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.PUT, "/lessons/{id}").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.PATCH, "/lessons/{id}").hasAuthority("ADMIN")
                .and().authorizeRequests().antMatchers(HttpMethod.DELETE, "/lessons/{id}").hasAuthority("ADMIN")

                .and().httpBasic();
//                .and().exceptionHandling().accessDeniedPage("/access_denied");

    }
}
