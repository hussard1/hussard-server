package com.hussard.web.config;

import com.hussard.web.filter.SimpleCORSFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by Matthew on 2015-06-09.
 */
public class FrontWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ApplicationConfig.class);

        container.addListener(new ContextLoaderListener(applicationContext));

        FilterRegistration encodingFilter = container.addFilter("encodingFilter", CharacterEncodingFilter.class);
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");

        FilterRegistration corsFilter = container.addFilter("SimpleCORSFilter", SimpleCORSFilter.class);
        corsFilter.addMappingForUrlPatterns(null, false, "/*");

        FilterRegistration springSecurityFilterChain = container.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"));
        springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");

        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(WebApplicationConfig.class);

        ServletRegistration.Dynamic dispatcher = container.addServlet("hussard", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }
}
