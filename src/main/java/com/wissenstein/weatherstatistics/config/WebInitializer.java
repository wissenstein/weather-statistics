package com.wissenstein.weatherstatistics.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(final ServletContext servletContext)
    throws ServletException {
        final AnnotationConfigWebApplicationContext appContext
                = new AnnotationConfigWebApplicationContext();
        appContext.register(Config.class);
        appContext.setServletContext(servletContext);

        final ServletRegistration.Dynamic dispatcher = servletContext
                .addServlet("dispatcher", new DispatcherServlet(appContext));
        dispatcher.addMapping("/");
        dispatcher.setLoadOnStartup(1);
    }
}
