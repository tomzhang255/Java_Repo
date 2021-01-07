import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


/**
 * Siyuan (Tom) Zhang
 * CS 219-01 Final Project
 * Video Poker Panel
 *
 * Note: make sure the PNG images of the cards are in
 * 		 the png directory of this java project folder
 */

@SuppressWarnings("serial")
public class VideoPokerPanel extends JPanel implements ActionListener {

    private JLabel result;
    private CardLabel card1, card2, card3, card4, card5;
    private ImageIcon icon1, icon2, icon3, icon4, icon5, iconBack;
    private JButton hold1, hold2, hold3, hold4, hold5, deal, reset;
    private int hold1Counter, hold2Counter, hold3Counter, hold4Counter, hold5Counter, dealCounter;
    private ArrayList<String> cards, hand;
    private ArrayList<Boolean> holdStatus;
    private Timer timer0, timer1, timer2, timer3, timer4, timer5, timer6;


    public VideoPokerPanel() {

        // initializing components of the panel

        iconBack = stringToImageIcon("green_back");

        card1 = new CardLabel(iconBack);
        card2 = new CardLabel(iconBack);
        card3 = new CardLabel(iconBack);
        card4 = new CardLabel(iconBack);
        card5 = new CardLabel(iconBack);
        result = new JLabel(" ", SwingConstants.CENTER);
        result.setForeground(Color.RED);

        hold1 = new JButton("Hold");
        hold2 = new JButton("Hold");
        hold3 = new JButton("Hold");
        hold4 = new JButton("Hold");
        hold5 = new JButton("Hold");
        deal = new JButton("Deal");
        reset = new JButton("Reset");

        hold1.addActionListener(this);
        hold2.addActionListener(this);
        hold3.addActionListener(this);
        hold4.addActionListener(this);
        hold5.addActionListener(this);
        deal.addActionListener(this);
        reset.addActionListener(this);

        // customizing layout

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1,3));

        northPanel.add(deal);
        northPanel.add(result);
        northPanel.add(reset);


        // used a border layout to divide the panel into three general sections
        super.setLayout(new BorderLayout());
        super.add(northPanel, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1, 5));

        centerPanel.add(card1.getLabel());
        centerPanel.add(card2.getLabel());
        centerPanel.add(card3.getLabel());
        centerPanel.add(card4.getLabel());
        centerPanel.add(card5.getLabel());

        super.add(centerPanel, BorderLayout.CENTER);


        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1,5));

        southPanel.add(hold1);
        southPanel.add(hold2);
        southPanel.add(hold3);
        southPanel.add(hold4);
        southPanel.add(hold5);

        super.add(southPanel, BorderLayout.SOUTH);

        // initializing array lists and button counters

        cards = new ArrayList<>(
                Arrays.asList(
                        "AC", "AD", "AH", "AS",
                        "2C", "2D", "2H", "2S",
                        "3C", "3D", "3H", "3S",
                        "4C", "4D", "4H", "4S",
                        "5C", "5D", "5H", "5S",
                        "6C", "6D", "6H", "6S",
                        "7C", "7D", "7H", "7S",
                        "8C", "8D", "8H", "8S",
                        "9C", "9D", "9H", "9S",
                        "10C", "10D", "10H", "10S",
                        "JC", "JD", "JH", "JS",
                        "QC", "QD", "QH", "QS",
                        "KC", "KD", "KH", "KS"
                )
        );

        hand = new ArrayList<>();

        holdStatus = new ArrayList<>(
                Arrays.asList(false, false, false, false, false)
        );


        hold1Counter = 0;
        hold2Counter = 0;
        hold3Counter = 0;
        hold4Counter = 0;
        hold5Counter = 0;
        dealCounter = 0;


    }


    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == this.deal) {
            dealCounter++;

            if (dealCounter == 1) {

                // when deal button is click the first time
                // shuffle the cards, pick 5 to add to player's hand
                // then remove those 5 cards from the deck
                Collections.shuffle(cards);

                hand.add(cards.get(0));
                hand.add(cards.get(1));
                hand.add(cards.get(2));
                hand.add(cards.get(3));
                hand.add(cards.get(4));

                cards.remove(0);
                cards.remove(1);
                cards.remove(2);
                cards.remove(3);
                cards.remove(4);


                // extract card information from player's hand and convert to ImageIcon
                icon1 = stringToImageIcon(hand.get(0));
                icon2 = stringToImageIcon(hand.get(1));
                icon3 = stringToImageIcon(hand.get(2));
                icon4 = stringToImageIcon(hand.get(3));
                icon5 = stringToImageIcon(hand.get(4));


                // display player's hand with a delay effect
                timer5 = new Timer(250, e -> {
                    card5.replaceIcon(icon5);
                    deal.setText("Draw");
                    timer5.stop();
                });

                timer4 = new Timer(250, e -> {
                    card4.replaceIcon(icon4);
                    timer5.start();
                    timer4.stop();
                });

                timer3 = new Timer(250, e -> {
                    card3.replaceIcon(icon3);
                    timer4.start();
                    timer3.stop();
                });

                timer2 = new Timer(250, e -> {
                    card2.replaceIcon(icon2);
                    timer3.start();
                    timer2.stop();
                });

                timer1 = new Timer(250, e -> {
                    card1.replaceIcon(icon1);
                    timer2.start();
                    timer1.stop();
                });

                timer1.start();

            }

            if (dealCounter == 2) {

                // loop through the hold status ArrayList, if false, then
                // add those corresponding cards
                // (which the player did not hold) to the deck of cards
                for (int i = 0; i < 5; i++) {
                    if (holdStatus.get(i) == false) {
                        cards.add(hand.get(i));
                    }
                }

                // shuffle the deck and replace the "unholded slots" in player's hand with
                // randomly selected cards from the deck
                Collections.shuffle(cards);
                for (int j = 0; j < 5; j++) {
                    if (holdStatus.get(j) == false) {
                        hand.set(j, cards.get(j));
                    }
                }

                // now update the visualization
                icon1 = stringToImageIcon(hand.get(0));
                icon2 = stringToImageIcon(hand.get(1));
                icon3 = stringToImageIcon(hand.get(2));
                icon4 = stringToImageIcon(hand.get(3));
                icon5 = stringToImageIcon(hand.get(4));


                timer6 = new Timer(250, e -> {
                    HandCombo playerHand = new HandCombo(hand);
                    result.setText(playerHand.determine());
                    System.out.println(playerHand.determine()); // also print result on console
                    timer6.stop();
                });


                timer5 = new Timer(250, e -> {
                    card5.replaceIcon(icon5);
                    timer6.start();
                    timer5.stop();
                });

                timer4 = new Timer(250, e -> {
                    card4.replaceIcon(icon4);
                    timer5.start();
                    timer4.stop();
                });

                timer3 = new Timer(250, e -> {
                    card3.replaceIcon(icon3);
                    timer4.start();
                    timer3.stop();
                });

                timer2 = new Timer(250, e -> {
                    card2.replaceIcon(icon2);
                    timer3.start();
                    timer2.stop();
                });

                timer1 = new Timer(250, e -> {
                    card1.replaceIcon(icon1);
                    timer2.start();
                    timer1.stop();
                });

                timer0 = new Timer(250, e -> {
                    if (holdStatus.get(0) == false) {
                        card1.replaceIcon(iconBack);
                    } else {
                        timer1 = new Timer(0, f -> {
                            card1.replaceIcon(icon1);
                            timer2.start();
                            timer1.stop();
                        });
                    }
                    if (holdStatus.get(1) == false) {
                        card2.replaceIcon(iconBack);
                    } else {
                        timer2 = new Timer(0, f -> {
                            card2.replaceIcon(icon2);
                            timer3.start();
                            timer2.stop();
                        });
                    }
                    if (holdStatus.get(2) == false) {
                        card3.replaceIcon(iconBack);
                    } else {
                        timer3 = new Timer(0, f -> {
                            card3.replaceIcon(icon3);
                            timer4.start();
                            timer3.stop();
                        });
                    }
                    if (holdStatus.get(3) == false) {
                        card4.replaceIcon(iconBack);
                    } else {
                        timer4 = new Timer(0, f -> {
                            card4.replaceIcon(icon4);
                            timer5.start();
                            timer4.stop();
                        });
                    }
                    if (holdStatus.get(4) == false) {
                        card5.replaceIcon(iconBack);
                    } else {
                        timer5 = new Timer(0, f -> {
                            card5.replaceIcon(icon5);
                            timer6.start();
                            timer5.stop();
                        });
                    }
                    timer1.start();
                    timer0.stop();
                });

                timer0.start();

                // finally, determine win / lose based on player's hand
                // instantiate handCombo object
                // use determine() method to identify type of winning hand or lose
                // then set result
                // (add to timer6)

            }
        }


        if (event.getSource() == reset) {

            // reset the necessary components

            hold1Counter = 0;
            hold2Counter = 0;
            hold3Counter = 0;
            hold4Counter = 0;
            hold5Counter = 0;
            dealCounter = 0;

            // randomly select a card back (6 choices)
            Random randomizer = new Random();
            int die = randomizer.nextInt(6);
            final ArrayList<String> cardBacks = new ArrayList<>(
                    Arrays.asList(
                           "red_back", "purple_back", "blue_back",
                           "gray_back", "yellow_back", "green_back"
                    )
            );

            iconBack = stringToImageIcon(cardBacks.get(die));

            card1.replaceIcon(iconBack);
            card2.replaceIcon(iconBack);
            card3.replaceIcon(iconBack);
            card4.replaceIcon(iconBack);
            card5.replaceIcon(iconBack);

            result.setText(" ");

            cards = new ArrayList<>(
                    Arrays.asList(
                            "AC", "AD", "AH", "AS",
                            "2C", "2D", "2H", "2S",
                            "3C", "3D", "3H", "3S",
                            "4C", "4D", "4H", "4S",
                            "5C", "5D", "5H", "5S",
                            "6C", "6D", "6H", "6S",
                            "7C", "7D", "7H", "7S",
                            "8C", "8D", "8H", "8S",
                            "9C", "9D", "9H", "9S",
                            "10C", "10D", "10H", "10S",
                            "JC", "JD", "JH", "JS",
                            "QC", "QD", "QH", "QS",
                            "KC", "KD", "KH", "KS"
                    )
            );

            hand = new ArrayList<>();

            holdStatus = new ArrayList<>(
                    Arrays.asList(false, false, false, false, false)
            );

            hold1.setForeground(Color.BLACK);
            hold2.setForeground(Color.BLACK);
            hold3.setForeground(Color.BLACK);
            hold4.setForeground(Color.BLACK);
            hold5.setForeground(Color.BLACK);
            deal.setText("Deal");

        }

        // now for the hold buttons:
        // if number of times pressed is an odd number,
        // then switch hold status to true and set button text color to red
        // if number of times pressed is even, meaning the player changed their mind
        // then reset hold status to false and reset button text color to black
        // note: hold buttons only work when the deal button is pressed the first time

        if (event.getSource() == hold1 && dealCounter == 1) {
            hold1Counter++;

            if (hold1Counter % 2 == 1) {
                holdStatus.set(0, true);
                hold1.setForeground(Color.RED);
            } else {
                holdStatus.set(0, false);
                hold1.setForeground(Color.BLACK);
            }
        }


        if (event.getSource() == hold2 && dealCounter == 1) {
            hold2Counter++;

            if (hold2Counter % 2 == 1) {
                holdStatus.set(1, true);
                hold2.setForeground(Color.RED);
            } else {
                holdStatus.set(1, false);
                hold2.setForeground(Color.BLACK);
            }
        }


        if (event.getSource() == hold3 && dealCounter == 1) {
            hold3Counter++;

            if (hold3Counter % 2 == 1) {
                holdStatus.set(2, true);
                hold3.setForeground(Color.RED);
            } else {
                holdStatus.set(2, false);
                hold3.setForeground(Color.BLACK);
            }
        }


        if (event.getSource() == hold4 && dealCounter == 1) {
            hold4Counter++;

            if (hold4Counter % 2 == 1) {
                holdStatus.set(3, true);
                hold4.setForeground(Color.RED);
            } else {
                holdStatus.set(3, false);
                hold4.setForeground(Color.BLACK);
            }
        }


        if (event.getSource() == hold5 && dealCounter == 1) {
            hold5Counter++;

            if (hold5Counter % 2 == 1) {
                holdStatus.set(4, true);
                hold5.setForeground(Color.RED);
            } else {
                holdStatus.set(4, false);
                hold5.setForeground(Color.BLACK);
            }
        }


    }

    // a static method to convert card name to image icon

    public static ImageIcon stringToImageIcon(String stringCard) {

        ImageIcon icon = new ImageIcon("png/" + stringCard + ".png");
        Image image = icon.getImage();
        Image modImage = image.getScaledInstance(230, 352, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(modImage);
        return icon;

    }

}
