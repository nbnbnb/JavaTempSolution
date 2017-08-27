package think;

import java.io.*;

public class IODemos {

    public static void main(String[] args) throws IOException {
        usingRandomAccessFile();
    }

    /**
     * 基本的文件输出
     *
     * @throws IOException
     */
    public static void basicFileOutput() throws IOException {

        String file = "BasicFileOutput.out";

        BufferedReader in = new BufferedReader(new StringReader(read("h:/log.txt")));

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)));

        int lineCount = 1;

        String s;

        while ((s = in.readLine()) != null) {

            out.println(lineCount++ + ": " + s);
        }

        out.close();

        System.out.println(read(file));
    }

    /**
     * 格式化的内存输入
     *
     * @throws IOException
     */
    public static void formattedMemoryInput() throws IOException {
        try {
            DataInputStream in = new DataInputStream(
                    new ByteArrayInputStream(
                            read("h:/log.txt").getBytes()));

            while (true) {
                System.out.print((char) in.readByte());
            }
        } catch (EOFException e) {  // 每次都会触发这个异常
            System.err.println("End of stream");
        }

        // 为了不抛出 EOFException，可以使用 available() 方法查看还有多少可供存储字符
        DataInputStream in = new DataInputStream(
                new ByteArrayInputStream(
                        read("h:/log.txt").getBytes()));

        while (in.available() != 0) {
            System.out.print((char) in.readByte());
        }

        /**
         * 注意，available() 方法的工作方式会随着所读取的媒介类型的不同而有所不同
         * 字面意思就是“在没有阻塞的情况下所能读取的字节数”
         * 对于文件，这意味着整个文件，但是对于不同类型的流，可能就不是这样的，因此要谨慎使用
         */
    }

    /**
     * 从内存输入
     *
     * @throws IOException
     */
    public static void memoryInput() throws IOException {
        StringReader in = new StringReader(read("h:/log.txt"));
        int c;
        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }
    }

    /**
     * 文本文件输出的快捷方式
     *
     * @throws IOException
     */
    public static void fileOutputShortcut() throws IOException {

        String file = "FileOutputShortcut.out";

        BufferedReader in = new BufferedReader(new StringReader(read("h:/log.txt")));

        // Java SE5 在 PrintWriter 中添加了一个辅助构造器，
        // 使得你不必在每次希望创建文本文件并向其写入时，都去执行所有的装饰工作
        // 仍旧是使用了缓存，只是不必自己去实现
        PrintWriter out = new PrintWriter(file);

        int lineCount = 1;

        String s;

        while ((s = in.readLine()) != null) {
            out.println(lineCount++ + ": " + s);
        }

        out.close();

        System.out.println(read(file));
    }


    /**
     * 缓冲输入文件
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static String read(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));

        String s;

        StringBuilder sb = new StringBuilder();

        while ((s = in.readLine()) != null) {
            sb.append(s + System.lineSeparator());
        }

        in.close();

        return sb.toString();
    }

    public static void storingAndRecoveringData() throws IOException {
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("Data.txt")));

        out.writeDouble(3.14159);
        out.writeUTF("张进");
        out.writeDouble(1.4777);
        out.writeUTF("Hello World!");
        out.close();

        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("Data.txt")));
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());

        /**
         * writeUTF( ) 和 readUTF( ) 使用的是适合于 Java 的 UTF-8 变体
         * 因此如果我们用一个非 Java 程序读取用 writeUTF() 所写的字符串时，必须编写一些特殊代码才能正确读取字符串
         */
    }

    public static void usingRandomAccessFile() throws IOException {

        String file = "rtest.dat";

        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        for (int i = 0; i < 7; i++) {
            rf.writeDouble(i * 1.414);
        }
        rf.writeUTF("The end of the file");
        rf.close();
        dispaly();

        rf = new RandomAccessFile(file, "rw");
        rf.seek(5 * 8);  // 定位到第 5 个 double 值内存的地方
        rf.writeDouble(47.0006); // 将第 5 个位置的值改写
        rf.close();
        dispaly();

    }

    public static void dispaly() throws IOException {
        String file = "rtest.dat";

        RandomAccessFile rf = new RandomAccessFile(file, "r");

        for (int i = 0; i < 7; i++) {
            System.out.println("Value " + i + ": " + rf.readDouble());
        }

        System.out.println(rf.readUTF());

        rf.close();
    }

}
