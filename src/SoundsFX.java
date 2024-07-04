import javax.swing.JApplet;
import java.applet.AudioClip;
import java.net.URL;

/*

    Classe SoundsFX possui os endereços de todos os efeitos sonoros
    do jogo.

 */

public class SoundsFX {

    public void play() {
        URL url = SoundsFX.class.getResource("res/godofwar.wav");
        AudioClip audio = JApplet.newAudioClip(url);
        audio.play();
    }

    public static void main(String[] args) {
        SoundsFX soundFX = new SoundsFX();
        soundFX.play();
    }
}
