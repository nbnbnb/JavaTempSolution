package concert;


import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCounterSpliterator implements Spliterator<Character> {

    private static final String SENTENCE =
            " Nel mezzo del cammin di nostra vita " +
                    "mi ritrovai in una selva oscura" +
                    " ché la dritta via era smarrita ";

    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    private static int countWords(Stream<Character> stream) {

        System.out.println(stream.isParallel());

        WordCounter wordCounter =
                stream.reduce(
                        new WordCounter(0, true),
                        WordCounter::accumulate,
                        WordCounter::combine);

        return wordCounter.getCounter();
    }

    public static void main(String[] args) {
        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + countWords(stream) + " words");
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        // 处理当前字符
        action.accept(string.charAt(currentChar++));
        // 如果还有字符要处理，则返回 true
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {

        int currentSize = string.length() - currentChar;
        // 返回 null 表示要解析的 string 已经足够小，可以顺序处理
        if (currentSize < 10) {
            return null;
        }

        // 将试探拆分位置设定为要解析 String 的中间
        for (int splitPos = currentSize / 2 + currentSize; splitPos < string.length(); splitPos++) {

            // 让拆分位置前进直到下一个空格
            if (Character.isWhitespace(string.charAt(splitPos))) {
                // 创建一个新 WordCounterSpliterator 来解析 String 从开始到拆分位置的部分
                Spliterator<Character> spliterator = new WordCounterSpliterator(string.substring(currentChar, splitPos));

                // 将这个 WordCounterSpliterator 的起始位置设为拆分位置
                currentChar = splitPos;
                return spliterator;
            }
        }

        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }
}
