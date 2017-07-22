package soundsystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlankDisc implements CompactDisc {

    private String title;
    private String artist;


    public BlankDisc(@Value("${disc.title}") String title, // 使用 SpEL 方式
                     @Value("${disc.artist}") String artist) {   // 使用属性占位符方式
        this.title=title;
        this.artist = artist;
    }

    @Override
    public void play() {
        System.out.println("This is a Blank Disc");
        System.out.println("Playing " + title + " by " + artist);
    }
}
