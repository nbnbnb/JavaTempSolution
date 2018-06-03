package basicconsoleapp.springdemo.ee.conditional

/**
 * Created by ZhangJin on 2018/6/3.
 */

class WindowsListService : ListService {
    override fun showListCmd(): String {
        return "dir"
    }
}