package basicconsoleapp.proxy.mock

import org.slf4j.LoggerFactory
import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component


/**
 * Created by jinzhanga on 2018/8/3.
 */

@Component
class AnnotationScannerConfigurer : ApplicationContextAware, BeanDefinitionRegistryPostProcessor {

    private var applicationContext: ApplicationContext? = null

    private val LOGGER = LoggerFactory.getLogger(AnnotationScannerConfigurer::class.java)


    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    @Throws(BeansException::class)
    override fun postProcessBeanFactory(beanFactory: ConfigurableListableBeanFactory) {
        LOGGER.info("postProcessBeanFactory() beanDefinition的个数=====>" + beanFactory.beanDefinitionCount)
    }

    @Throws(BeansException::class)
    override fun postProcessBeanDefinitionRegistry(registry: BeanDefinitionRegistry) {
        LOGGER.info("postProcessBeanDefinitionRegistry() beanDefinitionName=====>" + registry.beanDefinitionNames.toString())
        // 需要被代理的接口
        val annotationScanner = AnnotationScanner(registry)
        annotationScanner.setResourceLoader(applicationContext)
        // "com.pepsi.annotationproxy.service"是我 接口所在的包
        annotationScanner.scan("com.pepsi.annotationproxy.service")

    }
}