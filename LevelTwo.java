import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class LevelTwo extends Level {

    private Image backgroundImage;
	private Spikes spikeMaker;

    public LevelTwo() {
        super(new Rectangle2D.Double[] {
                new Rectangle2D.Double(0, 758, 1024, 10),
                new Rectangle2D.Double(32, 239, 91, 20),
                new Rectangle2D.Double(77, 653, 329, 20),
                new Rectangle2D.Double(120, 158, 91, 20),
                new Rectangle2D.Double(120, 333, 91, 20),
                new Rectangle2D.Double(200, 414, 91, 20),
                new Rectangle2D.Double(210, 113, 482, 20),
                new Rectangle2D.Double(256, 482, 91, 20),
                new Rectangle2D.Double(290, 570, 91, 20),
                new Rectangle2D.Double(673, 133, 170, 20),
                new Rectangle2D.Double(780, 323, 244, 20),
                new Rectangle2D.Double(823, 0, 20, 133)
        });
        backgroundImage = new ImageIcon(getClass().getResource("/level-assets/level2.png")).getImage();
		spikeMaker = new Spikes();
    }

    @Override
    public Image getBackgroundImage() {
        return backgroundImage;
    }
	
	public void spikeCreator(){
		spikeMaker.addSpike(0,950);
		spikeMaker.addSpike(60,950);
		spikeMaker.addSpike(120,950);
		spikeMaker.addSpike(700,360);
		spikeMaker.addSpike(760,360);
		spikeMaker.addSpike(820,360);
		spikeMaker.addSpike(290,360);
		spikeMaker.addSpike(350,360);
		spikeMaker.addSpike(1300,770);
		spikeMaker.addSpike(1360,770);
	}

    @Override
    public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		Rectangle2D.Double door1 = new Rectangle2D.Double(800,250,70,80);
		Rectangle2D.Double door2 = new Rectangle2D.Double(880,250,70,80);
		Rectangle2D.Double lift2 = new Rectangle2D.Double(730,680,55,10);
		Rectangle2D.Double lift3 = new Rectangle2D.Double(690,600,55,10);
		Rectangle2D.Double lift4 = new Rectangle2D.Double(730,520,55,10);
		Rectangle2D.Double lift5 = new Rectangle2D.Double(690,440,55,10);
		Rectangle2D.Double lift6 = new Rectangle2D.Double(730,360,55,10);
		g2d.fill(door1);
		g2d.fill(door2);
		g2d.fill(lift2);
		g2d.fill(lift3);
		g2d.fill(lift4);
		g2d.fill(lift5);
		g2d.fill(lift6);
        g2d.setColor(new Color(45, 45, 45));
        for (int i = 0; i < getPlatformCount(); i++) {
            g2d.fill(getPlatform(i));
        }
		
		spikeCreator();
		spikeMaker.draw(g2d);
    }
}
