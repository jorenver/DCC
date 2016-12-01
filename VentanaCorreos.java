import javax.swing.*;
import java.awt.FlowLayout;

public class VentanaCorreos extends JFrame{
	private JPanel panelSuperior,panelInferior,panelCentral;
	private MyList listaCorreos;
	private JButton botonAgregar,botonAceptar,botonCancelar,botonEliminar;

	
	public VentanaCorreos(){
		super("Editar Correos");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		crearPanel();
		setSize(500,350);
		setVisible(true);
		
	}
	private void crearPanelSuperior(){
		panelSuperior= new JPanel();
		FlowLayout layoutCorreo = new FlowLayout();
		panelSuperior.setLayout(layoutCorreo);
		JLabel correoLabel = new JLabel( "Correo:" );
	    JTextField correoTextField = new JPasswordField( 20 );
		botonAgregar = new JButton("Agregar");
		panelSuperior.add(correoLabel);
		panelSuperior.add(correoTextField);
		panelSuperior.add(botonAgregar);
		getContentPane().add(panelSuperior);
	}
	private void crearPanelCentral(){
		panelCentral= new JPanel();
		BoxLayout layoutCorreos = new BoxLayout(panelCentral, BoxLayout.Y_AXIS);
		panelCentral.setLayout(layoutCorreos);
		JLabel correoLabel = new JLabel( "Correos:" );
	    listaCorreos = new MyList();
	    modelList(listaCorreos.getModel());
	    botonEliminar = new JButton("Eliminar");
		panelCentral.add(correoLabel);
		panelCentral.add(listaCorreos.getContenedor());
		panelCentral.add(botonEliminar);
		getContentPane().add(panelCentral);
	}

	private void crearPanelInferior(){
		panelInferior= new JPanel();
	    BoxLayout layoutBotones = new BoxLayout(panelInferior, BoxLayout.X_AXIS);
	    panelInferior.setLayout(layoutBotones);
	    botonAceptar = new JButton("Guardar");
	    botonCancelar= new JButton("Cancelar");
	    //botonPass.addActionListener(ListenerBotonPass);
	    panelInferior.add(botonAceptar);
	    panelInferior.add(botonCancelar);
	    getContentPane().add(panelInferior);
	}

	private void crearPanel(){
		BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
	    getContentPane().setLayout(layout);
	    crearPanelSuperior();
	    crearPanelCentral();
	    crearPanelInferior();
	}
	private void modelList(DefaultListModel<String> model){
	   model.addElement("Alumno 1");
	   model.addElement("Alumno 2");
	   model.addElement("Alumno 3");
	   model.addElement("Alumno 3");
	   model.addElement("Alumno 3");
	   model.addElement("Alumno 3");
	   model.addElement("Alumno 3");
	   model.addElement("Alumno 3");
	   model.addElement("Alumno 3");
	   model.addElement("Alumno 3");
	   model.addElement("Alumno 3");
	   model.addElement("Alumno 3");
	   model.addElement("Alumno 3");
	   model.addElement("Alumno 3");
	}
}