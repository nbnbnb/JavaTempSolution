package config;

import concert.Audience;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"config", "demos", "concert"})
@EnableAspectJAutoProxy   // 启动 AspectJ 自动代理
public class AppConfig {

}
