package think;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.BitSet;
import java.util.Random;

public class Bits {

    /**
     * BitSet 的最小容量是 long：64 位
     *
     * @param bitSet
     */

    public static void printBitSet(BitSet bitSet) {
        System.out.println("bits: " + bitSet);  // 输出值表示哪一位被设置为 1，其余的都是 0
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < bitSet.size(); j++) {
            builder.append(bitSet.get(j) ? "1" : "0");
        }

        System.out.println("bit pattern: " + builder);
    }

    public static void main(String[] args) {
        Random random = new Random(47);
        byte bt = (byte) random.nextInt();
        BitSet bb = new BitSet();
        for (int i = 7; i >= 0; i--) {
            if (((1 << i) & bt) != 0) {
                bb.set(i);
            } else {
                bb.clear(i);
            }
        }
        System.out.println("byte value: " + bt);
        printBitSet(bb);

        short st = (short) random.nextInt();
        BitSet bs = new BitSet();
        for (int i = 15; i >= 0; i--) {
            if (((1 << i) & st) != 0) {
                bs.set(i);
            } else {
                bs.clear(i);
            }
        }
        System.out.println("short value: " + st);
        printBitSet(bs);

        int it = random.nextInt();
        BitSet bi = new BitSet();
        for (int i = 31; i >= 0; i--) {
            if (((1 << i) & it) != 0) {
                bi.set(i);
            } else {
                bi.clear(i);
            }
        }
        System.out.println("int value: " + it);
        printBitSet(bi);

        // Test bitsets >=64 bits
        BitSet b127 = new BitSet();
        b127.set(127);
        System.out.println("set bit 127: " + b127);

        BitSet b255 = new BitSet(65);
        b255.set(255);
        System.out.println("set bit 255: " + b255);

        BitSet b1023 = new BitSet(512);
        b1023.set(1023);
        b1023.set(1024);
        System.out.println("set bit 1023 & 1024: " + b1023);
    }
}
