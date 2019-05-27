package com.yx.demo.main.config;

import com.yx.demo.main.filter.MyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;

/**
 * @author yx start
 * @create 2019/5/26,15:23
 * 配置相关类型
 * @EnableWebSecurity + WebSecurityConfigurerAdapter
 * 即可配置拦截什么URL、设置什么权限等安全控制
 */
@Configurable
@EnableWebSecurity
public class MySpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     * 配置相关访问路径权限
     *
     * @param http
     * @throws Exception 匹配 / 不需要权限
     *                   匹配 "/yx" 及其以下所有路径，都需要 "yx_role" 权限
     *                   登录url为 login ，登录成功默认跳转页面 /user
     *                   登出URL 为logout .登出成功后跳转页面为login 页面
     *                   <p>
     *                   ########################################################################
     *                   HttpSecurity对象
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
        //  在 UsernamePasswordAuthenticationFilter 前添加 BeforeLoginFilter
        http.addFilterBefore(new MyFilter(), UsernamePasswordAuthenticationFilter.class);
        //在 SwitchUserFilter 后添加 BeforeLoginFilter
        http.addFilterAfter(new MyFilter(), SwitchUserFilter.class);

    }

    /**
     * 此方法构建权限对象
     * 1.内存存储->存储用户名和密码
     * 2.数据库获取 jdbc
     * <p>
     * 出现异常
     * There is no PasswordEncoder mapped for the id "null"
     * 需要对密码进行加密
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
        .passwordEncoder(passwordEncoder());
    }

    /**
     * 必须要 加密
     *
     * @return
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
