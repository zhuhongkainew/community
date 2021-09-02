package life.zhk.community.Interception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private SessionInterceptor sessionInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(new LocaleInterceptor());
        System.out.println("测试重启");
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
                //.excludePathPatterns("/");
                //.excludePathPatterns("/admin/**");
       // registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
    }
}