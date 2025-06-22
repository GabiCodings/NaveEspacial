package naveespacial.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import javax.swing.ImageIcon;

public class Player implements ActionListener {
	private int x,y;
	private int dx,dy;
	private Image Imagem;
	private int altura, largura;
	private List <Tiro> tiros;
	private boolean isVisible, isTurbo;
	private Timer timer;
	private Image naveNormal;
	private Image naveTurbo;
	
	public Player() {
		this.x = 100;
		this.y = 100;
		isVisible = true;
		isTurbo = false;
		
		ImageIcon referencia1 = new ImageIcon("res\\spaceship2.png");
	    naveNormal = referencia1.getImage();

	    ImageIcon referencia2 = new ImageIcon("res\\naveturbo.png");
	    naveTurbo = referencia2.getImage();

	    Imagem = naveNormal;

	    altura = Imagem.getHeight(null);
	    largura = Imagem.getWidth(null);
		
		tiros = new ArrayList<Tiro>();
		
		timer = new Timer(7000, this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isTurbo == true) {
			turbo();
			isTurbo = false;
		} 
		if (isTurbo == false) {
	        Imagem = naveNormal;
	    }
		
		
	}
	
	public void load() {
		ImageIcon referencia = new ImageIcon("res\\spaceship2.png");
		Imagem = referencia.getImage();
		
		altura = Imagem.getHeight(null);
		largura = Imagem.getWidth(null);
	}
	
	public void update() {
		x += dx;
		y += dy;
	}
	
	
	public void tiroSimples() {
		this.tiros.add(new Tiro(x+largura, y + (altura/2)));
	}
	
	public void turbo() {
		isTurbo = true;
		Imagem = naveTurbo;
		
	}
	
	public Rectangle getBound() {
		return new Rectangle (x, y, largura, altura);
	}
	
	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_SPACE) {
			turbo();
		}
		if(codigo == KeyEvent.VK_Z) {
			if(isTurbo == false) {
				tiroSimples();
			}
			
		}
		if(codigo == KeyEvent.VK_UP) {
			dy=-5;
		}
		
		if(codigo == KeyEvent.VK_DOWN) {
			dy=5;
		}
		
		if(codigo == KeyEvent.VK_LEFT) {
			dx=-5;
		}
		
		if(codigo == KeyEvent.VK_RIGHT) {
			dx=5;
		}
			
	}
	
	public void keyRelease(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP) {
			dy=0;
		}
		
		if(codigo == KeyEvent.VK_DOWN) {
			dy=0;
		}
		
		if(codigo == KeyEvent.VK_LEFT) {
			dx=0;
		}
		
		if(codigo == KeyEvent.VK_RIGHT) {
			dx=0;
		}
			
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return Imagem;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isTurbo() {
		return isTurbo;
	}

	public void setTurbo(boolean isTurbo) {
		this.isTurbo = isTurbo;
	}


	
	
	
	
	
	
}
