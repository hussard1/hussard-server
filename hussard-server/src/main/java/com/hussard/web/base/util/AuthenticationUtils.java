package com.hussard.web.base.util;

import com.hussard.web.base.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by Matthew on 2015-06-08.
 */
public class AuthenticationUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationUtils.class);

    private AuthenticationUtils() {
        // empty cause this is static class
    }

    public static User getUser() throws AuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                return (User) principal;
            }
        }

        return null;
    }

    public static void setLocale(Authentication authentication, LocaleResolver localeResolver, HttpServletRequest request, HttpServletResponse response) {
        if (authentication != null) {
            User user = getUser();

            if (user != null) {
                Locale locale = new Locale(user.getLanguage().toString(), user.getCountry().toString());
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("User locale = {}", locale.toString());
                }

                // save in cookie
                localeResolver.setLocale(request, response, locale);
            }
        }
    }
}
