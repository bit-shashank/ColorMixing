package LogicScript;

import java.awt.Color;

public class ColorMixture{

	static Color c1;//object to store first color
	static Color c2;//object to store second color
	static int qty1=-1;//quantity of first color a value between (0-100)
	static int qty2=-1;//quantity of second color a value between (0-100)
	
	//Getters and Setters
	public Color getC1() {
		return c1;
	}
	
	public static void setC1(Color c1) {
		ColorMixture.c1 = c1;
	}
	
	public static Color getC2() {
		return c2;
	}
	
	public static void setC2(Color c2) {
		ColorMixture.c2 = c2;
	}
	
	public static int getQty1() {
		return qty1;
	}
	
	public static void setQty1(int qty1) {
		ColorMixture.qty1 = qty1;
	}
	
	public static int getQty2() {
		return qty2;
	}
	
	public static void setQty2(int qty2) {
		ColorMixture.qty2 = qty2;
	}
	
	/*
	 * This functions mix the two colors and return 
	 * the mixed color.
	 * Mixed color is just the weighted mean of the two colors
	 */
	public static Color mix(){
		int red=(c1.getRed()*qty1+c2.getRed()*qty2)/(qty1+qty2);
		int green=(c1.getGreen()*qty1+c2.getGreen()*qty2)/(qty1+qty2);
		int blue=(c1.getBlue()*qty1+c2.getBlue()*qty2)/(qty1+qty2);	
		return new Color(red,green,blue);
	}

	public static boolean validateInput() {
		if((qty1==0&&qty2==0)||c1==null||c2==null)
			return false;
		return true;
	}
	
}
