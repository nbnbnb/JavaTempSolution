package soundsystem;

import java.util.List;

// 不设置 Component
// 这样不使用自动化组件扫描的方式进行装配
// 而是使用 xml 中的配置进行 bean 的装配
public class QQPlayer implements CompactDisc {

    private String title;
    private String artist;
    private List<String> tracks;

    public QQPlayer(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);

        for (String track : tracks) {
            System.out.println("-Track: " + track);
        }
    }
}
