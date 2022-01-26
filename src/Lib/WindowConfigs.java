package Lib;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WindowConfigs {
	
	public static FlowLayout FLayout = new FlowLayout();
	
	public static void windowConfig(JFrame Window, int x, int y) {
		setLayout(Window);
		Window.setSize(x,y);
		Window.setLocationRelativeTo(null);
	}
	
	public static void setLayout(JFrame Window) {
		Window.setLayout(FLayout);
	}
	
	public static void setLayout(JPanel Panel) {
		Panel.setLayout(FLayout);
	}
	
	
}
