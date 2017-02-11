package kino;

import java.util.concurrent.Semaphore;

public class Barek extends Kasa {

    protected Semaphore semafor = new Semaphore(1, true);

    public void kupujePrzekaski(String nazwaKlienta) {
        System.out.println("Klient " + nazwaKlienta + " kupuje przek¹ski.");
    }
}