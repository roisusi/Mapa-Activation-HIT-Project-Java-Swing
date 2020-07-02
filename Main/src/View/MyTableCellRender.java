package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class MyTableCellRender extends DefaultTableCellRenderer {

    public static final Border focusedCellBorder = UIManager.getBorder("Table.focusCellHighlightBorder");

    public static final Border unfocusedCellBorder = createEmptyBorder();

    private static Border createEmptyBorder() {
        Insets i = focusedCellBorder.getBorderInsets(new JLabel());
        return BorderFactory.createEmptyBorder(i.top, i.left, i.bottom, i.right);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);


        if (value != null && value.equals("כן"))
            try {
                this.setIcon(new ImageIcon(getClass().getResource("/Images/Check_16x16.png")));
                // This below code setting the border to be highlighted, but not whole

            } catch (Exception ex) {
                System.out.println(ex);
            }
        else
        {
            this.setIcon(null);
        }
        setBorder(hasFocus ? focusedCellBorder : unfocusedCellBorder);

        return this;
    }
}