package View;

import Controller.Contorller;
import Model.ActivationFormSip;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HomePageCalenderMenu extends JPanel{
        private JTable table;
        private CalanderPanelModel tableModel;
        private CalanderTableListener calanderTableListener;
        private JButton refresh;
        private Contorller contorller;

    public HomePageCalenderMenu() {

        tableModel = new CalanderPanelModel();
        table = new JTable(tableModel);
        refresh = new JButton("Refresh");
        contorller = new Contorller();

        //-- Create The Borders --//
        Border outerBorder = BorderFactory.createEmptyBorder(20,30,300,30);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Calander Option"); //adds Label to the border
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder)); //for 2 borders

        //-- Graphic Option --//
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(refresh,BorderLayout.SOUTH);

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
                System.out.println(contorller.getSipActivaion().size());
            }
        });
    }
    public void setData(List<ActivationFormSip> db)
    {
        tableModel.setData(db);
    }
    public void refresh ()
    {
        tableModel.fireTableDataChanged();
    }
    public void setPersonTableListener(CalanderTableListener calanderTableListener){
        this.calanderTableListener = calanderTableListener;
    }
}
