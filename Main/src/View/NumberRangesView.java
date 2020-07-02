package View;

import com.sun.source.tree.NewArrayTree;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberRangesView extends JDialog implements ActionListener {
        private JTable table;
        private NumberRangesViewModel tableModel;
        private JButton adding;
        private JPanel formPanelLeft;
        private JPanel formPanelRight;

    public NumberRangesView(JPanel parent) {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JToolBar toolBar = new JToolBar();

        //-- Table --//
        tableModel = new NumberRangesViewModel();
        table = new JTable(tableModel);

        //-- Buttons --//
        adding = new JButton();
        adding.addActionListener(this);
        ImageIcon imageIcon = new ImageIcon("src/Images/Add_16x16.png");
        adding.setIcon(imageIcon);
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


        //-- Tool Bar --//
        toolBar.add(adding);
        toolBar.setFloatable(false); //u cant move the tool bar


        //-- Border Panels --//
        Border outerBorder = BorderFactory.createEmptyBorder(0,0,0,0);
        Border innerBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),"הוספת מספרים"); //adds Label to the border
        panel.setBorder(BorderFactory.createCompoundBorder(innerBorder,outerBorder)); //for 2 borders

        //FormControl();
        panel.add(new JScrollPane(table), BorderLayout.WEST);
        panel.add(toolBar,BorderLayout.EAST);
        add(panel);
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
