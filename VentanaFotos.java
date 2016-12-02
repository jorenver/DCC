import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;

public class VentanaFotos extends JFrame{
	private JPanel panelSuperior,panelInferior;
	private MyList listaFotos;
	private JButton botonCancelar,botonEliminar, botonDescargar;
	private Scp scp;
	private String pass;

	
	public VentanaFotos(){
		super("Fotos Intrusos");
		crearPanel();
		setSize(500,350);
		setVisible(true);
		System.out.println("Ventana Fotos");
		
	}
	private void crearPanelSuperior(){
		panelSuperior= new JPanel();
		BoxLayout layoutFotos = new BoxLayout(panelSuperior, BoxLayout.Y_AXIS);
		panelSuperior.setLayout(layoutFotos);
		JLabel fotoLabel = new JLabel( "Fotos:" );
	    listaFotos = new MyList();
	    botonEliminar = new JButton("Eliminar");
	    botonEliminar.addActionListener(ListenerBotonEliminar);
		panelSuperior.add(fotoLabel);
		panelSuperior.add(listaFotos.getContenedor());
		panelSuperior.add(botonEliminar);
		getContentPane().add(panelSuperior);
	}

	private void crearPanelInferior(){
		panelInferior= new JPanel();
	    BoxLayout layoutBotones = new BoxLayout(panelInferior, BoxLayout.X_AXIS);
	    panelInferior.setLayout(layoutBotones);
	    botonCancelar= new JButton("Cancelar");
	    botonDescargar = new JButton("Descargar Foto");
	    botonCancelar.addActionListener(ListenerBotonCancelar);
	    botonDescargar.addActionListener(ListenerBotonDescargar);
	    panelInferior.add(botonCancelar);
	    panelInferior.add(botonDescargar);
	    getContentPane().add(panelInferior);
	}

	private void crearPanel(){
		BoxLayout layout = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
	    getContentPane().setLayout(layout);
	    crearPanelSuperior();
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
            		listaFotos.getModel().addElement(cadena);
            	}
        		b.close();
        	}catch(IOException exection){

        	}
	}



	ActionListener ListenerBotonEliminar =new ActionListener(){
		public void actionPerformed(ActionEvent e){
			int selection = listaFotos.getSelectedIndex();
			if (selection!=-1) {
   				listaFotos.getModel().remove(selection);
			}
		}
	};

	ActionListener ListenerBotonDescargar =new ActionListener(){
		public void actionPerformed(ActionEvent e){
			//ejecutar comandos para descargar la imagen	
		}
	};

	ActionListener ListenerBotonCancelar =new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();		
		}
	};



	
}