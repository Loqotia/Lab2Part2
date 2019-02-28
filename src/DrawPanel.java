import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.util.ArrayDeque;

public class DrawPanel extends JPanel {
    private class DrawCall {
        private BufferedImage image;
        private int x;
        private int y;

        DrawCall (BufferedImage image, int x, int y) {
            this.image = image;
            this.x = x;
            this.y = y;
        }

        public BufferedImage getImage() { return image; }
        public int getX() { return x; }
        public int getY() { return y; }
    }

    ArrayDeque<DrawCall> drawCalls;

    public DrawPanel(int width, int height) {
        drawCalls = new ArrayDeque<>();

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.green);
    }

    public void queryDrawCall(BufferedImage image, int x, int y) {
        drawCalls.add(new DrawCall(image, x, y));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        while (!drawCalls.isEmpty()) {
            DrawCall dc = drawCalls.pollFirst();
            g.drawImage(dc.getImage(), dc.getX(), dc.getY(), null);
        }

    }
}
