package be.kdg.freeflow.model.menus;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Sound {
    private static double volume = 1;

    private static Path soundPath = Paths.get("resources/audio/button_click.wav");
    private static Media media = new Media(new File(soundPath.toString()).toURI().toString());
    private static MediaPlayer player = new MediaPlayer(media);

    public static void play() {
        player.seek(Duration.ZERO);
        player.play();
    }

    public static void setVolume(double volume) {
        Sound.player.setVolume(volume);
    }


}