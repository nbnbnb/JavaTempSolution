package sbconsoleapp

import org.springframework.boot.ApplicationArguments
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


/**
 * Created by jinzhanga on 2018/3/8.
 */

/**
 * ApplicationArguments 可以获取命令行信息，在运行是将会自动注入
 * boolean debug = args.containsOption("debug");
 * List<String> files = args.getNonOptionArgs();
 * 如果使用命令行参数运行 "--debug logfile.txt"
 * 则得到的结果为 debug=true, files=["logfile.txt"]
 */
@Component
class MyBean @Autowired constructor(args: ApplicationArguments) {
    init {
        // if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]

        @SuppressWarnings("UNUSED")
        val debug = args.containsOption("debug")

        @Suppress("unused")
        val files = args.nonOptionArgs
    }
}