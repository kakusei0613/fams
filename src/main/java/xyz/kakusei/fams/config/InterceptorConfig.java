package xyz.kakusei.fams.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import xyz.kakusei.fams.interceptor.CheckLoginInterceptor;
import xyz.kakusei.fams.interceptor.CheckPermissionInterceptor;

import java.util.Locale;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/assets/**","/login");
        registry.addInterceptor(new CheckPermissionInterceptor()).addPathPatterns("/**").excludePathPatterns("/assets/**","/login");
    }
}
