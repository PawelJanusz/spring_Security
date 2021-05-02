package pl.spring.security.springSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/status")
                    .hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/", "/index", "/index/**")
                    .hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                .anyRequest()
                    .permitAll()
                .and()
                .csrf().disable()
                .formLogin()
                .defaultSuccessUrl("/status");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user@test.pl")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
            .and()
                .withUser("admin@test.pl")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN");
    }
}
