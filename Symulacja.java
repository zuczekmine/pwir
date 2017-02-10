package kino;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;


public class Symulacja extends JFrame { 
	private JScrollPane suwak;
	private JPanel panelGlowny, panelKasy, panelMiejsca, panelKlienci, panelKonsola, kasa1, kasa2, kasa3, kasa4, barek, sala1, sala2, sala3, sala4;
	private static JPanel[] siedzeniaSala1, siedzeniaSala2, siedzeniaSala3, siedzeniaSala4;
	private JLabel tytulSala1, tytulSala2, tytulSala3, tytulSala4, nrKlientaKasa1, nrKlientaKasa2, nrKlientaKasa3, nrKlientaKasa4, nrKlientaBarek;
	private JProgressBar pasekSala1, pasekSala2, pasekSala3, pasekSala4;
	private final JTextArea poleTekstowe = new JTextArea();
	private static int indeksSala1 =0;
	private static int indeksSala2 =0;
	private static int indeksSala3 =0;
	private static int indeksSala4 =0;
	
	public Symulacja(String nazwa) throws IOException {
		super(nazwa);
		//pasek = new JProgressBar();

		panelGlowny = new JPanel();
		panelKasy = new JPanel();
		panelKasy.setPreferredSize(new Dimension(905, 210));
		panelKonsola = new JPanel();
		panelKonsola.setPreferredSize(new Dimension(600, 350));
		panelMiejsca = new JPanel();
		panelMiejsca.setPreferredSize(new Dimension(300, 350));
		panelKlienci = new JPanel();
		panelKlienci.setPreferredSize(new Dimension(905, 30));
		
		kasa1 = new JPanel();
		kasa2 = new JPanel();
		kasa3 = new JPanel();
		kasa4 = new JPanel();
		barek = new JPanel();
		
		JPanel obrazKasa1 = new Obrazek("src/kino/kasaPusta1.png");
		JPanel obrazKasa1K = new Obrazek("src/kino/kasa1K.png");
		kasa1.add(obrazKasa1);
		kasa1.add(obrazKasa1K);
		nrKlientaKasa1 = new JLabel("Klient numer 70");
		kasa1.add(nrKlientaKasa1, BorderLayout.SOUTH);
		
		JPanel obrazKasa2 = new Obrazek("src/kino/kasaPusta2.png");
		JPanel obrazKasa2K = new Obrazek("src/kino/kasa2K.png");
		
		kasa2.add(obrazKasa2);
		kasa2.add(obrazKasa2K);
		
		JPanel obrazKasa3 = new Obrazek("src/kino/kasaPusta3.png");
		JPanel obrazKasa3K = new Obrazek("src/kino/kasa3K.png");
		kasa3.add(obrazKasa3);
		kasa2.add(obrazKasa3K);
		
		JPanel obrazKasa4 = new Obrazek("src/kino/kasaPusta4.png");
		JPanel obrazKasa4K = new Obrazek("src/kino/kasa4K.png");
		kasa4.add(obrazKasa4);
		kasa2.add(obrazKasa4K);
		
		JPanel obrazBarek = new Obrazek("src/kino/barekPusty.png");
		JPanel obrazBarekK = new Obrazek("src/kino/barekK.png");
		barek.add(obrazBarek);
		kasa2.add(obrazBarekK);
		
		panelKasy.setLayout(new GridLayout(1,5,0,0));
		
		panelKasy.add(kasa1);
		panelKasy.add(kasa2);
		panelKasy.add(kasa3);
		panelKasy.add(kasa4);
		panelKasy.add(barek);
		
		panelKonsola.setBackground(Color.DARK_GRAY);
		poleTekstowe.setEditable(false);
		suwak = new JScrollPane (poleTekstowe);
		suwak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		suwak.setPreferredSize(new Dimension(590, 340));
		DefaultCaret caret = (DefaultCaret)poleTekstowe.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		przekazKonsoleDo(poleTekstowe);
		panelKonsola.add(suwak);
		
		panelMiejsca.setLayout(new GridLayout(2,2,5,5));
		
		siedzeniaSala1 = new JPanel[16];
		sala1 = new JPanel();
		tytulSala1 = new JLabel("Sala 1");
		sala1.setLayout(new GridLayout(5,4,2,2));
		for (int i=0 ; i<siedzeniaSala1.length; i++) {
			siedzeniaSala1[i]= new JPanel();
			siedzeniaSala1[i].setBackground(Color.GREEN);
			sala1.add(siedzeniaSala1[i]);
		}
		sala1.add(tytulSala1);
		
		siedzeniaSala2 = new JPanel[16];
		sala2 = new JPanel();
		tytulSala2 = new JLabel("Sala 2");
		sala2.setLayout(new GridLayout(5,4,2,2));
		for (int i=0 ; i<siedzeniaSala2.length; i++) {
			siedzeniaSala2[i]= new JPanel();
			siedzeniaSala2[i].setBackground(Color.GREEN);
			sala2.add(siedzeniaSala2[i]);
		}
		sala2.add(tytulSala2);
		
		siedzeniaSala3 = new JPanel[16];
		sala3 = new JPanel();
		tytulSala3 = new JLabel("Sala 3");
		sala3.setLayout(new GridLayout(5,4,2,2));
		for (int i=0 ; i<siedzeniaSala3.length; i++) {
			siedzeniaSala3[i]= new JPanel();
			siedzeniaSala3[i].setBackground(Color.GREEN);
			sala3.add(siedzeniaSala3[i]);
		}
		sala3.add(tytulSala3);
		
		siedzeniaSala4 = new JPanel[16];
		sala4 = new JPanel();
		tytulSala4 = new JLabel("Sala 4");
		sala4.setLayout(new GridLayout(5,4,2,2));
		for (int i=0 ; i<siedzeniaSala4.length; i++) {
			siedzeniaSala4[i]= new JPanel();
			siedzeniaSala4[i].setBackground(Color.GREEN);
			sala4.add(siedzeniaSala4[i]);
		}
		sala4.add(tytulSala4);
		
		panelMiejsca.add(sala1);
		panelMiejsca.add(sala2);
		panelMiejsca.add(sala3);
		panelMiejsca.add(sala4);
		
		panelKlienci.setLayout(new GridLayout(1,5,0,0));
		nrKlientaKasa1 = new JLabel("Klient 12");
		panelKlienci.add(nrKlientaKasa1);
		nrKlientaKasa2 = new JLabel("Klient 12");
		panelKlienci.add(nrKlientaKasa2);
		nrKlientaKasa3 = new JLabel("Klient 12");
		panelKlienci.add(nrKlientaKasa3);
		nrKlientaKasa4 = new JLabel("Klient 12");
		panelKlienci.add(nrKlientaKasa4);
		nrKlientaBarek = new JLabel("Klient 12");
		panelKlienci.add(nrKlientaBarek);
		
		panelGlowny.add(panelKasy, BorderLayout.NORTH);
		panelGlowny.add(panelKlienci, BorderLayout.NORTH);
		panelGlowny.add(panelKonsola, BorderLayout.CENTER);
		panelGlowny.add(panelMiejsca, BorderLayout.EAST);
		
		add(panelGlowny);

		setSize(930, 645);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); 
		
    }
    
    public static int pobierzIndeks(int nrSali) {
    	int indeks = 0;
    	if (nrSali == 1) indeks = indeksSala1;
    	if (nrSali == 2) indeks = indeksSala2;
    	if (nrSali == 3) indeks = indeksSala3;
    	if (nrSali == 4) indeks = indeksSala4;
    	return indeks;
    }
    
    public static JPanel[] pobierzSiedzeniaSale(int nrSali) {
    	JPanel[] sala = new JPanel[16];
    	if (nrSali == 1) sala = siedzeniaSala1;
    	if (nrSali == 2) sala = siedzeniaSala2;
    	if (nrSali == 3) sala = siedzeniaSala3;
    	if (nrSali == 4) sala = siedzeniaSala4;
    	return sala;
    }
    
    public static void zmienIndeks(int nrSali, int index) {
    	if (nrSali == 1 && indeksSala1 < 16) indeksSala1 = index;
    	if (nrSali == 2 && indeksSala2 < 16) indeksSala2 = index;
    	if (nrSali == 3 && indeksSala3 < 16) indeksSala3 = index;
    	if (nrSali == 4 && indeksSala4 < 16) indeksSala4 = index;
    }
    
    private void przekazKonsoleDo(final JTextArea poleTekstowe) {
        PrintStream wyjscie = new PrintStream(new ByteArrayOutputStream() {
            public synchronized void flush() throws IOException {
                poleTekstowe.setText(toString());
            }
        }, true);

        System.setErr(wyjscie);
        System.setOut(wyjscie);
    }
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run(){
				try{
				new Symulacja("Symulator kina");
			} catch (IOException e) {
				
			}
		}
		});
    }
}

class Konsola extends JScrollPane {
	
}

class Obrazek extends JPanel {

	private BufferedImage image;

	public Obrazek(String nazwaPliku) {
		super();

		File imageFile = new File(nazwaPliku);
		try {
			image = ImageIO.read(imageFile);
		} catch (IOException e) {
			System.err.println("Blad odczytu obrazka");
			e.printStackTrace();
		}

		Dimension dimension = new Dimension(image.getWidth(), image.getHeight());
		setPreferredSize(dimension);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, this);
	}
}

class ZajmijSiedzenie implements ChangeListener{
	private int nrSali, index;
	private boolean czyZajete;
	private JPanel[] siedzeniaWSalach;

	public ZajmijSiedzenie(int nrSali) {
		this.nrSali = nrSali;
	}
	
	public void stateChanged(ChangeEvent z) {
			index = Symulacja.pobierzIndeks(nrSali);
			siedzeniaWSalach = Symulacja.pobierzSiedzeniaSale(nrSali);
			siedzeniaWSalach[index].setBackground(Color.RED);
			index++;
			Symulacja.zmienIndeks(nrSali, index);
	}
}