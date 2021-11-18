package de.bank;

import java.util.List;

public class BankTest {

    public static void main(String[] args) {
        Bank bank = new Bank();

        List<Angesteller> angestellers = List.of(new Angesteller(bank, 0,1,20)
                ,new Angesteller(bank, 1,2,20)
                ,new Angesteller(bank, 2,0,20));

        System.out.println("Vorher");
        bank.kontostand();

        angestellers.forEach(Angesteller::start);
    }
}
