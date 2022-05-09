package xyz.kakusei.fams.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.kakusei.fams.interceptor.CheckLoginInterceptor;
import xyz.kakusei.fams.interceptor.CheckPermissionInterceptor;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CheckLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/assets/**","/login");
        registry.addInterceptor(new CheckPermissionInterceptor()).addPathPatterns("/**").excludePathPatterns("/assets/**","/login");
    }
}
