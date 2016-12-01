import javax.swing.*;
import java.awt.FlowLayout;

public class VentanaConfiguracion extends JFrame{
	
	public VentanaConfiguracion(){
		super("Puerta Segura");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new PanelConfiguracion());
		setSize(500,200);
		setVisible(true);
	}
	
	public static void main(String args[]){
		Scp scp= new Scp();
       	scp.ScpFrom();
		new VentanaConfiguracion();
	}
}