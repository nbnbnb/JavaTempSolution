package think;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import java.util.SortedMap;

public class NIODemos {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws Exception {
        largeMappedFiles();
    }

    public static void bufferToText() throws Exception {

        FileChannel fc = new FileOutputStream("data2.txt").getChannel();

        fc.write(ByteBuffer.wrap("Some text".getBytes())); // 默认写入编码为 UTF-8
        fc.close();

        fc = new FileInputStream("data2.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fc.read(buffer);
        buffer.flip();

        // Doesn't work
        System.out.println("Bad Encoding: " + buffer.asCharBuffer());

        // Decode using this system's default Charset;
        buffer.rewind();  // 将位置置到开头

        String encoding = System.getProperty("file.encoding");  // UTF-8
        System.out.println("Decoded using " + encoding + ": " + Charset.forName(encoding).decode(buffer));

        fc = new FileOutputStream("data2.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));  // 指定用 UTF-16BF 编码写入
        fc.close();

        fc = new FileInputStream("data2.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());  // 读取时，默认使用 UTF-16BE 进行解码

        // Use a CharBuffer to write through:
        fc = new FileOutputStream("data2.txt").getChannel();
        buffer = ByteBuffer.allocate(24); // More than needed
        buffer.asCharBuffer().put("Some text");  // 此方法的写入和读取保证一致 UTF-16BF
        fc.write(buffer);
        fc.close();

        // Read and display
        fc = new FileInputStream("data2.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());  // 最后三个字符为不可见字符

    }

    public static void getData() throws Exception {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);

        int i = 0;
        while (i++ < bb.limit()) {
            if (bb.get() != 0) {
                System.out.println("nonzero");
            }
        }

        System.out.println("i = " + i);
        bb.rewind();
        bb.asCharBuffer().put("Howdy!");
        char c;
        while ((c = bb.getChar()) != 0) {
            System.out.println(c + " ");
        }

        bb.rewind();

        bb.asShortBuffer().put((short) 471142);
        System.out.println(bb.getShort());

        bb.rewind();
        bb.asDoubleBuffer().put(93445834);
        System.out.println(bb.getDouble());

    }

    public static void intBufferDemo() {

        ByteBuffer bb = ByteBuffer.allocate(BSIZE);

        IntBuffer ib = bb.asIntBuffer();

        ib.put(new int[]{11, 42, 47, 99, 143, 811, 1016});

        System.out.println(ib.get(3));  // 99

        ib.put(3, 1811);  // 替换索引 3 处的 int 值
        ib.flip();

        while (ib.hasRemaining()) {
            int i = ib.get();
            System.out.println(i);
        }
    }

    public static void endians() throws Exception {

        ByteBuffer bb = ByteBuffer.wrap(new byte[12]);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));
        bb.rewind();
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));
        bb.rewind();
        bb.order(ByteOrder.LITTLE_ENDIAN);
        bb.asCharBuffer().put("abcdef");
        System.out.println(Arrays.toString(bb.array()));
    }

    public static void largeMappedFiles() throws Exception {

        int length = 0x8FFFFFF; // 128 MB

        MappedByteBuffer out = new RandomAccessFile("test.data", "rw")
                .getChannel()
                .map(FileChannel.MapMode.READ_WRITE, 0, length);

        for (int i = 0; i < length; i++) {
            out.put((byte) 'x');
        }

        System.out.println("Finished writing");

        for (int i = length / 2; i < length / 2 + 6; i++) {
            System.out.print((char) out.get(i));
        }

    }
}
