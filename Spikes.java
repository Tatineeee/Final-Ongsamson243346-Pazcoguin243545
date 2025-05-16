import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

public class Spikes{

	int translateX, translateY;
	Path2D.Double path;
		
	public Spikes(){
		translateX = 0;
		translateY = 0;
		path = new Path2D.Double();
		path.moveTo(25,0);
		path.lineTo(0, 50);
		path.lineTo(50,50);
		path.closePath();
	}
		
	public Path2D.Double SpikeDraw(){
		return path;
	}
		
	public int getPathX(){
		return translateX;
	}
		
	public int getPathY(){
		return translateY;
	}
	
	
}