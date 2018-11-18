package cn.lsd.monitor;

import de.codecentric.boot.admin.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
@EnableTurbineStream
public class MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }

    @Configuration
    public static class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.formLogin()
                    .loginPage("/login.html").loginProcessingUrl("/login").permitAll()
                    .and()
                    .logout().logoutUrl("/logout")
                    .and()
                    .authorizeRequests()
                    //允许请求的登录页面和静态资源
                    .antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**")
                    .permitAll()
                    .and()
                    //任何其他请求都需要被授权
                    .authorizeRequests().antMatchers("/**").authenticated()
                    .and()
                    .httpBasic()
                    .and().csrf().disable();
        }
    }
}