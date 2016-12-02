import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class VentanaPass extends JFrame{
	private JPanel panelSuperior,panelInferior;
	private JTextField passTextField,newPassTextField;
	private Scp scp;
	
	public VentanaPass(Scp scp){
		super("Configurar Pin");
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.scp=scp;
		crearPanel();
		setSize(500,150);
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
	    passTextField = new JPasswordField( 20 );
	    PlainDocument document = (PlainDocument) passTextField.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
        	private boolean isNumber(String s) {
			    try {
			      Long.parseLong(s);
			      return true;
			    } catch (NumberFormatException e) {
			      return false;
			    }
			  }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 16 && isNumber(string)) {
                    super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
                }
            }

        });
	    
	    JLabel newPassLabel = new JLabel( "Repetir Nuevo Pin:" );
	    newPassTextField = new JPasswordField( 20 );
	    PlainDocument document2 = (PlainDocument) passTextField.getDocument();
        document2.setDocumentFilter(new DocumentFilter() {
        	private boolean isNumber(String s) {
			    try {
			      Long.parseLong(s);
			      return true;
			    } catch (NumberFormatException e) {
			      return false;
			    }
			  }

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

                if (string.length() <= 16 && isNumber(string)) {
                    super.replace(fb, offset, length, text, attrs); //To change body of generated methods, choose Tools | Templates.
                }
            }

        });

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
	    botonAceptar.addActionListener(ListenerBotonAceptar);
	    botonCancelar.addActionListener(ListenerBotonCancelar);
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
	ActionListener ListenerBotonAceptar=new ActionListener(){
		public void actionPerformed(ActionEvent e){
			String pin1,pin2;
			pin1=new String(passTextField.getText());
			pin2=new String(newPassTextField.getText());
			if(pin1.equals(pin2)){
				String lfile="config.txt";
				String nuevoContenido=pin1+"\n";
				String cadena;
	       		try{
	       			FileReader fr = new FileReader(lfile);
	        		BufferedReader b = new BufferedReader(fr);
	        		b.readLine();
	            	
	            	while((cadena = b.readLine())!=null){
	            		nuevoContenido=nuevoContenido+cadena+"\n";
	            	}
	        		b.close();
	        		FileWriter fw = new FileWriter(lfile);
	        		PrintWriter pw = new PrintWriter(fw);
	                pw.printf(nuevoContenido);
	        		fw.close();
	        		scp.ScpTo();
	        		JOptionPane.showMessageDialog(null, "Pin Actualizado!");
	        		dispose();
	        	}catch(IOException exection){}
			}else{
				JOptionPane.showMessageDialog(null, "El Pin no coincide!");
			}
			
		}
	
	};
	ActionListener ListenerBotonCancelar=new ActionListener(){
		public void actionPerformed(ActionEvent e){
			dispose();
		}
	
	};
}