package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javafx.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML Label volumeLbl;
    @FXML private MediaView mv;
    @FXML private Slider volumeSlider;
    private MediaPlayer mp;
    private Media me;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String path = new File("src/media/Animal.mp4").getAbsolutePath();
        me = new Media(new File(path).toURI().toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        mp.setAutoPlay(true);
        volumeSlider.setValue(mp.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(volumeSlider.getValue() / 100);
                volumeLbl.setText("Volume: " + String.valueOf(volumeSlider.getValue() / 100));
            }
        });
        DoubleProperty width = mv.fitWidthProperty();
        DoubleProperty hight = mv.fitHeightProperty();
        width.bind(Bindings.select(mv.sceneProperty(),"width"));
        hight.bind(Bindings.select(mv.sceneProperty(),"height"));
    }


    public void play (ActionEvent actionEvent){
        mp.play();
        mp.setRate(1);
    }

    public void pause (ActionEvent actionEvent){
        mp.pause();
    }

    public void fast (ActionEvent actionEvent){
        mp.setRate(2);
    }

    public void slow (ActionEvent actionEvent){
        mp.setRate(.5);
    }

    public void reload (ActionEvent actionEvent){
        mp.seek(mp.getStartTime());
        mp.play();
    }

    public void start (ActionEvent actionEvent){
        mp.seek(mp.getStartTime());
        mp.stop();
    }

    public void last (ActionEvent actionEvent){
        mp.seek(mp.getTotalDuration());
        mp.stop();
    }


}
