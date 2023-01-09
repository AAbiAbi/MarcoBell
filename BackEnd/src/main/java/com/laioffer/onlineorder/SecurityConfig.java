package com.laioffer.onlineorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

//https://docs.spring.io/spring-security/site/docs/5.5.5/reference/html5/

@EnableWebSecurity
//通过spring创建出来
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //两个dependencies，一个明文密码，一个db里面decode暗纹密码

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;
    //encode在security里完成

    @Override

    protected void configure(HttpSecurity http) throws Exception {
        //登录时setup
        http
                .csrf().disable()
                //不允许csrf
                .formLogin()
                //成功
                //失败跳转页面
                .failureForwardUrl("/login?error=true");
        http
                .authorizeRequests()
                //
                .antMatchers("/order/*", "/cart", "/checkout").hasAuthority("ROLE_USER")
                //以上请求必须有authority
                .anyRequest().permitAll();//其余访问请求不需要authority
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //登录时的验证

        auth
                .jdbcAuthentication()//filter去连接数据库
                .dataSource(dataSource)//
                .passwordEncoder(passwordEncoder)//encode
                //实现两个query
                .usersByUsernameQuery("SELECT email, password, enabled FROM customers WHERE email=?")//通过sql执行搜索条件
                //问号是占位符，security会填充用户email（key），sql传值principle，传回 email，password，enabled存回自定义object
                .authoritiesByUsernameQuery("SELECT email, authorities FROM authorities WHERE email=?");
                //

    }
}
