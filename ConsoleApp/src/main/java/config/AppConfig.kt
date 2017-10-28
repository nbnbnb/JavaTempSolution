package config

import SpringDemo.concert.IExecution
import SpringDemo.concert.MyExecution
import SpringDemo.concert.SheExecution
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@ComponentScan(basePackages = arrayOf("config", "demos", "SpringDemo.concert"))
@EnableAspectJAutoProxy   // 启动 AspectJ 自动代理
class AppConfig
