package com.设计模式.adapter.audio;

/**
 * 新式播放器 适配器
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
// 这里不能用类适配器的形式，因为先进的播放器有两种实现，MP4和VLC
//public class MediaPlayerAdapter extends Mp4Player implements Player {
//
//    @Override
//    public void play(String audioType, String fileName) {
//
//    }
//}
public class MediaPlayerAdapter implements Player {

    // 对象适配器，采用delegate委托来实现
    // 理解：新增了多种播放器形式，所以采用委托实现，不能使用类适配器
    // 当然，类适配器也可以抽象一下，可以用继承实现多态的类适配器
    AdvancedMediaPlayer advancedMusicPlayer;

    public MediaPlayerAdapter(String audioType) {
        if(audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer = new VlcPlayer();
        } else if(audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer = new Mp4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("vlc")) {
            advancedMusicPlayer.playVlc(fileName);
        } else if(audioType.equalsIgnoreCase("mp4")) {
            advancedMusicPlayer.playMp4(fileName);
        }
    }
}
