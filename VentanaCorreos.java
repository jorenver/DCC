import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;

public class VentanaCorreos extends JFrame{
	private JPanel panelSuperior,panelInferior,panelCentral;
	private MyList listaCorreos;
	private JButton botonAgregar,botonAceptar,botonCancelar,botonEliminar;
	private JTextField correoTextField;
	private Scp scp;
	private String pass;

	
	public VentanaCorreos(){
		super("Editar Correos");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		crearPanel();
		//this.scp=scp;
		setSize(500,350);
		setVisible(true);
		
	}
	private void crearPanelSuperior(){
		panelSuperior= new JPanel();
		FlowLayout layoutCorreo = new FlowLayout();
		panelSuperior.setLayout(layoutCorreo);
		JLabel correoLabel = new JLabel( "Correo:" );
	    correoTextField = new JTextField( 20 );
		botonAgregar = new JButton("Agregar");
		botonAgregar.addActionListener(ListenerBotonAgregar);
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
	    botonEliminar = new JButton("Eliminar");
	    botonEliminar.addActionListener(ListenerBotonEliminar);
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
	    botonCancelar.addActionListener(ListenerBotonCancelar);
	    botonAceptar.addActionListener(ListenerBotonGuardar);
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
	    cargarCorreos();
	}

	private void cargarCorreos(){
		String lfile="config.txt";
		String cadena;
       		try{
        		FileReader f = new FileReader(lfile);
        		BufferedReader b = new BufferedReader(f);
        		pass = b.readLine();
            	System.out.println(pass);
            	while((cadena = b.readLine())!=null){
            		listaCorreos.getModel().addElement(cadena);
            	}
        		b.close();
        	}catch(IOException exection){

        	}
	}



	ActionListener ListenerBotonAgregar =new ActionListener(){
		public void actionPerformed(ActionEvent e){
			listaCorreos.getModel().addElement(correoTextField.getText());
			correoTextField.setText("");
		}
	};

	ActionListener ListenerBotonEliminar =new ActionListener(){
		public void actionPerformed(ActionEvent e){
			int selection = listaCorreos.getSelectedIndex();
			if (selection!=-1) {
   				listaCorreos.getModel().remove(selection);
			}
		}
	};
	ActionListener ListenerBotonCancelar =new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();		
		}
	};

	ActionListener ListenerBotonGuardar =new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String lfile="config.txt";
			String cadena;
       		try{
        		FileWriter f = new FileWriter(lfile);
        		PrintWriter pw = new PrintWriter(f);
                pw.println(pass);
                for(int i=0; i < listaCorreos.getModel().getSize(); i++){
     				pw.println(listaCorreos.getModel().getElementAt(i));  
				}
        		f.close();
        		//JOptionPane.showMessageDialog(this, "Guardado Correctamente");
        	}catch(IOException exection){

        	}

			
		}
	};
	
}