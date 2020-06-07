package View;

import Controller.Controller;
import Model.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ChooseExpertDialog extends JDialog {
    private JLabel name = new JLabel("בחר מומחה");
    private JButton chooseButton = new JButton("בחר");
    private JList expertName;
    private Controller controller = new Controller();
    private String getSelectedName;
    private List<Users> users;
    private UserSetListener userSetListener;
    private int rowFromCalender;

    public ChooseExpertDialog(JFrame parent,int rowFromCalender) {
        this.rowFromCalender = rowFromCalender;

        //--Set listBox--//
        expertName = new JList();

        //inorder to set data we need to configure model list
        DefaultListModel expertModel = new DefaultListModel();

        expertModel.addElement("רועי");
        expertModel.addElement("נעם");
        expertModel.addElement("אסף");
        expertName.setModel(expertModel);
        expertName.setPreferredSize(new Dimension(100,68)); // set size of of not it fixed size
        expertName.setBorder(BorderFactory.createEtchedBorder()); // create just frame border
        expertName.setSelectedIndex(1);//set default index selected as 1 allways

        //-- Choosing Expert from JList to be added to Activation --//
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                users = new LinkedList<Users>();
                getSelectedName = (String)expertModel.getElementAt(expertName.getSelectedIndex());
                userSetListener.setUserListener(rowFromCalender,getSelectedName);// news where to add the name (Row)
                System.out.println("ChooseExpert I got Applications : " + controller.getSipActivation().size());
                dispose();
            }
        });
        setLayout(new BorderLayout());
        add(expertName,BorderLayout.CENTER);
        add(name,BorderLayout.EAST);
        add(chooseButton,BorderLayout.EAST);

        setModal(true);
        setSize(200, 100); // Size the Frame
        setLocationRelativeTo(parent);
    }

    public void setUserListener(UserSetListener userSetListener){
        this.userSetListener = userSetListener;
    }
}
