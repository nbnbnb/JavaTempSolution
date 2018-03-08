package sbconsoleapp

import org.springframework.boot.ApplicationArguments
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


/**
 * Created by jinzhanga on 2018/3/8.
 */

/**
 * ApplicationArguments 可以获取命令行信息
 * boolean debug = args.containsOption("debug");
 * List<String> files = args.getNonOptionArgs();
 * if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
 */

@Component
class MyBean @Autowired constructor(args: ApplicationArguments) {
    init {
        val debug = args.containsOption("debug")
        val files = args.nonOptionArgs
        // if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
    }
}