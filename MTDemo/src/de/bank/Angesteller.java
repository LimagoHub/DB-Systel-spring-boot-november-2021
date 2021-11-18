package de.bank;

public class Angesteller extends  Thread{

    private final Bank bank;
    private final int von;
    private final int nach;
    private final int betrag;


    public Angesteller(Bank bank, int von, int nach, int betrag) {
        this.bank = bank;
        this.von = von;
        this.nach = nach;
        this.betrag = betrag;
    }

    @Override
    public void run() {
        bank.ueberweisen(von,nach,betrag);
    }
}
