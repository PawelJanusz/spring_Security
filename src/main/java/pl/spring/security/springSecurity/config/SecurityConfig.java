package pl.spring.security.springSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
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
                .defaultSuccessUrl("/index");
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
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select u.login, u.password, 1 from login_user u where u.login = ?")
                .authoritiesByUsernameQuery("select u.login, u.role, 1 from login_user u where u.login = ?")
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }
}
