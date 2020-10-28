package com.设计模式.adapter.audio;

/**
 * MP4播放器
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public class Mp4Player implements AdvancedMediaPlayer {

    @Override
    public void playVlc(String fileName) {
        // do nothing
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("播放MP4视频，" + fileName);
    }
}
