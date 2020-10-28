package com.设计模式.adapter.audio;

/**
 * 老式播放器，兼容新式媒体播放器
 *
 * @author jianhua.luo
 * @date 2020/10/28
 */
public class AudioPlayer implements Player {

    MediaPlayerAdapter mediaPlayerAdapter;

    /**
     * 方法说明：
     * 第一版：实现了Player类，只支持mp3音乐播放
     * 第二版：加入支持mp4和vlc格式，添加新式播放器
     * @param audioType
     * @param fileName
     */
    @Override
    public void play(String audioType, String fileName) {
        // 播放 mp3 音乐文件的内置支持
        if(audioType.equalsIgnoreCase("mp3")) {
            System.out.println("播放mp3文件，名字：" + fileName);
        //mediaAdapter 提供了播放其他文件格式的支持
        } else if(audioType.equalsIgnoreCase("mp4")
            || audioType.equalsIgnoreCase("vlc")) {
            mediaPlayerAdapter = new MediaPlayerAdapter(audioType);
            mediaPlayerAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. "+
                    audioType + " format not supported");
        }
    }
}
