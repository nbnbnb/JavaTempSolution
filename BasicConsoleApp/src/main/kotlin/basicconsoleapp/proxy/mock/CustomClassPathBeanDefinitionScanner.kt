package basicconsoleapp.proxy.mock

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition
import org.springframework.beans.factory.config.BeanDefinitionHolder
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import org.springframework.beans.factory.support.GenericBeanDefinition
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner
import org.springframework.core.type.filter.AnnotationTypeFilter


/**
 * Created by jinzhanga on 2018/8/3.
 */

class CustomClassPathBeanDefinitionScanner(registry: BeanDefinitionRegistry) : ClassPathBeanDefinitionScanner(registry) {
    override fun registerDefaultFilters() {
        // 接口上定义了特定的注解
        this.addIncludeFilter(AnnotationTypeFilter(RefrenceFlag::class.java))
    }

    override fun doScan(vararg basePackages: String): Set<BeanDefinitionHolder> {
        // 扫描包路径
        val beanDefinitions = super.doScan(*basePackages)

        for (holder in beanDefinitions) {

            val definition = holder.beanDefinition as GenericBeanDefinition

            // 设置一个 FactoryBean，通过它来创建具体的实现对象
            definition.setBeanClass(CustomFactoryBean::class.java)

            // 注册这个逻辑
            this.registry.registerBeanDefinition(holder.beanName, definition)

        }
        return beanDefinitions
    }

    override fun isCandidateComponent(beanDefinition: AnnotatedBeanDefinition): Boolean {
        return beanDefinition.metadata.isInterface && beanDefinition.metadata.isIndependent
    }

}