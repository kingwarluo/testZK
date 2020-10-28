package com.设计模式.adapter.audio;

/**
 * VLC播放器
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("播放vlc媒体，" + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // do nothing
    }
}
