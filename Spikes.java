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
		path.moveTo(12.5,0);
		path.lineTo(0, 25);
		path.lineTo(25,25);
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