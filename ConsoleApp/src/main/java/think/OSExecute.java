package think;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.Charset;

public class OSExecute {

    public static void command(String command) {
        boolean err = false;

        // 由于 CMD 的编码问题和操作系统相关
        // 此处需要显式设置为 GBK
        // 默认为 UTF-8

        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream(), Charset.forName("GBK")));
            String s;
            while ((s = results.readLine()) != null) {
                System.out.println(s);
            }
            BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream(), Charset.forName("GBK")));

            while ((s = errors.readLine()) != null) {
                System.out.println(s);
                err = true;
            }
        } catch (Exception e) {

            // cmd /c {string}
            // 执行 {string} 指定的命令，然后停止

            if (!command.startsWith("CMD /C")) {
                command("CMD /C " + command);
            } else {
                throw new RuntimeException(e);
            }
        }

        if (err) {
            System.out.println("Process error.");
        }
    }

    public static void main(String[] args) {
        command("ipconfig");
    }

}
