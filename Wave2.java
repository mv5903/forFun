import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wave2 {
    public static void main(String[] args) {
        new Frame1();
    }
}

@SuppressWarnings("serial")
class Frame1 extends JFrame {
    final int PANEL_WIDTH = 2000, PANEL_HEIGHT = 1200;
    Panel1 p = new Panel1();
    JFrame mainFrame = this;
    public static int totalFrameCount = 0;

    Frame1() {
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(p);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Sine Wave");
        setVisible(true);
        mainFrame = this;
    }

    class Panel1 extends JPanel implements ActionListener {
        public MovableObject[] items = new MovableObject[25]; //number of circles
        GradientPaint paint;
        Timer timer;
        double z = 0;
        double oldTime;

        Panel1() {
            oldTime = System.nanoTime();
            setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
            setBackground(Color.black);
            timer = new Timer(1, this);
            for (int j = 0; j < items.length; j++) {
                Color toSet = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
                items[j] = new MovableObject();
                items[j].toSet = toSet;
                items[j].location.x = j*50; //spacing between circles
                items[j].location.y = j*25;
            }
            new FrameCounter().start();
            timer.start();
        }

        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < items.length; i++) {
                MovableObject m = items[i];
                m.graphic = (Graphics2D) g;
                m.graphic.setPaint(m.toSet);
                m.graphic.fillOval((int) m.location.x, (int) m.location.y, 40, 40); //(width = 40, height = 40)
            }
        }

        public void actionPerformed(ActionEvent arg0) {
            totalFrameCount++;
            // tweak the numbers here for different effects
            for (int i = 0; i < items.length; i++) {
                if (i < items.length) {
                    z = (i % 2 == 0) ? z + Math.tan(0.001 * i) : z - Math.tan(0.001 * i);
                    items[i].location.y = -2 * Math.sin(.5*z - i) * 100 + 250;
                    items[i].location.x = -2 * Math.sin(-.5*z - i) * 100 + 250;

                }
                repaint();
            }
        }
    }

    class FrameCounter extends Thread {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
                mainFrame.setTitle("FPS: " + totalFrameCount);
                totalFrameCount = 0;
            }
        }
    }

    class MovableObject {
        Points location;
        Graphics2D graphic;
        Color toSet;

        MovableObject() {
            location = new Points(0, 250);
        }

    }
}

class Points {
    double x;
    double y;

    Points(double d, double y) {
        this.x = d;
        this.y = y;
    }
}