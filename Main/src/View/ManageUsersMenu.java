package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ManageUsersMenu extends JPanel {
    private JButton buttonCreateUser;
    private CreateUserForm createUserForm;
    private GetUserFromUsersListener getUserFromUsersListener;

    public ManageUsersMenu() {
        // -- Create Button --//
        buttonCreateUser = new JButton("יצירת משתמש חדש");
        buttonCreateUser.setPreferredSize(new Dimension(200,50));

        //-- Create The Borders --//
        Border outerBorder = BorderFactory.createEmptyBorder(100,10,300,10);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"תפריט ניהול משתמשים"); //adds Label to the border
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder)); //for 2 borders

        //-- Create Form Dialog --//
        createUserForm = new CreateUserForm(ManageUsersMenu.this);

        //Grid Bag Layout - new way to set layouts
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        //-- 1St Row -- //
        gc.gridy++;
        gc.weightx=1;
        gc.weighty=0.1;
        gc.gridx=0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,30,0,30); // make space from label to field text
        add(buttonCreateUser,gc);

        // -- the Creation of New System User -- //
        buttonCreateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                createUserForm.setVisible(true);
            }

        });

        createUserForm.setFormListener(new CreateUserFormListener() {
            // ---- this Listener gets from Child Dialog the event of creating Activation Sip                         ---- //
            // ---- after the creation it adds the event to the DataBase, it send it to ManageUsers to show it on Table ----//
            @Override
            public void formEventOccurred(CreateFormEvent e) throws SQLException {
                getUserFromUsersListener.setUsers(e);
            }
        });
    }

    public void setUsersToTable(GetUserFromUsersListener getUserFromUsersListener){
        this.getUserFromUsersListener = getUserFromUsersListener;
    }
}
