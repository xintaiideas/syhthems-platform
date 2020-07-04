package top.sunriseydy.syhthems.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import top.sunriseydy.syhthems.common.constants.BaseConstants;
import top.sunriseydy.syhthems.common.properties.CorsProperties;
import top.sunriseydy.syhthems.sso.handler.*;

/**
 * WebSecurity配置类
 *
 * @author dengmin
 * @date 2019-03-18 14:44
 */
@Configuration
@EnableWebSecurity
@Order(BaseConstants.DEFAULT_ORDER + 1)
public class SsoWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CorsProperties corsProperties;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/favicon.ico");
    }

    @Bean
    public CustomAuthenticationEntryPoint ssoCustomAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler ssoCustomAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationSuccessHandler ssoCustomAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler ssoCustomAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler ssoCustomLogoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Qualifier("customUserDetailsServiceImpl")
    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = corsProperties.toCorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/sso/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().configurationSource(corsConfigurationSource())
            .and()
                .headers()
                .httpStrictTransportSecurity().disable()
                .defaultsDisabled().cacheControl().and()
            .and()
                .authorizeRequests()
                    .antMatchers("/error",
                            "/sso/.well-known/*",
                            "/sso/oauth/token",
                            "/sso/register",
                            "/sso/login",
                            "/sso/user/check_user_name",
                            "/sso/user/check_user_email").permitAll()
                    .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/sso/login")
                .loginProcessingUrl("/sso/login")
                // .failureHandler(ssoCustomAuthenticationFailureHandler())
                // .successHandler(ssoCustomAuthenticationSuccessHandler())
                .permitAll()
            .and()
                .logout().logoutSuccessHandler(ssoCustomLogoutSuccessHandler()).logoutUrl("/sso/logout")
            .and()
                .csrf().disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
    }
}
