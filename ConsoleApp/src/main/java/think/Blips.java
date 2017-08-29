package think;


import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;

class Blip1 implements Externalizable {
    public Blip1() {
        System.out.println("Blip1 Constructor");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip1.readExternal");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.readExternal");
    }
}

class Blip2 implements Externalizable {
    public Blip2() {
        System.out.println("Blip2 Constructor");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip2.readExternal");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip2.readExternal");
    }
}

public class Blips {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Constructor objects:");
        Blip1 b1 = new Blip1();
        Blip2 b2 = new Blip2();
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blips.out"));
        System.out.println("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.out"));
        System.out.println("Recovering b1:");
        b1 = (Blip1) in.readObject();

        System.out.println("Recovering b2:");
        b2 = (Blip2) in.readObject();
    }
}
