package View;

import Controller.ActivationSipController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseExpertDialog extends JDialog {
    private JLabel name = new JLabel("בחר מומחה");
    private JButton chooseButton = new JButton("בחר");
    private JList expertName;
    private ActivationSipController activationSipController = new ActivationSipController();
    private String getSelectedName;
    private UserSetExpertListener userSetExpertListener;
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
                getSelectedName = (String)expertModel.getElementAt(expertName.getSelectedIndex());
                userSetExpertListener.UserSetExpertListener(rowFromCalender,getSelectedName);// news where to add the name (Row)
                ActivationsMoves.SessionId.setExpertName(getSelectedName);
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

    public void setUserListener(UserSetExpertListener userSetExpertListener){
        this.userSetExpertListener = userSetExpertListener;
    }
}
