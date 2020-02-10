import javax.swing.*;

public class Form {
    private JPanel Panel;
    private Game game1;


    public static void main(String[] args) {
        JFrame frame = new JFrame("Game");
        frame.setContentPane(new Form().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

