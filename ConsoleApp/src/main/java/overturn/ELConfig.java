package overturn;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.nio.charset.Charset;

@Configuration
@ComponentScan
@PropertySource("test.properties")
public class ELConfig {

    /**
     * 为了使用占位符， 我们必须要配置一个 PropertyPlaceholderConfigurer bean 或 PropertySourcesPlaceholderConfigurer bean
     * 从 Spring 3.1 开始， 推荐使用 PropertySourcesPlaceholderConfigurer， 因为它能够基于 Spring Environment 及其属性源来解析占位符
     */

    // 注入普通
    @Value("I Love You!")
    private String norma;

    // 注入操作系统属性
    @Value("#{systemProperties['os.name']}")
    private String osName;

    // 输入表达式运算结果
    @Value("#{T(java.lang.Math).random() * 100.0}")
    private double randomNumber;

    // 输入其他 Bean 的属性
    // Bean 名称默认为首字母小写，也可以自行指定
    @Value("#{demoService.another}")
    private String fromAnother;

    // 注入文件内容
    @Value("classpath:test.txt")
    private Resource testFile;

    // 注入网址内容
    @Value("http://www.baidu.com/")
    private Resource testUrl;

    // 注入属性内容
    @Value("${book.name}")
    private String bookName;

    // 注入环境变量
    @Autowired
    private Environment environment;

    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ELConfig.class);
        // Config 默认就是一个 Bean
        ELConfig resourceService = context.getBean(ELConfig.class);
        resourceService.outputResource();
        context.close();
    }

    public void outputResource() {
        try {
            System.out.println(norma);
            System.out.println(osName);
            System.out.println(randomNumber);
            System.out.println(fromAnother);
            System.out.println(IOUtils.toString(testFile.getInputStream(), Charset.forName("utf-8")));
            System.out.println(IOUtils.toString(testUrl.getInputStream(), Charset.forName("utf-8")));
            System.out.println(bookName);
            System.out.println(environment.getProperty("book.author"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
