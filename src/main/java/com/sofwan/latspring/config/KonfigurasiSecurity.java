package com.sofwan.latspring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@EnableWebSecurity
public class KonfigurasiSecurity extends WebSecurityConfigurerAdapter {

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
                .antMatchers("/css/**", "/js/**","/login").permitAll()
                .antMatchers("/halo").hasAnyRole("ADMIN", "STAF")
                .antMatchers("/peserta/form").hasRole("ADMIN")
                .antMatchers("/peserta/list").hasAnyRole("ADMIN", "STAF")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/halo", false)
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .and()
                .addFilterAfter(new CsrfAttributeToCookieFilter(), CsrfFilter.class)
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and();
        //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
}
