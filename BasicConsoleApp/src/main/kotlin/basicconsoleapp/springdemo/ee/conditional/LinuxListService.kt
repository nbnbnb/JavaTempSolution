package basicconsoleapp.springdemo.ee.conditional

/**
 * Created by ZhangJin on 2018/6/3.
 */

class LinuxListService : ListService {
    override fun showListCmd(): String {
        return "ls"
    }
}