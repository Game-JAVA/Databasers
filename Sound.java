import java.applet.AudioClip;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Sound {

    private static final String caminho_malakar_intro = "Som/malakar_intro.wav";
    private static final String caminho_clique = "Som/click_sound.wav";

    private static final Map<String, AudioClip> audioClips = new HashMap<>();

    static {
        audioClips.put(caminho_malakar_intro, loadAudio(caminho_malakar_intro));
        audioClips.put(caminho_clique, loadAudio(caminho_clique));
    }

    private static AudioClip loadAudio(String path) {
        URL url = Sound.class.getResource(path);
        return java.applet.Applet.newAudioClip(url);
    }

    public static void playMalakarIntro() {
        AudioClip audioClip = audioClips.get(caminho_malakar_intro);
        audioClip.play();
    }

    public static void playClique() {
        AudioClip audioClip = audioClips.get(caminho_clique);
        audioClip.play();
    }

    public static void stopAll() {
        for (AudioClip audioClip : audioClips.values()) {
            audioClip.stop();
        }
    }
}