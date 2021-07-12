package com.regitiny.catiny.config;

import com.regitiny.catiny.aop.AspectService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import tech.jhipster.config.JHipsterConstants;

@Configuration
@EnableAspectJAutoProxy
public class AspectServiceConfiguration
{
  @Bean
  @Profile(JHipsterConstants.SPRING_PROFILE_DEVELOPMENT)
  public AspectService aspectService(Environment env)
  {
    return new AspectService(env);
  }
}
