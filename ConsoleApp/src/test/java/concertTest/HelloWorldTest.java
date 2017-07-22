package concertTest;

import concert.HelloWorld;
import concert.HelloWorldConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HelloWorldConfig.class)
public class HelloWorldTest {

    @Autowired()
    @Qualifier("helloWorldImpl1")
    private HelloWorld hw1;

    @Autowired
    @Qualifier("helloWorldImpl2")
    private HelloWorld hw2;

    @Test
    public void shouldOk() {

        hw1.printHelloWorld();
        System.out.println();
        hw1.doPrint();

        System.out.println();
        hw2.printHelloWorld();
        System.out.println();
        hw2.doPrint();
    }

}
