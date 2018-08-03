package basicconsoleapp.proxy.mock


/**
 * Created by jinzhanga on 2018/8/3.
 */

@RefrenceFlag
interface MockInterface {
    fun getName(name: String): String
}

@RefrenceFlag
interface MockInterface2 {
    fun getAddress(name: String): String
}