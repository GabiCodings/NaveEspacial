package naveespacial.Modelo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;

import java.awt.Graphics2D;

public class Fase extends JPanel implements ActionListener {

	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Enemy1> enemy1;
	private List<Stars> stars;
	private boolean emJogo;
	
	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);
		
		ImageIcon referencia = new ImageIcon("res\\Background.png");
		fundo = referencia.getImage();
		
		player = new Player();
		player.load();
		
		addKeyListener(new TecladoAdpter());
		
		timer = new Timer(10, this);
		timer.start();
		
		InicializaInimigo();
		InicializaStar();
		emJogo = true;
	}
	
	public void InicializaInimigo() {
		int cordenadas[] = new int[40];
		enemy1 = new ArrayList<Enemy1>();
		
		 for (int i = 0; i < cordenadas.length; i++ ) {
			int x = (int)(Math.random() * 8000+1024); 
			int y = (int)(Math.random() * 650+30);
			enemy1.add(new Enemy1(x, y));
		 }
		
	}
	
	public void InicializaStar() {
		int cordenadas[] = new int[400];
		stars = new ArrayList<Stars>();
		

		 for (int i = 0; i < cordenadas.length; i++ ) {
			int x = (int)(Math.random() * 1024 + 0); 
			int y = (int)(Math.random() * 768+ 0);
			stars.add(new Stars(x, y));
		 }
	}
	
	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		if(emJogo == true ) {
			graficos.drawImage(fundo, 0, 0, null);
			
			for(int h = 0; h < stars.size(); h++) {
				Stars l = stars.get(h);
				l.load();
				graficos.drawImage(l.getImagem(), l.getX(), l.getY(), this);
			}
			
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
			
			List<Tiro> tiros = player.getTiros();
			for(int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(),m.getY(), this);
					}
			
			for (int o = 0; o <enemy1.size(); o++) {
				Enemy1 in = enemy1.get(o);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
		} else {
			ImageIcon fimJogo = new ImageIcon("res\\fimdejogo.png");
			graficos.drawImage(fimJogo.getImage(), 0,0, null);
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.update();
		if(player.isTurbo()) {
			timer.setDelay(1);
		}
		
		if(player.isTurbo() == false) {
			timer.setDelay(5);
		}
		
		for(int h = 0; h < stars.size(); h++) {
			Stars on = stars.get(h);
				if(on.isVisible()) {
					on.update();
				} else {
					stars.remove(h);
				}
		}
		
		List<Tiro> tiros = player.getTiros();
		for(int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
				if(m.isVisible()) {
					m.update();
					if(player.isTurbo()) {
						tiros.get(i).setVELOCIDADE(2);
					}
				} else {
					tiros.remove(i);
				}
		}
		
		for (int o = 0; o <enemy1.size(); o++) {
			Enemy1 in = enemy1.get(o);
				
				if(in.isVisible()) {
					in.update();
				} else {
					enemy1.remove(o);
				}
		}
		checarColisoes();
		repaint();
		
	}
	
	public void checarColisoes () {
		Rectangle formaNave = player.getBound();
		Rectangle formaEnemy1;
		Rectangle formaTiro;
		
		for(int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBound();
			
			if(formaNave.intersects(formaEnemy1)) {
				if(player.isTurbo()) {
					tempEnemy1.setVisible(false);
				} else {
					player.setVisible(false);
					tempEnemy1.setVisible(false);
					emJogo = false;
				}
				
			}
		}
		
		List<Tiro> tiros = player.getTiros();
		
		for(int j = 0; j < tiros.size(); j++) {
			Tiro tempTiro = tiros.get(j);
			formaTiro = tempTiro.getBound();
			
			for (int o = 0; o < enemy1.size(); o++) {
				
				Enemy1 tempEnemy1 = enemy1.get(o);
				formaEnemy1 = tempEnemy1.getBound();
				
				if(formaTiro.intersects(formaEnemy1)) {
					tempEnemy1.setVisible(false);
					tempTiro.setVisible(false);
				}
			}
		}
		
		
		
}
	
	private class TecladoAdpter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}
	}
}



