package kino;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Obrazek extends JPanel {

	private BufferedImage obraz;

	public Obrazek(String nazwaPliku) {
		super();
		File plikObraz = new File(nazwaPliku);
		try {
			obraz = ImageIO.read(plikObraz);
		} catch (IOException e) {
			System.err.println("B³ad odczytu obrazka");
			e.printStackTrace();
		}

		Dimension wymiar = new Dimension(obraz.getWidth(), obraz.getHeight());
		setPreferredSize(wymiar);
	}
	public void paintComponent(Graphics g) {
		Graphics2D grafika = (Graphics2D) g;
		grafika.drawImage(obraz, 0, 0, this);
	}
}