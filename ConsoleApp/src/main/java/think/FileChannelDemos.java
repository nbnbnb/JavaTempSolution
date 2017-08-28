package think;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemos {

    private static final int BSIZE = 1024;

    public static void main(String[] args) throws Exception {
        transferTo();
    }

    public static void getChannel() throws Exception {
        FileChannel fc = new FileOutputStream("data.txt").getChannel();

        fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        fc.close();

        fc = new RandomAccessFile("data.txt", "rw").getChannel();
        fc.position(fc.size());  // Move to end
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();

        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);


        fc.read(buffer);
        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.print((char) buffer.get());
        }
    }

    public static void channelCopy() throws Exception {
        FileChannel in = new FileInputStream("data.txt").getChannel();
        FileChannel out = new FileOutputStream("data.copy.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);

        while (in.read(buffer) != -1) {
            buffer.flip();  // Prepare for writing
            out.write(buffer);
            buffer.clear(); // prepare for reading
        }
    }

    public static void transferTo() throws Exception {
        FileChannel in = new FileInputStream("data.txt").getChannel();
        FileChannel out = new FileOutputStream("data.copy.txt").getChannel();

        in.transferTo(0, in.size(), out);

        // or
        //out.transferFrom(in, 0, in.size());
    }
}
