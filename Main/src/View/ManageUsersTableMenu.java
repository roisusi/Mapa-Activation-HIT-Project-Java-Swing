package View;

import Model.Users;
import Model.Login;
import jdk.net.SocketFlow;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageUsersTableMenu extends JPanel {
    private JTable table;
    private UsersTableModel tableModel;
    private JButton buttonSave;
    private JPopupMenu popupMenu;
    private UsersTableListener usersTableListener;

    public ManageUsersTableMenu() {
        JFrame parent = new JFrame();
        tableModel = new UsersTableModel();
        table = new JTable(tableModel);
        JPanel buttonPanel = new JPanel();
        popupMenu = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("מחק שורה");
        popupMenu.add(removeItem);

        //--Create Table Cell Change Detector--//
        table.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //-- Create The Borders --//
        Border outerBorder = BorderFactory.createEmptyBorder(20,30,300,30);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"משתמשי מערכת"); //adds Label to the border
        setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder)); //for 2 borders

        TableColumn userTypeColumn = table.getColumnModel().getColumn(4);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("PrimaryManager");
        comboBox.addItem("ProjectManager");
        comboBox.addItem("Expert");
        userTypeColumn.setCellEditor(new DefaultCellEditor(comboBox));


        //-- Table Event To Mouse Click --//
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

        buttonSave = new JButton("שמור שינויים");

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    usersTableListener.rowEdit(tableModel.getUserList(), tableModel.getLoginList());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //-- Buttons Panel --//
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(buttonSave);

        //-- Graphic Option --//
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
    }

    public void setData(List<Users> user, List<Login> login)
    {
        tableModel.setData(user, login);
    }

    public void refresh ()
    {
        tableModel.fireTableDataChanged();
    }

    public void setUsersTableListener(UsersTableListener usersTableListener){
        this.usersTableListener = usersTableListener;
    }
}
