package View;


import Model.NumberRanges;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class NumberRangesView extends JDialog implements ActionListener {
        private JTable table;
        private NumberRangesViewModel tableModel;
        private JButton adding;
        private JButton remove;
        private JButton save;
        private JButton FNR;
        private JPanel formPanelLeft;
        private JPanel formPanelRight;
        private ArrayList fromRange = new ArrayList<Integer>();
        private ArrayList toRange = new ArrayList<Integer>();


    public NumberRangesView(JPanel parent) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JToolBar toolBar = new JToolBar(JToolBar.VERTICAL);

        //-- Table --//
        tableModel = new NumberRangesViewModel();
        table = new JTable(tableModel);

        //-- Buttons --//
        adding = new JButton();
        adding.addActionListener(this);
        ImageIcon imageIcon1 = new ImageIcon("src/Images/Add_32x32.png");
        adding.setIcon(imageIcon1);
        adding.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NumberRangesViewModel.setMoreRows();
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                tableModel.fireTableDataChanged();
                tableModel.isCellEditable(row,col);

            }
        });
        remove = new JButton();
        remove.addActionListener(this);
        ImageIcon imageIcon2 = new ImageIcon("src/Images/Remove_32x32.png");
        remove.setIcon(imageIcon2);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                tableModel.fireTableRowsDeleted(row,row);
                NumberRangesViewModel.removeRows(row);
            }
        });

        save = new JButton("שמור");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0;
                fromRange = ActivationsMoves.SessionId.getFromRange();
                toRange = ActivationsMoves.SessionId.getToRange();
                if (fromRange != null && toRange != null)
                while (!fromRange.get(i).equals("") && !toRange.get(i).equals("")){
                    System.out.println(fromRange.get(i));
                    i++;
                }
            }
        });

        FNR = new JButton("הפק FNR");

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

    private void FormControl () {
        formPanelLeft = new JPanel();
        formPanelRight = new JPanel();
        //-- Form Panel --//
        formPanelLeft.setLayout(new GridBagLayout());
        GridBagConstraints gcLeft = new GridBagConstraints();
        gcLeft.fill = GridBagConstraints.NONE;

        formPanelRight.setLayout(new GridBagLayout());
        GridBagConstraints gcRight = new GridBagConstraints();
        gcRight.fill = GridBagConstraints.NONE;

        int rightLeftRow = 100;

        //-- Left Rows --//
        gcLeft.weighty = 1;
        gcLeft.weightx = 1;
        gcLeft.gridy = 0;
        gcLeft.gridx = 0;
        gcLeft.insets = new Insets(0, 0, 0, 0);
        gcLeft.anchor = GridBagConstraints.LINE_END;
        formPanelLeft.add(adding, gcLeft);
        gcLeft.gridx++;
        gcLeft.insets = new Insets(0, 0, 0, 0);
        gcLeft.anchor = GridBagConstraints.LINE_START;
        //formPanelLeft.add(new JScrollPane(table), gcLeft);

        // Add sub panels //
        setLayout(new BorderLayout());
        add(formPanelLeft,BorderLayout.CENTER);
        //add(formPanelRight,BorderLayout.EAST);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
