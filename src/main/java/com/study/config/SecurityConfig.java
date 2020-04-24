package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;

/**
 * @author guanpeng
 * @description Security配置类
 * @date 2020/4/23 10:12 上午
 * @since
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * 配置http安全
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated().and()
                // 关闭csrf跨站伪造攻击拦截
                .csrf().disable()
                .formLogin().loginPage("/toLogin").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/login").defaultSuccessUrl("/index").permitAll()
                .and()
                .logout().logoutSuccessUrl("/toLogout").invalidateHttpSession(true).clearAuthentication(true).permitAll()
        ;
    }

    /**
     * 配置用户管理
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(s -> new UserDetails() {
            /**
             * 拥有的权限
             * @return
             */
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }

            /**
             * 密码
             * @return
             */
            @Override
            public String getPassword() {
                return "admin";
            }

            /**
             * 用户名
             * @return
             */
            @Override
            public String getUsername() {
                return "admin";
            }

            /**
             * 账户是否过期
             * @return
             */
            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            /**
             * 账户是否被锁
             * @return
             */
            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            /**
             * 登录凭证是否过期
             * @return
             */
            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            /**
             * 账户是否启用
             * @return
             */
            @Override
            public boolean isEnabled() {
                return true;
            }
        });
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
//        super.configure(auth);
    }

    /**
     * 配置web安全
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
