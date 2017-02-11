package kino;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Kasa {

	private int miejscaWSali;
	private boolean czyDopuszczony = true;
    private String[] tytulyFilmow = {"Ob-Sesja 2.0", "Harry Routter i serwerownia tajemnic", "W sieci z³a: TCP/IP", "W sieci z³a 2: powrót ISO OSI"};
    private String[] tytulyNiepelnoletni = {"Ob-Sesja 2.0", "Harry Routter i serwerownia tajemnic"};
    protected Semaphore semafor = new Semaphore(1, true);
    private Lock blokada = new ReentrantLock();
    
    public boolean czyJestKlient() {
    	if(semafor.availablePermits() == 0) {
    		return true;
    	} else return false;
    }

    public String[] pobierzTytuly() {
        return tytulyFilmow;
    }

    public String[] pobierzTytulyNiepelnoletni() {
        return tytulyNiepelnoletni;
    }

    public boolean sprawdzCzyDopuszczony(int wiek, String tytulFilmu) {
        if (tytulFilmu == "W sieci z³a: TCP/IP" || tytulFilmu == "W sieci z³a 2: powrót ISO OSI") {
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
                Symulacja.zajmijSiedzenie(sala.pobierzNumer());
                sala.ustawMiejsca(miejscaWSali);
                Thread.sleep((int) (Math.random() * 10000));
                czyMaBilet = true;
            } else {
                Thread.sleep((int) (Math.random() * 5000));
                System.out.println("Brak miejsc na film " + sala.pobierzTytul() + ". Klient opuszcza kino.");
                czyMaBilet = false;
            }
        } catch (InterruptedException e) {
            System.out.println("B³¹d");
        } finally {
            blokada.unlock();
        }
        return czyMaBilet;
    }
}
