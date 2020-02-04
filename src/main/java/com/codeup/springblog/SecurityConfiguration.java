package com.codeup.springblog;

import com.codeup.springblog.models.UserWithRoles;
import com.codeup.springblog.services.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = UserWithRoles.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader){
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(usersLoader)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                /* Login Configuration*/
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/posts") //users home page, it can be any URL
                    .permitAll() //anyone can go to the login page
                /* LogOut configuration*/
                .and()
                    .logout()
                    .logoutSuccessUrl("/login?logout")//append a query string value
                /* Pages that can be viewed without having to log in */
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/posts") //anyone can see the home and posts page
                    .permitAll()
                /* Pages that require authentication */
                .and()
                    .authorizeRequests()
                    .antMatchers(
                            "/posts/create", //only authenticated users can create posts
                            "/update/{id}", // only authenticated users can edit posts
                            "/posts/{id}"
                    )
                    .authenticated()
                .and()
                    .authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasRole("ADMIN")
                ;
    }
}
