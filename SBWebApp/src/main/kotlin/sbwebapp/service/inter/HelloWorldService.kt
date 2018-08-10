package sbwebapp.service.inter

import javax.jws.WebService

/**
 * Created by jinzhanga on 2018/8/10.
 */

@WebService(targetNamespace = "http://www.zhangjin.me/inter", name = "HelloWorldServiceInterface")
interface HelloWorldService {

    fun sayHello(name: String): String
}