package View;


import Controller.ActivationSipController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomePageCalenderMenu extends JPanel{
    private JTable table;
    private CalenderPanelModel tableModel;
    private JPopupMenu popupMenu;
    private CalenderTableListener calenderTableListener;
    private ChooseExpertDialog expert;
    private ActivationSipController activationSipController;

    public HomePageCalenderMenu() {

        JFrame parent = new JFrame();
        tableModel = new CalenderPanelModel();
        table = new JTable(tableModel);
        activationSipController = new ActivationSipController();


        popupMenu = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("מחק שורה");
        JMenuItem setExpertItem = new JMenuItem("שייך מומחה להפעלה");
        JMenuItem setApproved = new JMenuItem("אשר הפעלה");
        popupMenu.add(removeItem);
        popupMenu.add(setExpertItem);
        popupMenu.add(setApproved);


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
                int action = JOptionPane.showConfirmDialog(parent, "האם ברצונך למחוק ?", "Confirm", JOptionPane.OK_OPTION,/*Change Icon*/JOptionPane.INFORMATION_MESSAGE);

                if(calenderTableListener != null && action==JOptionPane.OK_OPTION)
                {
                    calenderTableListener.rowDelete(row);
                }
                tableModel.fireTableRowsDeleted(row,row);//tell him more efficent that what exact row deleted
            }
        });

        // -- Place A new Expert on Calender --//
        setExpertItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                expert = new ChooseExpertDialog(parent,row);
                expert.setUserListener(new UserSetExpertListener() {
                    @Override
                    public void UserSetExpertListener(int row, String firstName) {
                        calenderTableListener.addExpertUser(row,firstName);
                        tableModel.fireTableRowsUpdated(row,row);//tell him more efficent that what exact row deleted
                    }
                });
                expert.setVisible(true);
            }
        });

        setApproved.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                calenderTableListener.setStatus("כן",row);
                tableModel.fireTableRowsUpdated(row,row);//tell him more efficent that what exact row deleted
            }
        });

        //-- Graphic Option --//
        MyTableCellRender myTableCellRender = new MyTableCellRender();
        table.setDefaultRenderer(Object.class,myTableCellRender);
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);

    }
    public void setData(ActivationSipController db)
    {
        tableModel.setData(db);
    }
    public void refresh ()
    {
        tableModel.fireTableDataChanged();
    }

    public void setCalenderTableListener(CalenderTableListener calenderTableListener){
        this.calenderTableListener = calenderTableListener;
    }

}


