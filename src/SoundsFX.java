import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/*
    Classe SoundsFX possui os endereços de todos os efeitos sonoros
    do jogo.
 */

public class SoundsFX {

    private static final String caminho_malakar_intro = "malakar_intro.wav";
    private static final String caminho_clique = "Recursos_audio/click_sound.wav";
    private static final String caminho_musica_fundo = "Recursos_audio/timeless2.wav";

    private static final Map<String, Clip> audioClips = new HashMap<>();

        static {
            audioClips.put(caminho_clique, loadAudio(caminho_clique));
            audioClips.put(caminho_musica_fundo, loadAudio(caminho_musica_fundo));
        }

        private static Clip loadAudio(String path) {
            try {
                URL url = SoundsFX.class.getResource(path);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                return clip;
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
                return null;
            }
        }

        public static void playClique() {
            Clip clip = audioClips.get(caminho_clique);
            if (clip != null) {
                if (clip.isRunning()) {
                    clip.stop(); // Para o clip se estiver tocando
                }
                clip.setFramePosition(0); // Volta ao início
                clip.start();
            } else {
                System.err.println("Audio clip not found: " + caminho_clique);
            }
        }

    public static void playBackgroundMusic() {
        Clip clip = audioClips.get(caminho_musica_fundo);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop(); // Para o clip se estiver tocando
            }
            clip.setFramePosition(0); // Volta ao início
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Configura para tocar em loop
            clip.start();
        } else {
            System.err.println("Audio clip not found: " + caminho_musica_fundo);
        }
    }

    public static void main(String[] args) {
        playClique();
    }
}
