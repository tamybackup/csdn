package com.tamy.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //首页所有人可以访问，功能页只有对应权限的人才能访问
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限默认会跳转到登录页面
        http.formLogin().loginPage("/login");

        http.logout().logoutSuccessUrl("/");

        //防止网站攻击：post   get
        http.csrf().disable();  //关闭csrf功能，注销失败存在的原因，默认是开启的

        //firefox失败，不能成功保存cookie，关闭浏览器，cookie自动删除
        http.rememberMe().rememberMeParameter("remember");
    }

//    密码编码

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这些数据应该从数据库中读取，现在是在内存中模拟账号
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user").password(new BCryptPasswordEncoder().encode("123")).roles("vip1","vip2")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("vip1","vip2","vip3");
    }
}
