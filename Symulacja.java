package kino;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import javax.swing.*;
import javax.swing.text.DefaultCaret;


public class Symulacja extends JFrame { 
	
	private  static JLabel[] numery = {new JLabel("", SwingConstants.CENTER), new JLabel("", SwingConstants.CENTER), 
			new JLabel("",SwingConstants.CENTER), new JLabel("",SwingConstants.CENTER), new JLabel("",SwingConstants.CENTER)};
	private static JPanel[] obrazy = {new Obrazek("src/kino/kasaPusta1.png"),new Obrazek("src/kino/kasa1K.png"),new Obrazek("src/kino/kasaPusta2.png"),
	new Obrazek("src/kino/kasa2K.png"), new Obrazek("src/kino/kasaPusta3.png"),new Obrazek("src/kino/kasa3K.png"),new Obrazek("src/kino/kasaPusta4.png"),
	new Obrazek("src/kino/kasa4K.png"),new Obrazek("src/kino/barekPusty.png"),new Obrazek("src/kino/barekK.png")};
	private static JPanel[] siedzeniaSala1, siedzeniaSala2, siedzeniaSala3, siedzeniaSala4;
	private JPanel panelGlowny, panelKasy, panelMiejsca, panelKlienci, panelKonsola, kasa1, kasa2, kasa3, kasa4, barek, sala1, sala2, sala3, sala4;
	private JLabel tytulSala1, tytulSala2, tytulSala3, tytulSala4;
	private JScrollPane suwak;
	private Kasa[] kasy = new Kasa[4];
	private final JTextArea POLE_TEKSTOWE = new JTextArea();
	
	public Symulacja(String nazwa, Kasa kasa1Kino, Kasa kasa2Kino, Kasa kasa3Kino, Kasa kasa4Kino) throws IOException {
		super(nazwa);
		
		kasy[0] = kasa1Kino;
		kasy[1] = kasa2Kino;
		kasy[2] = kasa3Kino;
		kasy[3] = kasa4Kino;

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
		
		kasa1.add(obrazy[0]);
		kasa1.add(obrazy[1]);
		
		kasa2.add(obrazy[2]);
		kasa2.add(obrazy[3]);
		
		kasa3.add(obrazy[4]);
		kasa3.add(obrazy[5]);
		
		kasa4.add(obrazy[6]);
		kasa4.add(obrazy[7]);
		
		barek.add(obrazy[8]);
		barek.add(obrazy[9]);
		
		panelKasy.setLayout(new GridLayout(1,5,0,0));
		
		panelKasy.add(kasa1);
		panelKasy.add(kasa2);
		panelKasy.add(kasa3);
		panelKasy.add(kasa4);
		panelKasy.add(barek);
		
		panelKonsola.setBackground(Color.DARK_GRAY);
		POLE_TEKSTOWE.setEditable(false);
		suwak = new JScrollPane (POLE_TEKSTOWE);
		suwak.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		suwak.setPreferredSize(new Dimension(590, 340));
		DefaultCaret caret = (DefaultCaret)POLE_TEKSTOWE.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		przekazKonsoleDo(POLE_TEKSTOWE);
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
		
		panelKlienci.add(numery[0]);
		panelKlienci.add(numery[1]);
		panelKlienci.add(numery[2]);
		panelKlienci.add(numery[3]);
		panelKlienci.add(numery[4]);
		
		panelGlowny.add(panelKasy, BorderLayout.NORTH);
		panelGlowny.add(panelKlienci, BorderLayout.NORTH);
		panelGlowny.add(panelKonsola, BorderLayout.CENTER);
		panelGlowny.add(panelMiejsca, BorderLayout.EAST);
		
		add(panelGlowny);

		setSize(930, 645);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); 
		
    }
	
	public static JPanel[] pobierzObrazy() {
		return obrazy;
	}
	
	public static JLabel[] pobierzNumery() {
		return numery;
	}
    
    public static JPanel[] pobierzSiedzeniaSale(int nrSali) {
    	JPanel[] sala = new JPanel[16];
    	if (nrSali == 1) sala = siedzeniaSala1;
    	if (nrSali == 2) sala = siedzeniaSala2;
    	if (nrSali == 3) sala = siedzeniaSala3;
    	if (nrSali == 4) sala = siedzeniaSala4;
    	return sala;
    }
    
    public static void zajmijSiedzenie(int nrSali) {
    	int miejsce;
    	Random wybierz = new Random();
    	JPanel[] siedzeniaSala = new JPanel[16];
    	siedzeniaSala = pobierzSiedzeniaSale(nrSali);
    	miejsce = wybierz.nextInt(siedzeniaSala.length);
    	while (siedzeniaSala[miejsce].getBackground().equals(Color.RED)) {
    		miejsce = wybierz.nextInt(siedzeniaSala.length);
    	}
    	siedzeniaSala[miejsce].setBackground(Color.RED);
    }
    
    public static void seans(int nrSali) {
    	JPanel[] siedzeniaSala = new JPanel[16];
    	siedzeniaSala = pobierzSiedzeniaSale(nrSali);
    	for (int i=0; i<siedzeniaSala.length; i++) {
    		siedzeniaSala[i].setBackground(Color.GREEN);
    	}
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
}