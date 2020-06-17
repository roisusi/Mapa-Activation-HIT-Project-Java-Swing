package View;

import javax.swing.*;
import java.awt.*;

public class UpperMenu extends JPanel {
    JLabel welcom;

    public UpperMenu(String welcome) {
        Dimension dim = new Dimension();
        dim.width=500;
        dim.height=50;

        welcom = new JLabel(welcome);
        welcom.setPreferredSize(dim);
        welcom.setFont(new Font("Arial",Font.PLAIN,36));


        add(welcom,BorderLayout.CENTER);


        //-- Graphic Option --//
        setBorder(BorderFactory.createLineBorder(Color.red));//color border
    }
}
