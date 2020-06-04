package View;

import Controller.Controller;
import Model.ActivationFormSip;
import Model.Users;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class HomePageCalenderMenu extends JPanel{
    private JTable table;
    private CalanderPanelModel tableModel;
    private JPopupMenu popupMenu;
    private PersonTableListener personTableListener;
    private ChooseExpertDialog expert;

    public HomePageCalenderMenu() {

        JFrame parent = new JFrame();
        tableModel = new CalanderPanelModel();
        table = new JTable(tableModel);
        popupMenu = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("מחק שורה");
        JMenuItem setExpertItem = new JMenuItem("שייך מומחה להפעלה");
        JMenuItem refres = new JMenuItem("רענן");
        popupMenu.add(removeItem);
        popupMenu.add(setExpertItem);
        popupMenu.add(refres);


        //-- Create The Borders --//
        Border outerBorder = BorderFactory.createEmptyBorder(20,30,300,30);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"Calander Option"); //adds Label to the border
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder)); //for 2 borders

        //-- table event to mouse click --//
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row,row); //selection at any click mouse

                if(e.getButton() == MouseEvent.BUTTON3)
                    popupMenu.show(table,e.getX(),e.getY());

            }
        });

        //-- Popup Menu --//
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if(personTableListener != null)
                {
                    personTableListener.rowDelete(row);
                }
                tableModel.fireTableRowsDeleted(row,row);//tell him more efficent that what exact row deleted
            }
        });

        setExpertItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                expert = new ChooseExpertDialog(parent,row);
                expert.setUserListener(new UserSetListener() {
                    @Override
                    public void setUserListener(int row, String firstName) {
                        personTableListener.addExpertUser(row,firstName);
                    }

                    @Override
                    public void fireChanges() {
                        personTableListener.fireChanges();
                        tableModel.fireTableRowsUpdated(row,row);
                    }
                });
                expert.setVisible(true);
            }
        });

        refres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.fireTableDataChanged();//tell him more efficent that what exact row deleted
            }
        });

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
    public void setPersonTableListener(PersonTableListener personTableListener){
        this.personTableListener = personTableListener;
    }
}
