import java.util.ArrayList;
import java.util.Arrays;

// to test the HandCombo class

public class TestCombo {

    public static void main(String[] args) {
        ArrayList<String> hand = new ArrayList<>(
                Arrays.asList(
                        "AC", "AH", "10D", "10H", "AS"
                )
        );

        HandCombo playerHand = new HandCombo(hand);

        // expect Full House
        System.out.println(playerHand.determine());

    }

}
