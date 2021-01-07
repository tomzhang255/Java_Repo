import java.util.ArrayList;
import java.util.Collections;

// a class to determine winning hand combinations

public class HandCombo {
    // instance variables
    private String card1, card2, card3, card4, card5;
    public String card1Face, card2Face, card3Face, card4Face, card5Face;
    private String card1Suit, card2Suit, card3Suit, card4Suit, card5Suit;
    public ArrayList<String> faces;

    // constructor
    public HandCombo(ArrayList<String> hand) {
        Collections.sort(hand);

        this.card1 = hand.get(0);
        this.card2 = hand.get(1);
        this.card3 = hand.get(2);
        this.card4 = hand.get(3);
        this.card5 = hand.get(4);

        this.card1Face = extractFaceValue(card1);
        this.card2Face = extractFaceValue(card2);
        this.card3Face = extractFaceValue(card3);
        this.card4Face = extractFaceValue(card4);
        this.card5Face = extractFaceValue(card5);

        this.card1Suit = extractSuit(card1);
        this.card2Suit = extractSuit(card2);
        this.card3Suit = extractSuit(card3);
        this.card4Suit = extractSuit(card4);
        this.card5Suit = extractSuit(card5);

        faces = new ArrayList<String>();
        faces.add(card1Face);
        faces.add(card2Face);
        faces.add(card3Face);
        faces.add(card4Face);
        faces.add(card5Face);
    }

    // instance methods

    // the ultimate method to use when determine winning hands
    public String determine() {
        // start checking from the highest rank
        if (this.isRoyalFlush()) {
            return "Royal Flush!";
        } else {
            if (this.isStraightFlush()) {
                return "Straight Flush!";
            } else {
                if (this.isFourOfAKind()) {
                    return "Four of a Kind!";
                } else {
                    if (this.isFullHouse()) {
                        return "Full House!";
                    } else {
                        if (this.isFlush()) {
                            return "Flush!";
                        } else {
                            if (this.isStraight()) {
                                return "Straight!";
                            } else {
                                if (this.isThreeOfAKind()) {
                                    return "Three of a Kind!";
                                } else {
                                    if (this.isTwoPairs()) {
                                        return "Two Pairs!";
                                    } else {
                                        if (this.isJacksOrBetter()) {
                                            return "Jacks or Better!";
                                        } else {
                                            return "You Lose!";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // breaking down the combinations
    public boolean isRoyalFlush() {
        // a special kind of straight flush
        return this.isStraightFlush() &&
                this.faces.contains("10") &&
                this.faces.contains("J") &&
                this.faces.contains("Q") &&
                this.faces.contains("K") &&
                this.faces.contains("A");
    }

    public boolean isStraightFlush() {
        // has to be a straight and a flush
        return this.isStraight() && this.isFlush();
    }

    public boolean isFourOfAKind() {
        // after sorting, cards with the same face should be right next to each other
        // so there are 2 ways to arrange four of a kind now
        return (card1Face.equals(card2Face) && card2Face.equals(card3Face) && card3Face.equals(card4Face)) ^
                (card2Face.equals(card3Face) && card3Face.equals(card4Face) && card4Face.equals(card5Face));
    }

    public boolean isFullHouse() {
        // three ways to arrange a full house after sorting
        return (card1Face.equals(card2Face) && card2Face.equals(card3Face) && card4Face.equals(card5Face)) ^
                // *unnecessary* (card2Face.equals(card3Face) && card3Face.equals(card4Face) && card1Face.equals(card5Face)) ^
                (card3Face.equals(card4Face) && card4Face.equals(card5Face) && card1Face.equals(card2Face));
    }

    public boolean isFlush() {
        // 5 cards of the same suit
        return card1Suit.equals(card2Suit) &&
                card2Suit.equals(card3Suit) &&
                card3Suit.equals(card4Suit) &&
                card4Suit.equals(card5Suit);
    }

    public boolean isStraight() {
        // five cards in a sequence
        // 9 combinations
        return (faces.contains("2") && faces.contains("3") && faces.contains("4") && faces.contains("5") && faces.contains("6")) ^
                (faces.contains("3") && faces.contains("4") && faces.contains("5") && faces.contains("6") && faces.contains("7")) ^
                (faces.contains("4") && faces.contains("5") && faces.contains("6") && faces.contains("7") && faces.contains("8")) ^
                (faces.contains("5") && faces.contains("6") && faces.contains("7") && faces.contains("8") && faces.contains("9")) ^
                (faces.contains("6") && faces.contains("7") && faces.contains("8") && faces.contains("9") && faces.contains("10")) ^
                (faces.contains("7") && faces.contains("8") && faces.contains("9") && faces.contains("10") && faces.contains("J")) ^
                (faces.contains("8") && faces.contains("9") && faces.contains("10") && faces.contains("J") && faces.contains("Q")) ^
                (faces.contains("9") && faces.contains("10") && faces.contains("J") && faces.contains("Q") && faces.contains("K")) ^
                (faces.contains("10") && faces.contains("J") && faces.contains("Q") && faces.contains("K") && faces.contains("A"));
    }

    public boolean isThreeOfAKind() {
        // three ways to arrange three of a kind after sorting
        return (card1Face.equals(card2Face) && card2Face.equals(card3Face)) ^
                (card2Face.equals(card3Face) && card3Face.equals(card4Face)) ^
                (card3Face.equals(card4Face) && card4Face.equals(card5Face));
    }

    public boolean isTwoPairs() {
        // now after sorting, each pair should be right next to each other
        // There are three ways to arrange 2 consecutive pairs
        return (card1Face.equals(card2Face) && card3Face.equals(card4Face)) ^
                (card2Face.equals(card3Face) && card4Face.equals(card5Face)) ^
                (card1Face.equals(card2Face) && card4Face.equals(card5Face));
    }

    public boolean isJacksOrBetter() {
        // identify a pair of Jacks of better
        // first, convert the array list of faces to a string
        String strFaces = "";
        for (int i = 0; i < 5; i++) {
            strFaces += faces.get(i);
        }

        return strFaces.contains("JJ") ^
                strFaces.contains("QQ") ^
                strFaces.contains("KK") ^
                strFaces.contains("AA");
    }

    // static methods

    public static String extractFaceValue(String card) {
        if (card.length() == 2) {
            return card.substring(0, 1);
        } else {
            return card.substring(0, 2);
        }
    }

    public static String extractSuit(String card) {
        return card.substring(card.length() - 1, card.length());
    }

}
