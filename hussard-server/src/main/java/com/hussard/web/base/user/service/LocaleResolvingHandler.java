package com.hussard.web.base.user.service;

import com.hussard.web.base.util.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Matthew on 2015-06-08.
 */
@Service("localeResolvingHandler")
public class LocaleResolvingHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private LocaleResolver localeResolver;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        AuthenticationUtils.setLocale(authentication, localeResolver, request, response);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
