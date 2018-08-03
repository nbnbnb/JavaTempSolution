package basicconsoleapp.demos

import basicconsoleapp.proxy.*

/**
 * Created by jinzhanga on 2018/8/3.
 */

object ProxyDemo {
    fun run() {
        val jdkFacadeImpl = JDKFacadeImpl()
        val jdkFacadeProxy = JDKFacadeProxy()
        val jdkFacade = jdkFacadeProxy.bind(jdkFacadeImpl) as JDKFacade
        jdkFacade.addBook()

        println()

        val cglibFacadeImpl = CGlibFacadeImpl()
        val cglibFacadeImplProxy = CGlibFacadeImplProxy()
        val cgiFacade = cglibFacadeImplProxy.getInstance(cglibFacadeImpl) as CGlibFacadeImpl
        cgiFacade.addBook()
    }
}