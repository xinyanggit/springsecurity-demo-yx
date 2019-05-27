package com.yx.simpaledata.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author yx start
 * @create 2019/5/26,15:23
 * 配置相关类型
 *@EnableWebSecurity + WebSecurityConfigurerAdapter
 * 即可配置拦截什么URL、设置什么权限等安全控制
 *
 */
@Configurable
@EnableWebSecurity
public class MySpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 配置相关访问路径权限
     * @param http
     * @throws Exception
     *
     *  匹配 / 不需要权限
     *  匹配 "/yx" 及其以下所有路径，都需要 "yx_role" 权限
     *  登录url为 login ，登录成功默认跳转页面 /user
     *  登出URL 为logout .登出成功后跳转页面为login 页面
     *
     * ########################################################################
     * HttpSecurity对象
     *
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      //   http.openidLogin(); 给予openid的验证
      //   http.headers 将安全标头添加到响应
       http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/yx/**").hasRole("yx_role")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/yx/user")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }

    /**
     * 此方法构建权限对象
     *  1.内存存储->存储用户名和密码
     *  2.数据库获取 jdbc
     *
     *  出现异常
     *  There is no PasswordEncoder mapped for the id "null"
     *  需要对密码进行加密
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("yx").password(new BCryptPasswordEncoder().encode("111111")).roles("yx_role")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("111111")).roles("yx_role","admin");

     }



}
