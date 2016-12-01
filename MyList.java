import javax.swing.*;

public class MyList extends JList{
	private DefaultListModel<String> model;
	private JScrollPane contenedor;
	public MyList (){
		model= new DefaultListModel<>();
		this.setModel(model);
		contenedor=new JScrollPane(this);
		contenedor.setBounds(10,30,150,110); 

	}

	public DefaultListModel getModel(){
		return model;
	}

	public JScrollPane getContenedor(){
		return contenedor;
	}

}