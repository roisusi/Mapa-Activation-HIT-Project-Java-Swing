package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePageMenu extends JPanel {

    private JButton createForm;
    private JButton editForm;
    private JButton reports;
    private JButton manageUsers;
    ActivationFormSIP activationFormSIPDialog;

    public HomePageMenu() {

        // -- The 4 Buttons --//
        createForm = new JButton("Create Activation");
        createForm.setPreferredSize(new Dimension(200,50));
        editForm = new JButton("Edit Activation");
        editForm.setPreferredSize(new Dimension(200,50));
        reports = new JButton("Reports");
        reports.setPreferredSize(new Dimension(200,50));
        manageUsers = new JButton("Manage Users");
        manageUsers.setPreferredSize(new Dimension(200,50));

        //-- Create The Borders --//
        Border outerBorder = BorderFactory.createEmptyBorder(200,10,200,10);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Menu Option"); //adds Label to the border
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder)); //for 2 borders

        //-- Create Form Dialog --//
        activationFormSIPDialog = new ActivationFormSIP(HomePageMenu.this);

        //Grid Bag Layout - new way to set layouts
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE; //if the component isnt the same size as the frame , it resize it. NONE make it non resize

        //-- 1St Row -- //
        gc.gridy=0;
        gc.weightx=1;
        gc.weighty=0.1;
        gc.gridx=0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,30,0,30); // make space from label to field text
        add(createForm,gc);
        gc.gridx=1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(editForm,gc);

        //-- 2St Row -- //
        gc.gridy++;
        gc.weightx=1;
        gc.weighty=2;
        gc.gridx=0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(reports,gc);
        gc.gridx=1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(manageUsers,gc);

        createForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                activationFormSIPDialog.setVisible(true);
            }

        });

    }

}
