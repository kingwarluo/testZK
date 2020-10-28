package com.设计模式.adapter.audio;

/**
 * 新式播放器--需要兼容旧播放器
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public interface AdvancedMediaPlayer {

    void playVlc(String fileName);

    void playMp4(String fileName);

}
