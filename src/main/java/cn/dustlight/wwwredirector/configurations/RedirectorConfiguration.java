package cn.dustlight.wwwredirector.configurations;

import cn.dustlight.wwwredirector.core.DefaultRedirector;
import cn.dustlight.wwwredirector.core.Redirector;
import cn.dustlight.wwwredirector.filters.RedirectFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
public class RedirectorConfiguration implements WebFluxConfigurer {

    @Bean
    @ConditionalOnMissingBean(Redirector.class)
    public DefaultRedirector defaultRedirector(){
        return new DefaultRedirector();
    }

    @Bean
    public RedirectFilter redirectFilter(@Autowired Redirector redirector){
        return new RedirectFilter(redirector);
    }
}
