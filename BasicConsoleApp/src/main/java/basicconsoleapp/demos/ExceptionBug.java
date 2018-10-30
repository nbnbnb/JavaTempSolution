package basicconsoleapp.demos;

/**
 * 与 .NET 有非常大的差异
 * 能够在 finally 中重新赋值返回值和异常，这个非常容易导致 Bug
 */
public class ExceptionBug {
    static void m1() throws Exception {
        LostMessage lm = new LostMessage();
        try {
            lm.f();  // .NET 抛出的是这个异常
        } finally {
            lm.dispose();   // Java 抛出的是这个异常
        }
    }

    static void m2() {
        TestException testException = new TestException();
        try {
            testException.testEx();
        } catch (Exception e) {
            e.printStackTrace();  // 最底层的异常最终不会抛出
        }
    }
}

class VeryImportantException extends Exception {
    public String toString() {
        return "A very important exception";
    }
}

class HoHumException extends Exception {
    public String toString() {
        return "A trivial exception";
    }
}

class LostMessage {
    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }
}

class TestException {
    public TestException() {

    }

    boolean testEx() {
        boolean ret = true;
        try {
            // 没有了异常
            // 直接走 finally
            ret = textEx1();
        } catch (Exception e) {
            System.out.println("testEx, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("textEx, finally; return value=" + ret);
            return ret;
        }
    }

    boolean textEx1() {
        boolean ret = true;
        try {
            ret = textEx2();
            // 此处 ret=false，满足条件，直接走到 finally，屏蔽了底层的异常
            if (!ret) {
                return false;
            }
            System.out.println("textEx1, at the end of try");
            return ret;
        } catch (Exception e) {
            System.out.println("textEx1, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("textEx1, finally; return value=" + ret);
            // finally 中重新赋值返回值
            return ret;
        }
    }

    boolean textEx2() {
        boolean ret = true;
        try {
            int a = 1;
            int b = 0;
            int c = a / b;  // 抛出异常
            System.out.println("c is " + c);
            return true;
        } catch (Exception e) {
            System.out.println("textEx2, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("textEx2, finallly: return value=" + ret);
            // Java 中支持在 finally 中 return
            // 这个赋值最终被返回（可以覆盖 try 和 catch 块中的 return）
            // .NET 中是不支持 finally 中的 return
            return ret;
        }
    }
}