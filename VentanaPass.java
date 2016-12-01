import javax.swing.*;
import java.awt.FlowLayout;

public class VentanaPass extends JFrame{
	private JPanel panelSuperior,panelInferior;
	
	public VentanaPass(){
		super("Configurar Contraseña");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		crearPanel();
		setSize(500,200);
		setVisible(true);
		
	}
	private void crearPanelSuperior(){
		panelSuperior= new JPanel();
	    GroupLayout layoutForm = new GroupLayout( panelSuperior);

	    panelSuperior.setLayout(layoutForm);
	    panelSuperior.setBorder( BorderFactory.createEmptyBorder( 10, 10, 10, 10 ) );
	    layoutForm.setAutoCreateGaps( true );

	    // Create the components we will put in the form
	    JLabel passLabel = new JLabel( "Nuevo Pin:" );
	    JTextField passTextField = new JPasswordField( 20 );
	    
	    JLabel newPassLabel = new JLabel( "Repetir Nuevo Pin:" );
	    JTextField newPassTextField = new JPasswordField( 20 );

	    // definen los elementos que comparten columna
	    layoutForm.setHorizontalGroup( layoutForm.createSequentialGroup()
	                                       .addGroup( layoutForm.createParallelGroup( GroupLayout.Alignment.LEADING )
	                                                          .addComponent( passLabel )
	                                                          .addComponent( newPassLabel ))
	                                       .addGroup( layoutForm.createParallelGroup( GroupLayout.Alignment.LEADING )
	                                                          .addComponent( passTextField )
	                                                          .addComponent( newPassTextField ))
	    );

	    //define los elementos que comparten filas
	    layoutForm.setVerticalGroup( layoutForm.createSequentialGroup()
	                                     .addGroup( layoutForm.createParallelGroup( GroupLayout.Alignment.BASELINE )
	                                                        .addComponent( passLabel )
	                                                        .addComponent( passTextField ) )
	                                     .addGroup( layoutForm.createParallelGroup( GroupLayout.Alignment.BASELINE )
	                                                        .addComponent( newPassLabel )
	                                                        .addComponent( newPassTextField ) )
	    );
	    getContentPane().add(panelSuperior);

	}
	private void crearPanelInferior(){
		panelInferior= new JPanel();
	    BoxLayout layoutBotones = new BoxLayout(panelInferior, BoxLayout.X_AXIS);
	    panelInferior.setLayout(layoutBotones);
	    JButton botonAceptar = new JButton("Aceptar");
	    JButton botonCancelar= new JButton("Cancelar");
	    //botonPass.addActionListener(ListenerBotonPass);
	    panelInferior.add(botonAceptar);
	    panelInferior.add(botonCancelar);
	    getContentPane().add(panelInferior);
	}

	private void crearPanel(){
		BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
	    getContentPane().setLayout(layout);
	    crearPanelSuperior();
	    crearPanelInferior();
	}
}