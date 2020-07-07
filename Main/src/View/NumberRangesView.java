package View;

import Controller.Controller;
import Controller.NumberRangeController;
import Model.NumberRanges;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class NumberRangesView extends JDialog implements ActionListener {
    private JTable table;
    private NumberRangesViewModel tableModel;
    private JButton adding;
    private JButton remove;
    private JButton save;
    private JButton FNR;
    private ArrayList from = new ArrayList();
    private ArrayList to = new ArrayList();
    private JPanel panel = new JPanel();
    private NumberRangeController numberRangeController;
    private Controller controller;



    public NumberRangesView(JPanel parent,int id) {
        setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);
        int act = id;
        controller = new Controller();
        try {
            controller.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (act != 0)
                controller.loadNumberRangeFromDataBaseToList(act);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        controller.disconnect();

        if (ActivationsMoves.SessionId.getFromRange() != null && ActivationsMoves.SessionId.getToRange() != null){
            from = ActivationsMoves.SessionId.getFromRange();
            to = ActivationsMoves.SessionId.getToRange();
        }
        else if (controller.getNumberRanges().size() !=0){
            from = controller.getNumberRanges().get(0).getFromRange();
            to = controller.getNumberRanges().get(0).getToRange();
        }

        numberRangeController = new NumberRangeController(from, to);
        tableModel = new NumberRangesViewModel(from,to);
        table = new JTable(tableModel);
        tableModel.showEditRows();
        tableModel.fireTableDataChanged();

        //-- Buttons --//
        adding = new JButton();
        adding.addActionListener(this);
        ImageIcon imageIcon1 = new ImageIcon("src/Images/Add_32x32.png");
        adding.setIcon(imageIcon1);
        adding.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newNumberRage();
            }
        });
        remove = new JButton();
        remove.addActionListener(this);
        ImageIcon imageIcon2 = new ImageIcon("src/Images/Remove_32x32.png");
        remove.setIcon(imageIcon2);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delNumberRage();
            }
        });

        save = new JButton("שמור");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                from = tableModel.fromTableList();
                to = tableModel.toTableList();
                numberRangeController = new NumberRangeController(from,to);
                tableModel.removeViewCells();
                numberRangeController.removeEmptyCells(from,to);
                numberRangeController.setFromRange(from);
                numberRangeController.setToRange(to);
                ActivationsMoves.SessionId.setFromRange(from);
                ActivationsMoves.SessionId.setToRange(to);
                tableModel.fireTableDataChanged();
                dispose();
            }
        });

        FNR = new JButton("הפק FNR");
        FNR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberRangeController = new NumberRangeController(from,to);
                numberRangeController.removeEmptyCells(from,to);
                numberRangeController.FNRtoFileSip(from,to);
            }
        });

        //-- Tool Bar --//
        toolBar.add(adding);
        toolBar.addSeparator();
        toolBar.add(remove);
        toolBar.setFloatable(false); //u cant move the tool bar


        //-- Border Panels --//
        Border outerBorder = BorderFactory.createEmptyBorder(0,0,0,0);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"הוספת מספרים"); //adds Label to the border
        panel.setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder)); //for 2 borders

        //-- Buttons Panel --//
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(FNR);
        buttonPanel.add(save);


        //-- Main Panel --//
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(500,380));
        panel.add(scrollPane, BorderLayout.WEST);
        panel.add(toolBar,BorderLayout.EAST);


        add(panel,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);
        setModal(true);
        setSize(650, 500); // Size the Frame
        setLocationRelativeTo(panel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void newNumberRage(){
        tableModel.setMoreRows();
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        tableModel.fireTableDataChanged();
        tableModel.isCellEditable(row,col);
    }
    public void delNumberRage(){
        int row = table.getSelectedRow();
        if (row == -1){
            JOptionPane.showMessageDialog(NumberRangesView.this, "נא בחר שורה למחירה", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else {
            tableModel.removeRows(row);
            tableModel.fireTableRowsDeleted(row,row);
            //tableModel.fireTableDataChanged();
        }
    }
}
