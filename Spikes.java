import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class Spikes{
	
	private ArrayList<Spike> triangles;
	private AffineTransform transform, standardPosition;
	
	public Spikes(){
		triangles = new ArrayList<>();
	}	
	
	private class Spike{
		int translateX, translateY;
		Path2D.Double path;
		
		public Spike(int x, int y){
			translateX = x;
			translateY = y;
			path = new Path2D.Double();
		    path.moveTo(25,0);
		    path.lineTo(0, 50);
		    path.lineTo(50,50);
			path.closePath();
	    }
		
	}
	
	public void addSpike(int x, int y){
		triangles.add(new Spike(x,y));
	}

	public void draw(Graphics2D g2d){
		standardPosition = g2d.getTransform();
		
		for(Spike spike : triangles){
		    AffineTransform transform = AffineTransform.getTranslateInstance(spike.translateX, spike.translateY);
		    g2d.setTransform(transform);
		    g2d.setColor(Color.WHITE);
			g2d.fill(spike.path);
		}
		g2d.setTransform(standardPosition);
	}
	
}