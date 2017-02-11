package kino;

import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Klient extends Thread {

	private int wiek, wybranaKolejka, minimalnaWartosc, osobyDoBarku;
	private int[] kolejki;
    private String tytulFilmu, nazwaKlienta;
    private String[] tytulyDoWyboru;
    private boolean czyPrzekaski, czyPozwolono, czyMaBilet;
    private Random losuj = new Random();
    private Kasa kasa = new Kasa();
    private Kino kino;
    private Sala sala;
    private JPanel[] obrazy;
    private JLabel[] numery;

    public Klient(String nazwaKlienta, Kino kino) {
        this.kino = kino;
        this.nazwaKlienta = nazwaKlienta;
        czyPrzekaski = false; //Czy przekÃ‚Â¹ski z this???
        czyMaBilet = false;
        tytulyDoWyboru = kasa.pobierzTytuly();
        tytulFilmu = tytulyDoWyboru[losuj.nextInt(tytulyDoWyboru.length)];
        wiek = losuj.nextInt(99);
    }

    public void wchodzi() {
        System.out.println("Klient " + nazwaKlienta + " wchodzi do kina");
    }

    public void zmienCzyPrzekaski(boolean czyPrzekaski) {
        this.czyPrzekaski = czyPrzekaski;
    }

    public int wybierzNajmniejszaKolejke(int[] kolejki) {
        int minimalnaWartosc = kolejki[0];
        int pomoc = 0;
        
        for (int i = 0; i < kolejki.length; i++) {
            if (kolejki[i] < minimalnaWartosc) {
                minimalnaWartosc = kolejki[i];
                pomoc = i;
            }
        }
        return pomoc;
    }

    public void run() {
        try {
            wchodzi();
            kolejki = kino.pobierzLiczbeOsob();
            wybranaKolejka = wybierzNajmniejszaKolejke(kolejki);
            obrazy = Symulacja.pobierzObrazy();
            numery = Symulacja.pobierzNumery();
            switch (wybranaKolejka) {
                case 0:
                    if (kino.kasa1.semafor.tryAcquire()) {
                        System.out.println("Klient " + nazwaKlienta + " podchodzi do kasy 1");
                    } else {
                        System.out.println("Klient " + nazwaKlienta + " czeka w kolejce do kasy 1");
                        kino.kasa1.semafor.acquire();
                        System.out.println("Klient " + nazwaKlienta + " podchodzi do kasy 1");
                    }
                    numery[0].setText("Klient "+nazwaKlienta);
                    obrazy[0].setVisible(false);
                    obrazy[1].setVisible(true);
                    Thread.sleep((int) (Math.random() * 10000));
                    break;
                case 1:
                    if (kino.kasa2.semafor.tryAcquire()) {
                        System.out.println("Klient " + nazwaKlienta + " podchodzi do kasy 2");
                    } else {
                        System.out.println("Klient " + nazwaKlienta + " czeka w kolejce do kasy 2");
                        kino.kasa2.semafor.acquire();
                        System.out.println("Klient " + nazwaKlienta + " podchodzi do kasy 2");
                    }
                    numery[1].setText("Klient "+nazwaKlienta);
                    obrazy[2].setVisible(false);
                    obrazy[3].setVisible(true);
                    Thread.sleep((int) (Math.random() * 10000));
                    break;
                case 2:
                    if (kino.kasa3.semafor.tryAcquire()) {
                        System.out.println("Klient " + nazwaKlienta + " podchodzi do kasy 3");
                    } else {
                        System.out.println("Klient " + nazwaKlienta + " czeka w kolejce do kasy 3");
                        kino.kasa3.semafor.acquire();
                        System.out.println("Klient " + nazwaKlienta + " podchodzi do kasy 3");
                    }
                    numery[2].setText("Klient "+nazwaKlienta);
                    obrazy[4].setVisible(false);
                    obrazy[5].setVisible(true);
                    Thread.sleep((int) (Math.random() * 10000));
                    break;
                case 3:
                    if (kino.kasa4.semafor.tryAcquire()) {
                        System.out.println("Klient " + nazwaKlienta + " podchodzi do kasy 4");
                    } else {
                        System.out.println("Klient " + nazwaKlienta + " czeka w kolejce do kasy 4");
                        kino.kasa4.semafor.acquire();
                        System.out.println("Klient " + nazwaKlienta + " podchodzi do kasy 4");
                    }
                    numery[3].setText("Klient "+nazwaKlienta);
                    obrazy[6].setVisible(false);
                    obrazy[7].setVisible(true);
                    Thread.sleep((int) (Math.random() * 10000));
                    break;
            }

            czyPozwolono = kasa.sprawdzCzyDopuszczony(wiek, tytulFilmu);
            if (czyPozwolono == false) {
                System.out.println("Klient " + nazwaKlienta + " jest niepe³noletni - musi wybraæ inny film");
                tytulyDoWyboru = kasa.pobierzTytulyNiepelnoletni();
                tytulFilmu = tytulyDoWyboru[losuj.nextInt(tytulyDoWyboru.length)];
                sala = kino.sprawdzSale(tytulFilmu);
                System.out.println("Klient " + nazwaKlienta + " kupuje bilet na film "+ tytulFilmu + " w sali " + sala.pobierzNumer());
                czyMaBilet = kasa.kupuje(nazwaKlienta, sala, czyMaBilet);
            } else {
                sala = kino.sprawdzSale(tytulFilmu);
                System.out.println("Klient " + nazwaKlienta + " kupuje bilet na film "+ tytulFilmu + " w sali " + sala.pobierzNumer());
                czyMaBilet = kasa.kupuje(nazwaKlienta, sala, czyMaBilet);
            }
            
            switch (wybranaKolejka) {
            case 0:
            	obrazy[0].setVisible(true);
                obrazy[1].setVisible(false);
                numery[0].setText("");
                System.out.println("Klient " + nazwaKlienta + " odchodzi od kasy 1");
                Thread.sleep(1000);
                kino.kasa1.semafor.release();
                break;
            case 1:
            	obrazy[2].setVisible(true);
                obrazy[3].setVisible(false);
                numery[1].setText("");
                System.out.println("Klient " + nazwaKlienta + " odchodzi od kasy 2");
                Thread.sleep(1000);
                kino.kasa2.semafor.release();
                break;
            case 2:
            	obrazy[4].setVisible(true);
                obrazy[5].setVisible(false);
                numery[2].setText("");
                System.out.println("Klient " + nazwaKlienta + " odchodzi od kasy 3");
                Thread.sleep(1000);
                kino.kasa3.semafor.release();
                break;
            case 3:
            	obrazy[6].setVisible(true);
                obrazy[7].setVisible(false);
                numery[3].setText("");
                System.out.println("Klient " + nazwaKlienta + " odchodzi od kasy 4");
                Thread.sleep(1000);
                kino.kasa4.semafor.release();
                break;
        }

        } catch (InterruptedException e) {
            System.out.println("B³¹d");
        }
        if (czyMaBilet) {
            try {
                osobyDoBarku = kino.barek.semafor.getQueueLength();
                if (osobyDoBarku < 6) {
                    kino.barek.semafor.acquire();
                    System.out.println("Klient " + nazwaKlienta + " idzie kupiæ przek¹ski");
                    numery[4].setText("Klient "+nazwaKlienta);
                    obrazy[8].setVisible(false);
                    obrazy[9].setVisible(true);
                    zmienCzyPrzekaski(true);
                    Thread.sleep((int) (Math.random() * 5000));
                    obrazy[8].setVisible(true);
                    obrazy[9].setVisible(false);
                    numery[4].setText("");
                    Thread.sleep(1000);
                    kino.barek.semafor.release();
                }
            } catch (InterruptedException e) {
                System.out.println("B³¹d");
            } finally {
            	kino.wejdzDoSali(nazwaKlienta, sala);
            }
        }
    }
}