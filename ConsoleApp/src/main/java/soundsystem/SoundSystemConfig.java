package soundsystem;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Iterator;

@Configuration  // 表明这个类是一个配置类
@ComponentScan  // 用于自动扫描 Component 组件，用于自动创建 Bean
@ImportResource("classpath:cd-config.xml") // 导入 xml 配置项
@PropertySource("classpath:app.properties")
public class SoundSystemConfig {

    // 但不启动自动扫描时
    // 所有的 Bean 都需要在这里进行集中配置

    // 如果没有开启自动扫描 ComponentScan，那么 @Component 注释是没意义的

    /*
    @Bean
    public CompactDisc cdPlayer() {
        return new CDPlayer();
    }

    @Bean
    public MediaPlayer mdPlayer(CompactDisc cd) {
        return new MediaPlayer(cd);
    }
    */
}
