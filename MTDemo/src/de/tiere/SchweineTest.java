package de.tiere;

public class SchweineTest {

    public static void main(String[] args) {
        Schwein piggy = new Schwein("Miss Piggy");
        System.out.println(piggy);
        piggy.fressen();
        System.out.println(piggy);

        while(piggy.getGewicht() < 11 ) {}
        System.out.println(piggy);
    }
}
