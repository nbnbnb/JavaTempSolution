package basicconsoleapp.rmi.client

import basicconsoleapp.rmi.Spitter
import basicconsoleapp.rmi.SpitterService
import org.springframework.remoting.rmi.RmiProxyFactoryBean
import org.springframework.stereotype.Component


/**
 * Created by jinzhanga on 2018/8/9.
 */

@Component
class SpitterController constructor(val rmiService: RmiProxyFactoryBean) {
    fun getSpitter(id: Long): Spitter {

        val pp=rmiService.getObject()

        val spitterService = rmiService.getObject() as SpitterService
        return spitterService.getSpitter(id)
    }
}