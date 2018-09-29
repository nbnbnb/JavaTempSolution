package basicconsoleapp.demos;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class IoCDemos {

    public static void Run() {
        annotationIoC();
        hardCodeIoC();
        propertiesIoc();
        xmlIoc();
    }


    private static void annotationIoC() {
        ApplicationContext container = new ClassPathXmlApplicationContext("annotation-config.xml");
        FXNewsProvider newsProvider = (FXNewsProvider) container.getBean("FXNewsProvider"); // 注意，此处的默认命名
        newsProvider.show();
    }

    /**
     * 直接编码绑定方式
     */
    private static void hardCodeIoC() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanFactory container = bindViaCode(beanFactory);
        FXNewsProvider newsProvider = (FXNewsProvider) container.getBean("djNewsProvider");
        newsProvider.show();
    }

    private static void propertiesIoc() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanFactory container = bindViaProperties(beanFactory);
        FXNewsProvider newsProvider = (FXNewsProvider) container.getBean("djNewsProvider");
        newsProvider.show();
    }

    private static void xmlIoc() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanFactory container = bindViaXMLFile(beanFactory);
        FXNewsProvider newsProvider = (FXNewsProvider) container.getBean("djNewsProvider");
        newsProvider.show();
    }

    private static BeanFactory bindViaXMLFile(BeanDefinitionRegistry registry) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(registry);
        reader.loadBeanDefinitions("classpath:binding-config-dtd.xml");
        return (BeanFactory) registry;
    }

    private static BeanFactory bindViaProperties(BeanDefinitionRegistry registry) {
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(registry);
        reader.loadBeanDefinitions("classpath:binding-config.properties");
        return (BeanFactory) registry;
    }

    private static BeanFactory bindViaCode(BeanDefinitionRegistry registry) {

        AbstractBeanDefinition newsProvider = new RootBeanDefinition(FXNewsProvider.class, null);
        AbstractBeanDefinition newsListener = new RootBeanDefinition(DowJonesNewsListener.class, null);
        AbstractBeanDefinition newsPersister = new RootBeanDefinition(DowJonesNewsPersister.class, null);

        registry.registerBeanDefinition("djNewsProvider", newsProvider);
        registry.registerBeanDefinition("djListener", newsListener);
        registry.registerBeanDefinition("djPersister", newsPersister);

        ConstructorArgumentValues argumentValues = new ConstructorArgumentValues();
        argumentValues.addIndexedArgumentValue(0, newsListener);
        argumentValues.addIndexedArgumentValue(1, newsPersister);
        newsProvider.setConstructorArgumentValues(argumentValues);

        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("newsListener", newsListener));
        propertyValues.addPropertyValue(new PropertyValue("newsPersister", newsPersister));
        newsProvider.setPropertyValues(propertyValues);

        return (BeanFactory) registry;
    }


}

//region 基础类型

interface IFXNewsListener {
    void sayListener();
}

interface IFXNewsPersister {
    void sayPersister();
}

@Component
class DowJonesNewsPersister implements IFXNewsPersister {

    @Override
    public void sayPersister() {
        System.out.println("Persister");
    }
}

@Component
class DowJonesNewsListener implements IFXNewsListener {

    @Override
    public void sayListener() {
        System.out.println("Listener");
    }
}

@Component
class FXNewsProvider {

    @Autowired
    private IFXNewsPersister newsPersister;
    @Autowired
    private IFXNewsListener newsListener;

    public FXNewsProvider() {

    }

    public FXNewsProvider(IFXNewsListener newsListener, IFXNewsPersister newsPersister) {
        this.newsListener = newsListener;
        this.newsPersister = newsPersister;
    }

    public IFXNewsListener getNewsListener() {
        return newsListener;
    }

    public void setNewsListener(IFXNewsListener newsListener) {
        this.newsListener = newsListener;
    }

    public IFXNewsPersister getNewsPersister() {
        return newsPersister;
    }

    public void setNewsPersister(IFXNewsPersister newsPersister) {
        this.newsPersister = newsPersister;
    }

    void show() {
        System.out.println(newsListener);
        System.out.println(newsPersister);
        newsListener.sayListener();
        newsPersister.sayPersister();
    }
}

//endregion