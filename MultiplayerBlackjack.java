
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
public class MultiplayerBlackjack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
         Initializing a Scanner for the keyboard
         */
        Scanner kbd = new Scanner(System.in);


        /*
         Introducing the program
         */
        System.out.println("Welcome to a game of blackjack to "
                + "start you'll each have 1000 chips\n");

        /*
         Getting the number of players
         */
        System.out.println("How many players are there today?");
        int numPlayers = kbd.nextInt();
        kbd.nextLine();
        while (numPlayers < 1) {
            System.out.println("Please enter a positive integer");
            numPlayers = kbd.nextInt();
            kbd.nextLine();
        }

        /*
         Creating an array for all the players to help keep track of 
         them in the future
         */
        Players[] players = new Players[numPlayers];

        /*
         Getting the names of all the players
         */
        for (int i = 0; i < players.length; i++) {
            System.out.println("\nPlease enter player " + (i + 1) + "'s "
                    + "name");
            String name = kbd.nextLine();
            players[i] = new Players(name);
        }


        /*
         Varible to see how many players are yet to cash out
         */
        int numOfPlayersIn = players.length;
        do {

            /*
             Creating the dealers 2 cards and their total
             */
            int dealerCard1 = CardsDealing.dealt();
            int dealerCard2 = CardsDealing.dealt();
            String dealerNamed1 = CardsDealing.cardNames(dealerCard1);
            String dealerNamed2 = CardsDealing.cardNames(dealerCard2);
            if (dealerCard1 != 1) {
                dealerCard1 = CardsDealing.cardValues(dealerCard1);
            } else {
                dealerCard1 = CardsDealing.dealerDecidingAce(dealerCard2);
            }
            if (dealerCard2 != 1) {
                dealerCard2 = CardsDealing.cardValues(dealerCard2);
            } else {
                dealerCard2 = CardsDealing.dealerDecidingAce(dealerCard1);
            }
            int dealerTotal = dealerCard1 + dealerCard2;

            /*
             Getting their bet amount
             */
            for (int i = 0; i < players.length; i++) {
                /*
                 If they are not cashed out...
                 */
                if (!players[i].getCashedOut()) {
                    /*
                     ...Ask how much they want to bet
                     */
                    System.out.println("\n" + players[i].getName()
                            + " currently has " + players[i].getChips()
                            + " chips");
                    System.out.println("How much would they like to bet?");
                    players[i].setBet(kbd.nextInt());
                    kbd.nextLine();
                    players[i].takeChips(players[i].getBet());
                }

            }

            /*
             Drawing each players cards
             */
            for (int i = 0; i < players.length; i++) {
                /*
                 If not cashed out...
                 */
                if (!players[i].getCashedOut()) {
                    /*
                     ...Draw 2 cards and total them
                     */
                    players[i].setCard1(CardsDealing.dealt());
                    players[i].setCard2(CardsDealing.dealt());
                    players[i].nameCard1();
                    players[i].nameCard2();

                    /*
                     Tell them their cards
                     */
                    System.out.println("\n" + players[i].getName()
                            + " your cards are " + players[i].getNamed1() + " and "
                            + players[i].getNamed2());
                    /*
                     Setting their cards to a value (only will change the value 
                     of the cards if it is a face card)
                     */
                    players[i].setCard1(CardsDealing.cardValues(players[i].getCard1()));
                    players[i].setCard2(CardsDealing.cardValues(players[i].getCard2()));
                    players[i].setTotal(players[i].getCard1()
                            + players[i].getCard2());
                    System.out.println("Totaling " + players[i].getTotal());
                }
            }

            /*
             Show the dealers first card
             */
            System.out.println("\nThe dealer shows a " + dealerNamed1
                    + " and a hidden card");

            for (int i = 0; i < players.length; i++) {

            }


            /*
             Asking players to hit or stay
             */
            for (int i = 0; i < players.length; i++) {
                /*
                 If not cashed out...
                 */
                if (!players[i].getCashedOut()) {
                    /*
                     Tell them it's their turn
                     */
                    System.out.println("\n*****" + players[i].getName()
                            + "'s turn******");

                    /*
                     If they got a natural blackjack...
                     */
                    if (players[i].getTotal() == 21) {
                        /*
                         ...tell them and add 0.5x their bet back (they will get
                         the other 2x when they win the hand)
                         */
                        System.out.println("Winner winner chicken dinner, "
                                + "BLACKJACK!");
                        players[i].addChips((int) (players[i].getBet() * 0.5));
                    }
                    /*
                     Ask them to hit or stay
                     */
                    String hit = CardsDealing.hitOrStay(players[i].getTotal());
                    /*
                     While they hit...
                     */
                    while (hit.equalsIgnoreCase("h")) {
                        /*
                         ...Draw a new card 
                         */
                        int playerCard3 = CardsDealing.dealt();
                        String named3 = CardsDealing.cardNames(playerCard3);
                        playerCard3 = CardsDealing.cardValues(playerCard3);
                        /*
                         Add the new card to their total
                         */
                        players[i].setTotal(playerCard3 + players[i].getTotal());
                        /*
                         Tell them their new card and total
                         */
                        System.out.println("Your new card is a " + named3
                                + " making your new total "
                                + players[i].getTotal());
                        /*
                         Ask them if they want to hit again
                         */
                        hit = CardsDealing.hitOrStay(players[i].getTotal());
                    }
                    /*
                     If they go over 21...
                     */
                    if (players[i].getTotal() > 21) {
                        /*
                         ...Tell them they busted
                         */
                        System.out.println("Sorry " + players[i].getName()
                                + " you busted");
                    }
                }
            }

            /*
             Creating the dealers total
             */
            dealerTotal = dealerCard1 + dealerCard2;
            /*
             Tell the players the dealers hidden card and their total
             */
            System.out.println("\nThe dealers hidden card was a "
                    + dealerNamed2 + " making their total "
                    + dealerTotal);

            /*
             While the dealers hand total is under 17...
             */
            while (dealerTotal < 17) {
                /*
                 ...Draw them a new card
                 */
                int dealerCard3 = CardsDealing.dealt();
                String dealerNamed3 = CardsDealing.cardNames(dealerCard3);
                if (dealerCard3 == 1) {
                    dealerCard3 = CardsDealing.dealerDecidingAce(dealerTotal);
                } else {
                    dealerCard3 = CardsDealing.cardValues(dealerCard3);
                }
                /*
                 Telling the user what the dealer drew and the dealers 
                 new total
                 */
                dealerTotal += dealerCard3;
                System.out.println("The dealer hits and draws a "
                        + dealerNamed3 + " now totaling "
                        + dealerTotal);
            }
            for (int i = 0; i < players.length; i++) {
                /*
                 If the players are not cashed out...
                 */
                if (!players[i].getCashedOut()) {
                    /*
                     ...Find out if they won the hand
                     */

                    /*
                     if they player busted...
                     */
                    if (players[i].getTotal() > 21) {
                        /*
                         Tell them nothing
                         */

                    } /*
                     Otherwise if the dealer busted...
                     */ else if (dealerTotal > 21) {
                        /*
                         ...Add 2 times their bet to their chips and...
                         */
                        players[i].addChips((int) (2 * players[i].getBet()));
                        /*
                         ...Tell the players
                         */
                        System.out.println("The dealer busted "
                                + players[i].getName() + " is awarded "
                                + players[i].getBet() + " chips");
                    } /*
                     Otherwise if the players hand total is greater then the 
                     dealers hand total...
                     */ else if (CardsDealing.playerWin(players[i].getTotal(),
                            dealerTotal) && players[i].getTotal() <= 21) {
                        /*
                         ...The player wins the hand and 2x their initial bet
                         */
                        players[i].addChips(players[i].getBet() * 2);
                        System.out.println(players[i].getName() + " won the hand "
                                + "with a " + players[i].getTotal()
                                + " to the dealers " + dealerTotal);

                    } /*
                     If the players hand total and the dealers 
                     hand total are the same...
                     */ else if (players[i].getTotal() == dealerTotal) {
                        /*
                         The hand results in a push, the user gets their 
                         initial bet back with no net gain or loss
                         */
                        System.out.println(players[i].getName()
                                + " the hand was a push, no chips awarded");
                        players[i].addChips(players[i].getBet());
                    } /*
                     The only other situation is the dealer wins the hand and 
                     the player does not get any chips back
                     */ else {
                        System.out.println("Sorry " + players[i].getName()
                                + " the dealer won with a " + dealerTotal
                                + " to your " + players[i].getTotal());
                    }

                }
            }

            String cashOut;
            for (int i = 0; i < players.length; i++) {
                /*
                 If the player is not cashed out...
                 */
                if (!players[i].getCashedOut()) {
                    if (players[i].getChips() != 0) {
                        /*
                         Ask them if they would like to cash out
                         */
                        System.out.println("Would " + players[i].getName()
                                + " like to cash out? (Y/N)");
                        cashOut = kbd.next();
                        kbd.nextLine();
                        /*
                         Ensure a Y/N answer
                         */
                        while (!cashOut.equalsIgnoreCase("y")
                                && !cashOut.equalsIgnoreCase("n")) {
                            System.out.println("Y or N only please");
                            cashOut = kbd.next();
                            kbd.nextLine();
                        }

                    } 
                    /*
                     If their chip total is 0...
                    */
                    else {
                        /*
                         ...Cash them out1
                        */
                        cashOut = "y";
                    }
                    /*
                     If they cash out...
                     */
                    if (cashOut.equalsIgnoreCase("y")) {
                        /*
                         ...Set them to cashed out...
                         */
                        players[i].cashOut();
                        /*
                         ...And take away 1 from the numbers of players still in
                         */
                        numOfPlayersIn--;
                    }
                }
            }

            for (int i = 0; i < players.length; i++) {
                /*
                 Reset all players totals to 0
                 */
                players[i].setTotal(0);
            }

        } /*
         Stop play when there are no more active players in
         */ while (numOfPlayersIn > 0);

        for (int i = 0; i < players.length; i++) {
            /*
             After everyone is out tell everyone how much they cashed out with
             */
            System.out.println(players[i].getName() + " cashed out with "
                    + players[i].getChips() + " chips");
        }
    }

}
