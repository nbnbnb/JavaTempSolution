package basicconsoleapp.proxy.mock


/**
 * Created by jinzhanga on 2018/8/3.
 */

@RefrenceFlag
interface MockInterface {
    fun dynamicMock(a: MockAClass): MockBClass
}
