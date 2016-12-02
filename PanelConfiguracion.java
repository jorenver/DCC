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
	private JTextField ipAddressTextField;
	private JPasswordField passTextField;
	private JButton botonConectar, botonPass, botonCorreos ,botonFotos,botonDesconectar;
	private Scp scp;

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
		panelInferior.setLayout(layoutBotones);
	    botonConectar = new JButton("Conectar");
	    botonDesconectar = new JButton("Desconectar");
	    botonPass = new JButton("Configurar Contraseña");
	    botonCorreos = new JButton("Configurar Correos");
	    botonFotos= new JButton("Fotos Intrusos");
	    botonConectar.setEnabled(true);
	    botonDesconectar.setEnabled(false);
	    botonPass.setEnabled(false);
	    botonCorreos.setEnabled(false);
	    botonFotos.setEnabled(false);
	    botonConectar.addActionListener(ListenerBotonConectar);
	    botonPass.addActionListener(ListenerBotonPass);
	    botonCorreos.addActionListener(ListenerBotonCorreos);
	    botonFotos.addActionListener(ListenerBotonFotos);
	    panelInferior.add(botonConectar);
	    panelInferior.add(botonDesconectar);
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
	    ipAddressTextField = new JTextField( 20 );
	    
	    JLabel passLabel = new JLabel( "Pin:" );
	    passTextField = new JPasswordField( 20 );

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
			new VentanaPass();
		}


	};
	ActionListener ListenerBotonCorreos =new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new VentanaCorreos();
		}


	};
	ActionListener ListenerBotonFotos =new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new VentanaFotos();
		}


	};

	ActionListener ListenerBotonConectar=new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String user="root";
    		String host=ipAddressTextField.getText();
    		String pass="arduino";
    		String rfile="/mnt/sda1/config.txt";
    		String lfile="config.txt";

			scp= new Scp(user,host,pass,rfile,lfile);
       		scp.ScpFrom();
       		String cadena;
       		try{
        		FileReader f = new FileReader(lfile);
        		BufferedReader b = new BufferedReader(f);
        		cadena = b.readLine().replaceAll("\n", ""); ;
            	System.out.println(cadena);
            	System.out.println(passTextField.getPassword());
            	System.out.println(cadena.equals(passTextField.getPassword()));
        		b.close();
        		//if(cadena.equals(passTextField.getPassword())){
        		if(true){
        			botonConectar.setEnabled(false);
	    			botonDesconectar.setEnabled(true);
	    			botonPass.setEnabled(true);
	    			botonCorreos.setEnabled(true);
	    			botonFotos.setEnabled(true);
        		}
        	}catch(IOException exection){

        	}
			
		}
	
	};
	
}