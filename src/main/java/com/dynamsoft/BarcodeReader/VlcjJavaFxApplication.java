package com.dynamsoft.BarcodeReader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.base.MediaPlayer;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurface;
/**
 *
 */
public class VlcjJavaFxApplication extends Application {

    private MediaPlayerFactory mediaPlayerFactory;

    private EmbeddedMediaPlayer embeddedMediaPlayer;

    private ImageView videoImageView;
    public Stage stage;

    public VlcjJavaFxApplication() {
        mediaPlayerFactory = new MediaPlayerFactory();
        embeddedMediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();
        this.embeddedMediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void playing(MediaPlayer mediaPlayer) {
            }

            @Override
            public void paused(MediaPlayer mediaPlayer) {
            }

            @Override
            public void stopped(MediaPlayer mediaPlayer) {
            }

            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
            }
        });
    }

    @Override
    public final void start(Stage primaryStage) throws Exception {
    	stage=primaryStage;
        videoImageView = new ImageView();
        videoImageView.setPreserveRatio(true);
        embeddedMediaPlayer.videoSurface().set(new ImageViewVideoSurface(videoImageView));
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black;");

        videoImageView.fitWidthProperty().bind(root.widthProperty());
        videoImageView.fitHeightProperty().bind(root.heightProperty());

        root.setCenter(videoImageView);

        Scene scene = new Scene(root, 1200, 675, Color.BLACK);
        primaryStage.setTitle("vlcj JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void play(String mrl) {
    	embeddedMediaPlayer.media().play(mrl);
    }
    
    public void play(String mrl,String[] options) {
    	embeddedMediaPlayer.media().play(mrl,options);
    }
    
    public ImageView getImageView() {
        return videoImageView;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

