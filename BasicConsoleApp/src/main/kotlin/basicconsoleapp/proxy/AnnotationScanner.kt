package basicconsoleapp.proxy

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition
import org.springframework.beans.factory.config.BeanDefinitionHolder
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.GenericBeanDefinition
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner


/**
 * Created by jinzhanga on 2018/8/3.
 */

class AnnotationScanner(registry: BeanDefinitionRegistry) : ClassPathBeanDefinitionScanner(registry) {
    override fun registerDefaultFilters() {
        //扫描规则
        //        this.addIncludeFilter(new AnnotationTypeFilter(Refrence.class));
        this.addIncludeFilter { metadataReader, metadataReaderFactory -> true }
    }

    override fun doScan(vararg basePackages: String): Set<BeanDefinitionHolder> {
        val beanDefinitions = super.doScan(*basePackages)
        for (holder in beanDefinitions) {
            val definition = holder.beanDefinition as GenericBeanDefinition
            //BeanFactory.getBean的方法跟进去后有一个判断是不是FactroyBean类型的。如果是从FactroyBean.getObejct获取
            //RefrenceAnnotationFactoryBean 实现了FactoryBean
            definition.setBeanClass(RefrenceAnnotationFactoryBean::class.java)
            this.registry.registerBeanDefinition(holder.beanName, definition)
        }
        return beanDefinitions
    }

    override fun isCandidateComponent(beanDefinition: AnnotatedBeanDefinition): Boolean {
        return beanDefinition.metadata.isInterface && beanDefinition.metadata.isIndependent
    }

}