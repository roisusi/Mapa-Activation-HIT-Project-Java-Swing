package View;

import Model.ActivationFormSip;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.List;

public class HomePageCalenderMenu extends JPanel{
        private JTable table;
        private CalanderPanelModel tableModel;
        private CalanderTableListener calanderTableListener;

    public HomePageCalenderMenu() {

        tableModel = new CalanderPanelModel();
        table = new JTable(tableModel);

        //-- Create The Borders --//
        Border outerBorder = BorderFactory.createEmptyBorder(20,30,300,30);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Calander Option"); //adds Label to the border
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder)); //for 2 borders

        //-- Graphic Option --//
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
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
