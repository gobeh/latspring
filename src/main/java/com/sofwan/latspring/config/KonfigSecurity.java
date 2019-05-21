package com.sofwan.latspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@EnableWebSecurity
public class KonfigSecurity extends WebSecurityConfigurerAdapter {

    private static final String SQL_LOGIN
            = "select username, password, active as enabled "
            + "from s_users where username= ?";
    private static final String SQL_PERMISSION
            = "select u.username, r.nama as authority "
            + "from s_users u join s_users_roles ur on u.id = ur.id_users "
            + "join s_roles r on ur.id_roles = r.id "
            + "where u.username = ?";

    @Autowired
    DataSource ds;

    @Autowired
    public void konfigurGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(ds)
                .usersByUsernameQuery(SQL_LOGIN)
                .authoritiesByUsernameQuery(SQL_PERMISSION);

        //  .inMemoryAuthentication()
        //  .withUser("alan")
        //  .password("{noop}123")
        //  .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/halo", false)
                .and()
                .logout();
    }
}
