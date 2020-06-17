package View;

import Controller.Controller;
import Model.Users;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ManageUsersTableMenu extends JPanel {
    private JTable table;
    private UsersTableModel tableModel;
    private JPopupMenu popupMenu;
    private UsersTableListener usersTableListener;

    public ManageUsersTableMenu() {
        JFrame parent = new JFrame();
        tableModel = new UsersTableModel();
        table = new JTable(tableModel);
        popupMenu = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("מחק שורה");
        popupMenu.add(removeItem);

        //-- Create The Borders --//
        Border outerBorder = BorderFactory.createEmptyBorder(20,30,300,30);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"System Users"); //adds Label to the border
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

                if(usersTableListener != null && action==JOptionPane.OK_OPTION)
                {
                    usersTableListener.rowDelete(row);
                }
                tableModel.fireTableRowsDeleted(row,row);//tell him more efficent that what exact row deleted
            }
        });

        //-- Graphic Option --//
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Users> db)
    {
        tableModel.setData(db);
    }

    public void refresh ()
    {
        tableModel.fireTableDataChanged();
    }

    public void setUsersTableListener(UsersTableListener usersTableListener){
        this.usersTableListener = usersTableListener;
    }
}
