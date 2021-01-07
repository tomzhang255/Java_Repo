import javax.swing.ImageIcon;
import javax.swing.JLabel;

// a class to visually represent the cards in the panel

@SuppressWarnings("serial")
public class CardLabel extends JLabel {
    // instance variable
    private JLabel card;

    // constructor
    public CardLabel(ImageIcon icon) {
        this.card = new JLabel(icon);
    }

    // instance methods
    public JLabel getLabel() {
        return this.card;
    }

    public void replaceIcon(ImageIcon icon) {
        this.card.setIcon(icon);
    }

}
