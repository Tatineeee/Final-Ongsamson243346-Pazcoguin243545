import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Items{
	
	private Image book1, book2, book3;
	private Image water1, water2, water3;
	private Image cassette1, cassette2, cassette3;
	private Image weapon1, weapon2, weapon3;
	
	public Items(){
		book1 = new ImageIcon(getClass().getResource("/items/book1.PNG")).getImage();
		book2 = new ImageIcon(getClass().getResource("/items/book2.PNG")).getImage();
		book3 = new ImageIcon(getClass().getResource("/items/book3.PNG")).getImage();
		water1 = new ImageIcon(getClass().getResource("/items/water1.PNG")).getImage();
		water2 = new ImageIcon(getClass().getResource("/items/water2.PNG")).getImage();
		water3 = new ImageIcon(getClass().getResource("/items/water3.PNG")).getImage();
		cassette1 = new ImageIcon(getClass().getResource("/items/cassette1.PNG")).getImage();
		cassette2 = new ImageIcon(getClass().getResource("/items/cassette2.PNG")).getImage();
		cassette3 = new ImageIcon(getClass().getResource("/items/cassette3.PNG")).getImage();
		weapon1 = new ImageIcon(getClass().getResource("/items/weapon1.PNG")).getImage();
		weapon2 = new ImageIcon(getClass().getResource("/items/weapon2.PNG")).getImage();
		weapon3 = new ImageIcon(getClass().getResource("/items/weapon3.PNG")).getImage();
		
	}
	
	public Image getBookImage1() {
        return book1;
    }
	
	public Image getBookImage2() {
        return book2;
    }
	
	public Image getBookImage3() {
        return book3;
    }
	
	public Image getWaterImage1(){
		return water1;
	}
	
	public Image getWaterImage2(){
		return water2;
	}
	
	public Image getWaterImage3(){
		return water3;
	}
	
	public Image getCassetteImage1(){
		return cassette1;
	}
	
	public Image getCassetteImage2(){
		return cassette2;
	}
	
	public Image getCassetteImage3(){
		return cassette3;
	}
	
	public Image getWeaponImage1(){
		return weapon1;
	}
	
	public Image getWeaponImage2(){
		return weapon2;
	}
	
	public Image getWeaponImage3(){
		return weapon3;
	}
}