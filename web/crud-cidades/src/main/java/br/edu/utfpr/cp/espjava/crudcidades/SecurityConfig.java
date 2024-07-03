package br.edu.utfpr.cp.espjava.crudcidades;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/").hasAnyAuthority("toList", "admin")
            .antMatchers("/create").hasAuthority("admin")
            .antMatchers("/delete").hasAuthority("admin")
            .antMatchers("/update").hasAuthority("admin")
            .antMatchers("/prepareUpdate").hasAuthority("admin")
            .antMatchers("/show").authenticated()
            .anyRequest().denyAll()
                .and()
            .formLogin()
            .loginPage("/login.html").permitAll()
            .defautSuccessUrl("/", false)
                .and()
            .logout().permitAll();
    }

    @Bean
    public PasswordEncoder cipher() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(InteractiveAuthenticationSuccessEvent.class)
    public void printUsuarioAtual(InteractiveAuthenticationSuccessEvent event) {
        var user = event.getAuthentication().getName();

        System.out.println(user);
    }
}
