package soundsystem;

import org.springframework.stereotype.Component;


// @Component
// 由于有构造函数，此处不能自动装配，所以注释掉 @Component 特性
public class BlankDisc implements CompactDisc {

    private String title;
    private String artist;


    public BlankDisc(String title, String artist) {
        this.title=title;
        this.artist = artist;
    }

    @Override
    public void play() {
        System.out.println("This is a Blank Disc");
    }
}
