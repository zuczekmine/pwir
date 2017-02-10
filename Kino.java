package kino;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Kino {

    private Lock zamknijSale = new ReentrantLock();
    private int kolejkaBarek, numerSali;
    private int[] kolejkiSemafor = new int[4];
    private String[] saleTytuly = new String[4];
    protected Kasa kasa1;
    protected Kasa kasa2;
    protected Kasa kasa3;
    protected Kasa kasa4;
    protected Barek barek;
    protected Sala sala1;
    protected Sala sala2;
    protected Sala sala3;
    protected Sala sala4;
    protected Sala[] sale;

    public Kino(Kasa kasa1, Kasa kasa2, Kasa kasa3, Kasa kasa4, Sala sala1, Sala sala2, Sala sala3, Sala sala4, Sala[] sale, Barek barek) {
        this.kasa1 = kasa1;
        this.kasa2 = kasa2;
        this.kasa3 = kasa3;
        this.kasa4 = kasa4;
        this.sala1 = sala1;
        this.sala2 = sala2;
        this.sala3 = sala3;
        this.sala4 = sala4;
        this.barek = barek;
        this.sale = sale;
    }

    public int[] pobierzLiczbeOsob() {
        kolejkiSemafor[0] = kasa1.semafor.getQueueLength();
        kolejkiSemafor[1] = kasa2.semafor.getQueueLength();
        kolejkiSemafor[2] = kasa3.semafor.getQueueLength();
        kolejkiSemafor[3] = kasa4.semafor.getQueueLength();
        return kolejkiSemafor;
    }

    public void zacznijFilm(int nrSali) {
        try {
            if (nrSali == 1) {
                if (sale[0].pobierzMiejsca() == 0) {
                    System.out.println("Film " + sale[0].pobierzTytul() + " rozpoczyna siê w sali " + sale[0].pobierzNumer());
                    Thread.sleep(sale[0].pobierzCzas());
                    System.out.println("Film " + sale[0].pobierzTytul() + " zakoñczy³ siê w sali " + sale[0].pobierzNumer());
                    sale[0].ustawMiejsca(16);
                }
            }
            if (nrSali == 2) {
                if (sale[1].pobierzMiejsca() == 0) {
                    System.out.println("Film " + sale[1].pobierzTytul() + " rozpoczyna siê w sali " + sale[1].pobierzNumer());
                    Thread.sleep(sale[1].pobierzCzas());
                    System.out.println("Film " + sale[1].pobierzTytul() + " zakoñczy³ siê w sali " + sale[1].pobierzNumer());
                    sale[1].ustawMiejsca(16);
                }
            }
            if (nrSali == 3) {
                if (sale[2].pobierzMiejsca() == 0) {
                    System.out.println("Film " + sale[2].pobierzTytul() + " rozpoczyna siê w sali " + sale[2].pobierzNumer());
                    Thread.sleep(sale[2].pobierzCzas());
                    System.out.println("Film " + sale[2].pobierzTytul() + " zakoñczy³ siê w sali " + sale[2].pobierzNumer());
                    sale[2].ustawMiejsca(16);
                }
            }
            if (nrSali == 4) {
                if (sale[3].pobierzMiejsca() == 0) {
                    System.out.println("Film " + sale[3].pobierzTytul() + " rozpoczyna siê w sali " + sale[3].pobierzNumer());
                    Thread.sleep(sale[3].pobierzCzas());
                    System.out.println("Film " + sale[3].pobierzTytul() + " zakoñczy³ siê w sali " + sale[3].pobierzNumer());
                    sale[3].ustawMiejsca(16);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("B³¹d LAMUSIE");
        } finally {
        }
    }

    public Sala sprawdzSale(String tytulFilmu) {
        for (int i = 0; i < sale.length; i++) {
            saleTytuly[i] = sale[i].pobierzTytul();
        }
        for (int j = 0; j < sale.length; j++) {
            if (saleTytuly[j] == tytulFilmu) {
                numerSali = j;
                break;
            }
        }
        return sale[numerSali];
    }

    public void wejdzDoSali(String nazwaKlienta, Sala sala) {
        System.out.println("Klient " + nazwaKlienta + " kieruje siê do sali " + sala.pobierzNumer());
    }

    public int pobierzOsobyBarek() {
        kolejkaBarek = barek.semafor.getQueueLength();
        return kolejkaBarek;
    }

    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
			@Override
			public void run(){
				try{
				new Symulacja("Symulator kina");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		});
    	try {
    		Thread.sleep(3000);
    	} catch (InterruptedException e) {
        }
    	Random priorytet = new Random();
        Kasa kasa1 = new Kasa();
        Kasa kasa2 = new Kasa();
        Kasa kasa3 = new Kasa();
        Kasa kasa4 = new Kasa();
        Sala sala1 = new Sala("Ob-Sesja 2.0", 1, 2, 30000);
        Sala sala2 = new Sala("Harry Routter i serwerownia tajemnic", 2, 2, 25000);
        Sala sala3 = new Sala("W sieci z³a: TCP/IP", 3, 2, 35000);
        Sala sala4 = new Sala("W sieci z³a: powrót ISO OSI", 4, 2, 27000);
        Sala[] sale = {sala1, sala2, sala3, sala4};
        Barek barek = new Barek();
        Kino kino = new Kino(kasa1, kasa2, kasa3, kasa4, sala1, sala2, sala3, sala4, sale, barek);
        Klient[] klienci = new Klient[70];
        Rozklad rozklad = new Rozklad();
        rozklad.start();
        Sala1Organizacja sala1Demon = new Sala1Organizacja(kino);
        sala1Demon.start();
        Sala2Organizacja sala2Demon = new Sala2Organizacja(kino);
        sala2Demon.start();
        Sala3Organizacja sala3Demon = new Sala3Organizacja(kino);
        sala3Demon.start();
        Sala4Organizacja sala4Demon = new Sala4Organizacja(kino);
        sala4Demon.start();
        for (int i = 0; i < klienci.length; i++) {
            klienci[i] = new Klient("K"+i, kino);
            if (priorytet.nextDouble() > 0.90) {
            	klienci[i].setPriority(Thread.MAX_PRIORITY);
            	System.out.println("Przyby³ klient K"+i+" posiadaj¹cy rezerwacjê");
            }
            klienci[i].start();
            try {
                Thread.sleep((int) (Math.random() * 5000));
            } catch (InterruptedException e) {
            }
        }
    }
}

class Sala1Organizacja extends Thread {

    private Kino kino;
    private int nrSali1;

    public Sala1Organizacja(Kino kino) {
        this.kino = kino;
        setDaemon(true);
    }

    public void run() {
        try {
            nrSali1 = kino.sala1.pobierzNumer();
            Thread.sleep(10000);
            while (true) {
                kino.zacznijFilm(nrSali1);
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.out.println("BÂ³Â¹d LAMUSIE");
        }
    }
}

class Sala2Organizacja extends Thread {

    private Kino kino;
    private int nrSali2;

    public Sala2Organizacja(Kino kino) {
        this.kino = kino;
        setDaemon(true);
    }

    public void run() {
        try {
            nrSali2 = kino.sala2.pobierzNumer();
            Thread.sleep(10000);
            while (true) {
                kino.zacznijFilm(nrSali2);
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.out.println("BÂ³Â¹d LAMUSIE");
        }
    }
}

class Sala3Organizacja extends Thread {

    private Kino kino;
    private int nrSali3;

    public Sala3Organizacja(Kino kino) {
        this.kino = kino;
        setDaemon(true);
    }

    public void run() {
        try {
            nrSali3 = kino.sala3.pobierzNumer();
            Thread.sleep(10000);
            while (true) {
                kino.zacznijFilm(nrSali3);
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.out.println("BÂ³Â¹d LAMUSIE");
        }
    }
}

class Sala4Organizacja extends Thread {

    private Kino kino;
    private int nrSali4;

    public Sala4Organizacja(Kino kino) {
        this.kino = kino;
        setDaemon(true);
    }

    public void run() {
        try {
            nrSali4 = kino.sala4.pobierzNumer();
            Thread.sleep(10000);
            while (true) {
                kino.zacznijFilm(nrSali4);
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            System.out.println("BÂ³Â¹d LAMUSIE");
        }
    }
}

class Kasa {

    private String[] tytulyFilmow = {"Ob-Sesja 2.0", "Harry Routter i serwerownia tajemnic", "W sieci z³a: TCP/IP", "W sieci z³a 2: powrót ISO OSI"};
    private String[] tytulyNiepelnoletni = {"Ob-Sesja 2.0", "Harry Routter i serwerownia tajemnic"};
    private int miejscaWSali;
    private boolean czyDopuszczony = true;
    protected Semaphore semafor = new Semaphore(1, true);
    private Lock blokada = new ReentrantLock();
    private ZajmijSiedzenie zajmijSiedzenie;

    public String[] pobierzTytuly() {
        return tytulyFilmow;
    }

    public String[] pobierzTytulyNiepelnoletni() {
        return tytulyNiepelnoletni;
    }

    public boolean sprawdzCzyDopuszczony(int wiek, String tytulFilmu) {
        if (tytulFilmu == "W sieci z³a: TCP/IP" || tytulFilmu == "W sieci z³a: powrót ISO OSI") {
            if (wiek < 18) {
                czyDopuszczony = false;
            }
        }
        return czyDopuszczony;
    }

    public boolean kupuje(String nazwaKlienta, Sala sala, boolean czyMaBilet) { 
        blokada.lock();
        try {
            miejscaWSali = sala.pobierzMiejsca();
            if (miejscaWSali > 0) {
                miejscaWSali--;
                zajmijSiedzenie = new ZajmijSiedzenie(sala.pobierzNumer());
                sala.ustawMiejsca(miejscaWSali);
                Thread.sleep((int) (Math.random() * 10000));
                czyMaBilet = true;
            } else {
                Thread.sleep((int) (Math.random() * 5000));
                System.out.println("Brak miejsc na film " + sala.pobierzTytul() + ". Klient opuszcza kino.");
                czyMaBilet = false;
            }
        } catch (InterruptedException e) {
            System.out.println("BÂ³Â¹d LAMUSIE");
        } finally {
            blokada.unlock();
        }
        return czyMaBilet;
    }
}

class Klient extends Thread {

    private String tytulFilmu, nazwaKlienta;
    private boolean czyPrzekaski, czyPozwolono, czyMaBilet;
    private String[] tytulyDoWyboru;
    private int[] kolejki, pomocnicza;
    private int wiek, wybranaKolejka, minimalnaWartosc, osobyDoBarku;
    private Kasa kasa = new Kasa();
    private Kino kino;
    private Sala sala;
    protected Random losuj = new Random();

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

    public int WybierzNajmniejszaKolejke(int[] kolejki) {
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
            pomocnicza = kino.pobierzLiczbeOsob();
            kolejki = kino.pobierzLiczbeOsob();
            wybranaKolejka = WybierzNajmniejszaKolejke(kolejki);
            switch (wybranaKolejka) {
                case 0:
                    if (kino.kasa1.semafor.tryAcquire()) {
                        System.out.println("Klient " + nazwaKlienta + " podchodzi do kasy 1");
                    } else {
                        System.out.println("Klient " + nazwaKlienta + " czeka w kolejce do kasy 1");
                        kino.kasa1.semafor.acquire();
                    }
                    Thread.sleep((int) (Math.random() * 2000));
                    break;
                case 1:
                    if (kino.kasa2.semafor.tryAcquire()) {
                        System.out.println("Klient " + nazwaKlienta + " podchodzi do kasy 2");
                    } else {
                        System.out.println("Klient " + nazwaKlienta + " czeka w kolejce do kasy 2");
                        kino.kasa2.semafor.acquire();
                    }
                    Thread.sleep((int) (Math.random() * 2000));
                    break;
                case 2:
                    if (kino.kasa3.semafor.tryAcquire()) {
                        System.out.println("Klient " + nazwaKlienta + " podchodzi do kasy 3");
                    } else {
                        System.out.println("Klient " + nazwaKlienta + " czeka w kolejce do kasy 3");
                        kino.kasa3.semafor.acquire();
                    }
                    Thread.sleep((int) (Math.random() * 2000));
                    break;
                case 3:
                    if (kino.kasa4.semafor.tryAcquire()) {
                        System.out.println("Klient " + nazwaKlienta + " podchodzi do kasy 4");
                    } else {
                        System.out.println("Klient " + nazwaKlienta + " czeka w kolejce do kasy 4");
                        kino.kasa4.semafor.acquire();
                    }

                    Thread.sleep((int) (Math.random() * 2000));
                    break;
            }

            czyPozwolono = kasa.sprawdzCzyDopuszczony(wiek, tytulFilmu);
            if (czyPozwolono == false) {
                System.out.println("Klient " + nazwaKlienta + " jest niepe³noletni - musi wybraæ inny film");
                tytulyDoWyboru = kasa.pobierzTytulyNiepelnoletni();
                tytulFilmu = tytulyDoWyboru[losuj.nextInt(tytulyDoWyboru.length)];
                sala = kino.sprawdzSale(tytulFilmu);
                System.out.println("Klient " + nazwaKlienta + " kupuje bilet....");
                czyMaBilet = kasa.kupuje(nazwaKlienta, sala, czyMaBilet);
            } else {
                sala = kino.sprawdzSale(tytulFilmu);
               
                System.out.println("Klient " + nazwaKlienta + " kupuje bilet....");
                czyMaBilet = kasa.kupuje(nazwaKlienta, sala, czyMaBilet);
            }

        } catch (InterruptedException e) {
            System.out.println("BÂ³Â¹d LAMUSIE");
        } finally {
            switch (wybranaKolejka) {
                case 0:
                    kino.kasa1.semafor.release();
                    System.out.println("Klient " + nazwaKlienta + " odchodzi od kasy 1");
                    break;
                case 1:
                    kino.kasa2.semafor.release();
                    System.out.println("Klient " + nazwaKlienta + " odchodzi od kasy 2");
                    break;
                case 2:
                    kino.kasa3.semafor.release();
                    System.out.println("Klient " + nazwaKlienta + " odchodzi od kasy 3");
                    break;
                case 3:
                    kino.kasa4.semafor.release();
                    System.out.println("Klient " + nazwaKlienta + " odchodzi od kasy 4");
                    break;
            }
        }
        if (czyMaBilet) {
            try {
                osobyDoBarku = kino.barek.semafor.getQueueLength();
                if (osobyDoBarku < 6) {
                    kino.barek.semafor.acquire();
                    System.out.println("Klient " + nazwaKlienta + " idzie kupiæ przek¹ski");
                    zmienCzyPrzekaski(true);
                    Thread.sleep((int) (Math.random() * 5000));
                    kino.barek.semafor.release();
                }
            } catch (InterruptedException e) {
                System.out.println("BÂ³Â¹d LAMUSIE");
            } finally {
            	kino.wejdzDoSali(nazwaKlienta, sala);
            }
        }
    }
}

class Rozklad extends Thread {

    private String[] rozklad;
    private Kasa kasa = new Kasa();

    public Rozklad() {
        setDaemon(true);
        rozklad = kasa.pobierzTytuly();
    }

    public void run() {
        while (true) {
            System.out.println("Aktualny rozk³ad:");
            for (int i = 0; i < rozklad.length; i++) {
                System.out.println("Sala " + (i + 1) + ": " + rozklad[i]);
            }
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                System.out.println("BÂ³Â¹d LAMUSIE");
            }
        }
    }
}

class Sala {

    private String tytulFilmu;
    private int liczbaMiejsc, dlugoscFilmu, numerSali;

    public Sala(String tytulFilmu, int numerSali, int liczbaMiejsc, int dlugoscFilmu) {
        this.tytulFilmu = tytulFilmu;
        this.numerSali = numerSali;
        this.liczbaMiejsc = liczbaMiejsc;
        this.dlugoscFilmu = dlugoscFilmu;
    }

    public int pobierzNumer() {
        return numerSali;
    }

    public String pobierzTytul() {
        return tytulFilmu;
    }

    public int pobierzMiejsca() {
        return liczbaMiejsc;
    }

    public int pobierzCzas() {
        return dlugoscFilmu;
    }

    public void ustawMiejsca(int liczbaMiejsc) {
        this.liczbaMiejsc = liczbaMiejsc;
    }
}

class Barek extends Kasa {

    protected Semaphore semafor = new Semaphore(1, true);

    public void kupujePrzekaski(String nazwaKlienta) {
        System.out.println("Klient " + nazwaKlienta + " kupuje przek¹ski.");
    }
}
