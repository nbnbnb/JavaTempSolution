package basicconsoleapp.proxy

/**
 * Created by jinzhanga on 2018/8/3.
 */

@RefrenceFlag
interface BizService {
    fun getName(name: String): String
}