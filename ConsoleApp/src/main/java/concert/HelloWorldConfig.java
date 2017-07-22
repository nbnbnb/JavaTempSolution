package concert;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration  // 表明这个类是一个配置类
@ImportResource("classpath:helloworld-config.xml") // 导入 xml 配置项
public class HelloWorldConfig {
}
