package View;

import Model.Reports;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportView extends JDialog {
    private Reports reports;
    private JLabel welcome;
    private JButton buttonInstall;
    private JButton buttonActivation;

    public ReportView(JPanel parent) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder("דוחות מערכת"));
        add(mainPanel, BorderLayout.CENTER);

        //-- Initialization --//
        reports = new Reports();
        welcome = new JLabel("תפריט דוחות");
        buttonInstall = new JButton("דוח התקנה");
        buttonInstall.setPreferredSize(new Dimension(200,50));
        buttonActivation = new JButton("דוח הפעלות");
        buttonActivation.setPreferredSize(new Dimension(200,50));

        buttonInstall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reports.InstallReport();
                dispose();
            }
        });

        buttonActivation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reports.ActivationReport();
                dispose();
            }
        });

        FormControl();
        setModal(true);
        setSize(550, 500); // Size the Frame
        setLocationRelativeTo(parent);
    }

    private void FormControl()
    {
        JPanel formPanelTop = new JPanel();
        JPanel buttonsPanel = new JPanel();

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.NONE;

        //-- 1St Row -- //
        gc.gridy=0;
        gc.weightx=1;
        gc.weighty=0.1;
        gc.gridx=0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,30,0,30); // make space from label to field text
        add(buttonInstall,gc);

        //-- 2St Row -- //
        gc.gridy++;
        gc.weightx=1;
        gc.weighty=2;
        gc.gridx=0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(buttonActivation,gc);

        //-- Buttons Panel --//
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(buttonInstall);
        buttonsPanel.add(buttonActivation);

        //-- Title Panel Top --//
        formPanelTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        formPanelTop.setBorder(BorderFactory.createLineBorder(Color.black));
        welcome.setFont(new Font("Arial",Font.PLAIN,36));
        formPanelTop.add(welcome);

        // Add sub panels //
        setLayout(new BorderLayout());
        add(formPanelTop,BorderLayout.NORTH);
        add(buttonsPanel,BorderLayout.SOUTH);
    }
}