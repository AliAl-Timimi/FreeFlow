package be.kdg.freeflow.model.menus;

import javafx.scene.media.AudioClip;

public class Sound {
    private static double volume = 1;
    private static final AudioClip player = new AudioClip("file:/resources/audio/button_click.wav");

    public static void play() {
        player.play(volume);
    }

    public static void setVolume(double value){
        volume = value;
    }
}