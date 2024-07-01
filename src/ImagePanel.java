import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
    private BufferedImage portalImage;
    private String text;

    public ImagePanel() {
        try {
            portalImage = ImageIO.read(new File("resources/portal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = "Caldeum\nO povo precisa de um herói!\nAjude Dante a eliminar as ameaças esqueléticas e\n"
                + "derrotar o Lorde das Trevas, Malakar.";
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();

        // Desenha a imagem do portal na parte superior
        if (portalImage != null) {
            int portalImageX = (width - portalImage.getWidth()) / 2;
            g.drawImage(portalImage, portalImageX, 0, this);
        }

        // Configura a fonte e a cor do texto
        g.setFont(new Font("Monospaced", Font.BOLD, 20));
        g.setColor(Color.WHITE);

        // Quebra o texto em múltiplas linhas
        String[] lines = text.split("\n");
        int lineHeight = g.getFontMetrics().getHeight();
        int startY = portalImage.getHeight() + 20; // Ajusta a posição inicial do texto

        // Desenha cada linha do texto
        for (int i = 0; i < lines.length; i++) {
            int lineWidth = g.getFontMetrics().stringWidth(lines[i]);
            int startX = (width - lineWidth) / 2;
            g.drawString(lines[i], startX, startY + (i * lineHeight));
        }
    }
}



