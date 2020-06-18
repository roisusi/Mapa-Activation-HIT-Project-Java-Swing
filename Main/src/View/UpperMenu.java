package View;

import javax.swing.*;
import java.awt.*;

public class UpperMenu extends JPanel {
    JLabel welcome;

    public UpperMenu(String str) {
        Dimension dim = new Dimension();
        dim.width=500;
        dim.height=50;

        welcome = new JLabel(str);
        welcome.setPreferredSize(dim);
        welcome.setFont(new Font("Arial",Font.PLAIN,36));


        add(welcome,BorderLayout.CENTER);


        //-- Graphic Option --//
        setBorder(BorderFactory.createLineBorder(Color.red));//color border
    }
}
