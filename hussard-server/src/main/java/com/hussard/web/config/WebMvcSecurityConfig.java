package com.hussard.web.config;

import com.hussard.web.base.auth.service.CustomFilterInvocationSecurityMetadataSource;
import com.hussard.web.base.auth.service.CustomRoleVoter;
import com.hussard.web.base.auth.service.SecuredResourceService;
import com.hussard.web.base.user.service.LocaleResolvingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Matthew on 2015-06-08.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebMvcSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PersistentTokenRepository userTokenRepository;

    @Autowired
    private LocaleResolvingHandler localeResolvingHandler;

    @Autowired
    private SecuredResourceService securedResourceService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers("/settings/**")
                .antMatchers("/resource/**")
                .antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .formLogin()
                .loginPage("/auth/login").permitAll()
                .and()
            .addFilter(usernamePasswordAuthenticationFilter())
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .and()
            .exceptionHandling()
                .accessDeniedPage("/auth/c/denied")
                .and()
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .addFilterAfter(filterSecurityInterceptor(), FilterSecurityInterceptor.class)
            .httpBasic();

        http.rememberMe().rememberMeServices(rememberMeServices());
    }

    private Filter filterSecurityInterceptor() throws Exception {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setAuthenticationManager(authenticationManager());
        filterSecurityInterceptor.setSecurityMetadataSource(securityMetadataSource());
        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());
        return filterSecurityInterceptor;
    }

    @Bean
    public FilterInvocationSecurityMetadataSource securityMetadataSource() {
        return new CustomFilterInvocationSecurityMetadataSource(securedResourceService.getMetaDataSource());
    }

    @Bean
    public AffirmativeBased affirmativeBased() {
        RoleVoter voter = new CustomRoleVoter();
        voter.setRolePrefix("");
        List<AccessDecisionVoter<? extends Object>> voters = new ArrayList<>();
        voters.add(voter);
        AffirmativeBased affirmativeBased = new AffirmativeBased(voters);
        affirmativeBased.setAllowIfAllAbstainDecisions(false);
        return affirmativeBased;
    }

    @Bean
    public PersistentTokenBasedRememberMeServices rememberMeServices() {
        return new PersistentTokenBasedRememberMeServices("hussard", userDetailsService, userTokenRepository);
    }

    @Bean
    public Filter usernamePasswordAuthenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setRememberMeServices(rememberMeServices());
        filter.setAuthenticationSuccessHandler(localeResolvingHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());

        return filter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/auth/login?login_error");
    }
}
