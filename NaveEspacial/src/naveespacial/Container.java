package naveespacial;

import javax.swing.JFrame;

import naveespacial.Modelo.Fase;

public class Container extends JFrame {
	
	public Container() {
		add (new Fase());
		setTitle("Nave Espacial");
		setSize(1024, 728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	
	public static void main (String[] args) {
		new Container();
	}
}
