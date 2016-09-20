
/**
 *
 * @author Nathaniel McIntyre
 */
import java.util.*;

public class CardsDealing {

    public static final Random r = new Random();
    public static final Scanner kbd = new Scanner(System.in);

    /**
     * decides a random number from 1-13 simulating the 13 different cards in a
     * suit for a deck
     *
     * @return card the value of the card
     */
    public static int dealt() {
        /*
         Selecting a random int between 1 and 13
         */
        return (int) (Math.random() * 13) + 1;
    }

    /**
     * Creates a string to simulate a face card I.E. 11 is a jack, 12 is a queen
     * and 13 is a king
     *
     * @param value A card value which is not defined by a normal card
     * @return the name of the card
     */
    public static String cardNames(int value) {
        /* 
         Creating and assigning a varible to hold the name of the card
         */
        String name;
        if(value == 2){
            name = "2";
        } else if (value == 3){
            name = "3";
        } else if (value == 4){
            name = "4";
        } else if (value == 5){
            name = "5";
        } else if (value == 6){
            name = "6";
        } else if (value == 7){
            name = "7";
        } else if (value == 8){
            name = "8";
        } else if (value == 9){
            name = "9";
        } else if (value == 10){
            name = "10";
        } else if (value == 11) {
            name = "jack";
        } else if (value == 12) {
            name = "queen";
        } else if (value == 13) {
            name = "king";
        } else {
            name = "ace";
        }
        return name;
    }

    /**
     * Gives a numeric value to a face card and if its an ace allows the user to
     * decide if its a 1 or an 11
     *
     * @param card the face card
     * @return the value of the card
     */
    public static int cardValues(int card) {
        int value;
        /*
         A non face card has the value of the original card
        */
        if(card < 11 && card != 1){
            value = card;
        }
        /*
         Setting a jack, queen or king's value to 10
         */
        else if (card >= 11) {
            value = 10;
        } /*
         Allowing the user to decide the value of their ace
         */ else {
            value = decidingAce();
        }
        return value;
    }

    /**
     * Asks the user to decide if they want the ace to count as a 1 or an 11
     *
     * @return the value chosen for the ace
     */
    public static int decidingAce() {
        System.out.println("Would you like the ace to count as a 1 or an 11?");
        int aceValue = kbd.nextInt();
        while (aceValue != 1 && aceValue != 11) {
            System.out.println("Please insert a value of 1 or 11");
            aceValue = kbd.nextInt();
        }
        return aceValue;
    }

    /**
     * The dealer deciding if it's ace should be a 1 or 11, if 11 + its current
     * total is over 21 then it will choose 1 otherwise it will choose 1
     *
     * @param dealerCard2 Their other card or total
     * @return either 1 or 11
     */
    public static int dealerDecidingAce(int dealerCard2) {
        int value;
        /*
         If the value of their hand would be over 21 then it will be 1 
         */
        if (dealerCard2 + 11 > 21) {
            value = 1;
        } /*
         Otherwise it will be 11
         */ else {
            value = 11;
        }
        return value;
    }

    /**
     * Asking the user if they want to hit or stay, in the case of their total
     * being over 21 it will automatically choose stay
     *
     * @param total the users current hand total
     * @return if they hit or stay
     */
    public static String hitOrStay(int total) {
        String hitOrStay;
        /*
         If the users hand total is less then 21...
         */
        if (total < 21) {
            /*
             ...Ask them for if they want to hit or stay
             */
            System.out.println("\nWould you like to hit or stay (type H or S)");
            hitOrStay = kbd.next();
            kbd.nextLine();
            /*
             Ensure that the user either enters an "H" or a "S" 
             */
            while (!hitOrStay.equalsIgnoreCase("h")
                    && !hitOrStay.equalsIgnoreCase("s")) {
                System.out.println("Please enter either a \"h\" or \"s\"");
                hitOrStay = kbd.next();
                kbd.nextLine();
            }
        } /*
         Otherwise force them to stay 
         */ else {
            return "s";
        }
        return hitOrStay;
    }

    /**
     * Printing out that they busted
     */
    public static void busted() {
        System.out.println("Sorry you busted better luck next hand");

    }

    /**
     * Deciding who won the hand
     *
     * @param playerTotal the users hand total
     * @param dealerTotal the dealers hand total
     * @return true if the user won, false if the dealer won
     */
    public static boolean playerWin(int playerTotal, int dealerTotal) {
        /*
         If the dealer has a 21 they automatically win
         */
        if (dealerTotal == 21) {
            return false;
        } /*
         If the players total makes it to 21 and the dealers total isn't 21 
         then the player wins
         */ else if (playerTotal == 21) {
            return true;
        } /*
         If the players hand total is greater then the dealers hand total then
         the player wins
         */ else if (playerTotal > dealerTotal) {
            return true;
        } /*
         Any other situation leads to the dealer winning  
         */ else {
            return false;
        }
    }

    /**
     * Determines if a card is a face card or not
     *
     * @param card the card it is determining
     * @return true if it's a face card, otherwise false
     */
    public static boolean isFaceCard(int card) {
        if (card > 10 || card == 1) {
            return true;
        } else {
            return false;
        }
    }

}
