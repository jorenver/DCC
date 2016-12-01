import javax.swing.*;
import java.awt.FlowLayout;

public class VentanaConfiguracion extends JFrame{
	
	public VentanaConfiguracion(){
		super("Puerta Segura");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new PanelConfiguracion());
		setSize(750,150);
		setVisible(true);
	}
	
	public static void main(String args[]){
		new VentanaConfiguracion();
	}
}