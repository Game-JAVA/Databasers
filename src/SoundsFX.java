import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SoundsFX {

    private static final String caminho_clique = "Recursos_audio/click_sound.wav";
    private static final String caminho_musica_fundo = "Recursos_audio/timeless2.wav";
    private static final String caminho_som_corrida = "Recursos_audio/running.wav";
    private static final String caminho_hit = "Recursos_audio/manHit.wav";
    private static final String caminho_atk = "Recursos_audio/atk_sound.wav";
    private static final String caminho_themesong = "Recursos_audio/theme_song.wav";


    private static final Map<String, Clip> audioClips = new HashMap<>();

    static {
        audioClips.put(caminho_clique, loadAudio(caminho_clique));
        audioClips.put(caminho_musica_fundo, loadAudio(caminho_musica_fundo));
        audioClips.put(caminho_themesong, loadAudio(caminho_themesong));
        audioClips.put(caminho_som_corrida, loadAudio(caminho_som_corrida));
        audioClips.put(caminho_hit, loadAudio(caminho_hit));
        audioClips.put(caminho_atk, loadAudio(caminho_atk));
    }

    private static Clip loadAudio(String path) {
        try {
            URL url = SoundsFX.class.getResource(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);

            AudioFormat baseFormat = audioStream.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream decodedStream = AudioSystem.getAudioInputStream(decodedFormat, audioStream);
            Clip clip = AudioSystem.getClip();
            clip.open(decodedStream);
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

    public static void playRunSound() {
        Clip clip = audioClips.get(caminho_som_corrida);
        Clip atk = audioClips.get(caminho_atk);
        if (clip != null) {
            if (!clip.isRunning()) {
                clip.setFramePosition(0); // Volta ao início
                atk.stop();
                clip.start();
            }
        } else {
            System.err.println("Audio clip not found: " + caminho_som_corrida);
        }
    }

    public static void playHit() {
        Clip clip = audioClips.get(caminho_hit);
        if (clip != null) {
            if (!clip.isRunning()) {
                clip.setFramePosition(0); // Volta ao início
                clip.start();
            }
        } else {
            System.err.println("Audio clip not found: " + caminho_hit);
        }
    }

    public static void playAtk() {
        Clip clip = audioClips.get(caminho_atk);
        Clip run = audioClips.get(caminho_som_corrida);
        if (clip != null) {
            if (!clip.isRunning()) {
                clip.setFramePosition(0); // Volta ao início
                run.stop();
                clip.start();
            }
        } else {
            System.err.println("Audio clip not found: " + caminho_atk);
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

    public static void stopBackgroundMusic() {
        Clip clip = audioClips.get(caminho_musica_fundo);
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Para o clip se estiver tocando
        } else {
            System.err.println("Audio clip not found or not playing: " + caminho_musica_fundo);
        }
    }

    public static void playThemesong(float volume) {
        Clip clip = audioClips.get(caminho_themesong);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop(); // Stop the clip if it's running
            }
            clip.setFramePosition(0); // Reset to the beginning
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Set to loop
            clip.start();

            // Set the volume
            try {
                FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
                volumeControl.setValue(dB);
            } catch (Exception e) {
                System.err.println("Volume control not supported: " + e.getMessage());
            }
        } else {
            System.err.println("Audio clip not found: " + caminho_themesong);
        }
    }

    public static void stopThemesong() {
        Clip clip = audioClips.get(caminho_themesong);
        if (clip != null && clip.isRunning()) {
            clip.stop(); // Para o clip se estiver tocando
        } else {
            System.err.println("Audio clip not found or not playing: " + caminho_themesong);
        }
    }

    public static void main(String[] args) {
        playBackgroundMusic();

        // Adicione aqui o código para esperar e parar a música de fundo para teste
        // Exemplo:
        try {
            Thread.sleep(5000); // Espera por 5 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stopBackgroundMusic();
    }
}
