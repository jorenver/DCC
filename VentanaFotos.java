import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;
import com.jcraft.jsch.*;
import java.util.Properties;



public class VentanaFotos extends JFrame{
	private JPanel panelSuperior,panelInferior;
	private MyList listaFotos;
	private JButton botonCancelar,botonEliminar, botonDescargar;
	private Scp scp;
	private String pass;
	private String ip;

	
	public VentanaFotos(String ip){
		super("Fotos Intrusos");
		this.ip = ip;
		crearPanel();
		setSize(500,350);
		setVisible(true);
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
	    cargarFotos();
	}

	private void cargarFotos(){
		
		JSch jsch=new JSch();
		try { 
			Session session=jsch.getSession("root",ip, 22);
			session.setPassword("arduino");
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();

			ChannelExec channel=(ChannelExec) session.openChannel("exec");
			BufferedReader in=new BufferedReader(new InputStreamReader(channel.getInputStream()));
			channel.setCommand("ls /mnt/sda1;");
			channel.connect();

			String msg=null;
			while((msg=in.readLine())!=null){
			  listaFotos.getModel().addElement(msg);
			}

			channel.disconnect();
			session.disconnect();
		}catch(Exception e){
			e.printStackTrace();
		}
	}



	ActionListener ListenerBotonEliminar =new ActionListener(){
		public void actionPerformed(ActionEvent e){
			int selection = listaFotos.getSelectedIndex();
			if (selection==-1) {
   				return;
			}
			String fileName = (String)listaFotos.getModel().get(selection);
			System.out.println(fileName);
			listaFotos.getModel().remove(selection);

			JSch jsch=new JSch();
			try { 
				Session session=jsch.getSession("root",ip, 22);
				session.setPassword("arduino");
				Properties config = new Properties();
				config.put("StrictHostKeyChecking", "no");
				session.setConfig(config);
				session.connect();

				ChannelExec channel=(ChannelExec) session.openChannel("exec");
				BufferedReader in=new BufferedReader(new InputStreamReader(channel.getInputStream()));
				channel.setCommand("rm /mnt/sda1/" + fileName + ";");
				channel.connect();
				channel.disconnect();
				session.disconnect();
			}catch(Exception ex){
				ex.printStackTrace();
			}

		}
	};

	ActionListener ListenerBotonDescargar =new ActionListener(){
		public void actionPerformed(ActionEvent e){
			int selection = listaFotos.getSelectedIndex();
			if (selection==-1) {
   				return;
			}
			String fileName = (String)listaFotos.getModel().get(selection);
			System.out.println(fileName);
			listaFotos.getModel().remove(selection);

			String rutaFile = "/mnt/sda1/" + fileName;
			Scp scp = new Scp("root",ip,"arduino",rutaFile,fileName);
			scp.ScpFrom();	
		}
	};

	ActionListener ListenerBotonCancelar =new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();		
		}
	};



	
}