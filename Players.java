
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nathaniel
 */
public class Players {
    /*
     Creating a scanner
     */

    private Scanner kbd = new Scanner(System.in);

    /*
     Instance varibles
     */
    private String name; //Holds the players name
    private int card1, card2, total; //Holds their 2 cards and their total
    private int bet;//How much they bet
    private int chips;//How many chips they have
    private boolean cashedOut; //Wheather they have cashed out or not
    private String namedCard1, namedCard2; //Their cards name

    /**
     * Constructor for a player object
     *
     * @param name
     */
    Players(String name) {
        this.name = name; //user defined name
        this.card1 = 0; //until they draw a card
        this.card2 = 0;
        this.chips = 1000; //initial amount of chips
        this.total = card1 + card2; //their hand total
        this.cashedOut = false; //they start off not being cashed out
        this.bet = 0; // until they bet
        this.namedCard1 = null;
        this.namedCard2 = null;
    }

    /**
     * Constructor overload for if they don't supply a name, their name is
     * assumed to be "Player"
     */
    Players() {
        this("Player");
    }

    /**
     * Getter to return a name
     *
     * @return Their name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter to return their first card
     *
     * @return Their first card
     */
    public int getCard1() {
        return card1;
    }

    /**
     * Getter to return their second card
     *
     * @return their second card
     */
    public int getCard2() {
        return card2;
    }

    /**
     * Getter to return their total
     *
     * @return their total
     */
    public int getTotal() {
        return total;
    }

    /**
     * Getter for their number of chips
     *
     * @return how many chips they got
     */
    public int getChips() {
        return chips;
    }

    /**
     * Getter for if they are cashed out
     *
     * @return if they're cashed out
     */
    public boolean getCashedOut() {
        return cashedOut;
    }

    public String getNamed1() {
        return namedCard1;
    }

    public String getNamed2() {
        return namedCard2;
    }

    /**
     * Getter for how much they bet
     *
     * @return how much they bet
     */
    public int getBet() {
        return bet;
    }

    /**
     * Allows the user to make a bet
     *
     * @param num how much they have bet
     */
    public void setBet(int num) {
        /*
         While they try to bet more chips then they have or 
         bet a negative amount...
         */
        while (num > chips || num < 0) {
            /*
             Ask for a new bet amount
             */
            System.out.println("Please enter a positivie number that's less"
                    + " then your current number of chips");
            num = kbd.nextInt();
            kbd.nextLine();
        }
        /*
         Set their bet to how much they asked
         */
        bet = num;
    }

    /**
     * Takes away chips
     *
     * @param num how many chips to take away
     */
    public void takeChips(int num) {
        chips -= num;
    }

    /**
     * Adds chips
     *
     * @param num how many chips to add
     */
    public void addChips(int num) {
        chips += num;
    }

    /**
     * Sets the first card to something (preferably randomly generated)
     *
     * @param num what to set their first card to
     */
    public void setCard1(int num) {
        card1 = num;
    }

    public void nameCard1() {
        namedCard1 = CardsDealing.cardNames(card1);
    }

    /**
     * Sets the Second card to something (preferably randomly generated)
     *
     * @param num what to set their second card to
     */
    public void setCard2(int num) {
        card2 = num;
    }

    public void nameCard2() {
        namedCard2 = CardsDealing.cardNames(card2);
    }

    /**
     * Adding to their hand total
     *
     * @param num
     */
    public void setTotal(int num) {
        total = num;
    }

    /**
     * Letting a player cash out
     */
    public void cashOut() {
        cashedOut = true;
    }
}
