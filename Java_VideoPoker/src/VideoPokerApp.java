import javax.swing.JFrame;


/**
 * Siyuan (Tom) Zhang
 * CS 219-01 Final Project
 * Video Poker App
 *
 * Note: make sure the PNG images of the cards are in
 * 		 the png directory of this java project folder
 */

public class VideoPokerApp {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Video Poker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        VideoPokerPanel panel = new VideoPokerPanel();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

    }

}
