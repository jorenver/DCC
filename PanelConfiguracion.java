import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;





public class PanelConfiguracion extends JPanel {
	private JPanel panelInferior, panelSuperior;

	public PanelConfiguracion(){
		setSize(300,300);
		llenarPanel();
	}

	private void llenarPanel(){
	    BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	    setLayout(layout);
	    crearPanelSuperior();
	    crearPanelInferior();
	}

	private void crearPanelInferior(){
		panelInferior= new JPanel();
	    BoxLayout layoutBotones = new BoxLayout(panelInferior, BoxLayout.X_AXIS);
	   	JButton botonPass = new JButton("Configurar Contraseña");
	    JButton botonCorreos = new JButton("Configurar Correos");
	    JButton botonFotos= new JButton("Fotos Intrusos");
	    botonPass.addActionListener(ListenerBotonPass);
	    panelInferior.add(botonPass);
	    panelInferior.add(botonCorreos);
	    panelInferior.add(botonFotos);
	    add(panelInferior);
	}

	private void crearPanelSuperior(){
		panelSuperior= new JPanel();
	    GroupLayout layoutForm = new GroupLayout( panelSuperior);

	    panelSuperior.setLayout(layoutForm);
	    panelSuperior.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
	    layoutForm.setAutoCreateGaps( true );

	    // Create the components we will put in the form
	    JLabel ipAddressLabel = new JLabel( "Direccion IP:" );
	    JTextField ipAddressTextField = new JTextField( 20 );
	    
	    JLabel passLabel = new JLabel( "Pin:" );
	    JTextField passTextField = new JPasswordField( 20 );

	    // definen los elementos que comparten columna
	    layoutForm.setHorizontalGroup( layoutForm.createSequentialGroup()
	                                       .addGroup( layoutForm.createParallelGroup( GroupLayout.Alignment.LEADING )
	                                                          .addComponent( ipAddressLabel )
	                                                          .addComponent( passLabel ))
	                                       .addGroup( layoutForm.createParallelGroup( GroupLayout.Alignment.LEADING )
	                                                          .addComponent( ipAddressTextField )
	                                                          .addComponent( passTextField ))
	    );

	    //define los elementos que comparten filas
	    layoutForm.setVerticalGroup( layoutForm.createSequentialGroup()
	                                     .addGroup( layoutForm.createParallelGroup( GroupLayout.Alignment.BASELINE )
	                                                        .addComponent( ipAddressLabel )
	                                                        .addComponent( ipAddressTextField ) )
	                                     .addGroup( layoutForm.createParallelGroup( GroupLayout.Alignment.BASELINE )
	                                                        .addComponent( passLabel )
	                                                        .addComponent( passTextField ) )
	    );
	    add(panelSuperior);

	}

	ActionListener ListenerBotonPass =new ActionListener(){
		public void actionPerformed(ActionEvent e){
       		String cadena;
       		try{
        		FileReader f = new FileReader("./config.txt");
        		BufferedReader b = new BufferedReader(f);
        		cadena = b.readLine();
            	System.out.println(cadena);
        		b.close();
        	}catch(IOException exection){

        	}
			new VentanaPass();
		}
	
	};
	
}