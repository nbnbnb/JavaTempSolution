package basicconsoleapp.demos

import basicconsoleapp.proxy.*


object KotlinDemo {
    fun test() {
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

