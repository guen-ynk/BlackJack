/*
 * @Author Guen Yanik 2020
 * @Blackjack Klasse fuer Blackjack Game in Java
 *
 */

import java.nio.charset.IllegalCharsetNameException;
import java.util.*;

/**
 * Klasse fuer Spieler ( Momentan nur 1 Spieler und 1 Dealer )
 */
class Account {
    int money = 5000;
    int count = 0;  // Handwerte
    ArrayList<Integer> hand = new ArrayList<>();
}

class BlackJack {

    private ArrayList<Integer> cards_new = new ArrayList<>(Arrays.asList(
            2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9, 9,
            9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11)
    );
    private ArrayList<Integer> cards = new ArrayList<>();
    private Account player;
    private Account dealer;

    BlackJack() {
        this.player = new Account();
        this.dealer = new Account();
    }

    void start() {
        boolean ingame = true;
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream

        while (ingame) {
            init_round();
            System.out.println("Einsatz bitte, aktuelles Guthaben: " + this.player.money);
            int einsatz = sc.nextInt();
            this.player.money = Math.max(0, this.player.money - einsatz);
            while (true) {
                System.out.println(this.player.hand.toString() + this.player.count + "\n" + this.dealer.hand.get(0));
                System.out.println("0: hit, 1:stay ");
                int choice = sc.nextInt();
                if (choice == 1) break;
                this.player.hand.add(this.cards.remove(0));
                this.player.count += this.player.hand.get(this.player.hand.size() - 1);
                if (this.player.count > 21) {
                    this.player.count = -1;
                    break;
                }

            }
            while (this.player.count != -1 && this.dealer.count < 17) {
                int x = this.cards.remove(0);
                this.dealer.hand.add(x);
                this.dealer.count += x;
            }
            System.out.println(this.player.hand.toString() + this.player.count + "\n" + this.dealer.hand.toString() + this.dealer.count);

            if (this.player.count < this.dealer.count && this.dealer.count < 22) {
                System.out.println("Dealer wins");
                if (this.player.money == 0) ingame = false;
            } else if (this.player.count == this.dealer.count) {
                System.out.println("push");
                this.player.money += einsatz;
            } else {
                System.out.println("you win");
                this.player.money += 2 * einsatz;
            }
        }
    }

    private void init_round() {
        this.cards = new ArrayList<>();
        this.cards.addAll(this.cards_new);
        this.dealer.hand.clear();
        this.player.hand.clear();
        System.out.println(this.cards.toString() + this.cards.size());

        Collections.shuffle(this.cards);

        this.dealer.hand.add(this.cards.remove(0));
        this.dealer.hand.add(this.cards.remove(0));
        this.dealer.count = this.dealer.hand.get(0) + this.dealer.hand.get(1);

        this.player.hand.add(this.cards.remove(0));
        this.player.hand.add(this.cards.remove(0));
        this.player.count = this.player.hand.get(0) + this.player.hand.get(1);
    }
}


public class Main {


    public static void main(String[] args) {
        System.out.println("Welcome to Blackjack");
        BlackJack test = new BlackJack();
        test.start();
    }
}
