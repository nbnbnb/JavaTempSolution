package basicconsoleapp.proxy.mock

import basicconsoleapp.proxy.mock.RefrenceFlag

/**
 * Created by jinzhanga on 2018/8/3.
 */

@RefrenceFlag
interface BizService {
    fun getName(name: String): String
}