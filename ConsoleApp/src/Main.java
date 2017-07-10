import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * Created by ZhangJin on 2017/7/8.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("----- start -----");
        Demo();
        System.out.println("-----  end  -----");
    }

    public static void Demo() {

        try {

            RandomAccessFile out = new RandomAccessFile("g:\\abc.txt", "rw");
            out.writeInt(1234);


            RandomAccessFile in = new RandomAccessFile("g:\\abc.txt", "rw");

            int abc = in.readInt();

            System.out.println(abc);

            in.seek(0);

            byte bbb = in.readByte();
            System.out.println(bbb);
            bbb = in.readByte();
            System.out.println(bbb);
            bbb = in.readByte();
            System.out.println(bbb);
            bbb = in.readByte();
            System.out.println(bbb);

        } catch (Exception ex) {

            ex.printStackTrace();
        }


    }

    public static void jdbcDemo() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost\\SQLExpress;databaseName=JavaTempSolution;IntegratedSecurity=True";
        Connection con = DriverManager.getConnection(url);
        con.close();
    }
}


class ABC implements Serializable {
    public void readExternal(ObjectInput input) {

    }
}

class Orientation {

    public static final Orientation HORIZONTAL = new Orientation(1);
    public static final Orientation VERTICAL = new Orientation(2);

    private int value;

    private Orientation(int v) {
        this.value = v;
    }

    protected Object readResolve() throws ObjectStreamException {
        if (value == 1) {
            return Orientation.HORIZONTAL;
        }
        if (value == 2) {
            return Orientation.VERTICAL;
        }
        return null;
    }
}

