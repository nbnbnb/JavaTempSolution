package basicconsoleapp.springdemo.concert.aware

import org.springframework.beans.factory.BeanNameAware
import org.springframework.context.ResourceLoaderAware
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service
import org.springframework.util.StreamUtils
import java.io.IOException
import java.nio.charset.Charset

/**
 * Created by ZhangJin on 2018/6/3.
 */

@Service
class AwareService : BeanNameAware, ResourceLoaderAware {

    private lateinit var beanName: String
    private lateinit var loader: ResourceLoader

    override fun setBeanName(name: String) {
        this.beanName = name
    }

    override fun setResourceLoader(resourceLoader: ResourceLoader) {
        this.loader = resourceLoader
    }

    fun outputResult() {
        println("Bean 的名称为: $beanName")
        val resource = loader.getResource("test.txt")
        try {
            println("ResourceLoader 加载的文件内容为: " + StreamUtils.copyToString(resource.inputStream, Charsets.UTF_8))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}