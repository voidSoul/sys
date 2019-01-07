package lym.cs.lengfeng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login/**").excludePathPatterns("/error");
        registry.addInterceptor(init()).addPathPatterns("/admin/**");
        super.addInterceptors(registry);
    }

    @Bean
    AdminInterceptor init() {
        return new AdminInterceptor();
    }
}
