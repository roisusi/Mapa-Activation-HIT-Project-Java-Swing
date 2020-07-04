package View;

import Controller.NumberRangeController;
import Model.NumberRanges;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NumberRangesView extends JDialog implements ActionListener {
        private JTable table;
        private NumberRangesViewModel tableModel;
        private JButton adding;
        private JButton remove;
        private JButton save;
        private JButton FNR;
        private ArrayList fromRange = new ArrayList<Integer>();
        private ArrayList toRange = new ArrayList<Integer>();
        private NumberRangeController numberRangeController;


    public NumberRangesView(JPanel parent) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);

        //-- Table --//
        if (ActivationsMoves.SessionId.getFromRange() != null && ActivationsMoves.SessionId.getToRange() != null) {
            fromRange = ActivationsMoves.SessionId.getFromRange();
            toRange = ActivationsMoves.SessionId.getToRange();
        }
        tableModel = new NumberRangesViewModel(fromRange,toRange);
        table = new JTable(tableModel);

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
                fromRange = tableModel.fromTableList();
                toRange = tableModel.toTableList();
                numberRangeController = new NumberRangeController(fromRange,toRange);
                tableModel.removeViewCells();
                numberRangeController.removeEmptyCells(fromRange,toRange);
                tableModel.fireTableDataChanged();
                ActivationsMoves.SessionId.setFromRange(fromRange);
                ActivationsMoves.SessionId.setToRange(toRange);
                dispose();
            }
        });

        FNR = new JButton("הפק FNR");
        FNR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberRangeController = new NumberRangeController(fromRange,toRange);
                numberRangeController.removeEmptyCells(fromRange,toRange);
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
        tableModel.removeRows(row);
        tableModel.fireTableDataChanged();
    }
}
