package Model;

import Controller.Controller;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Reports{
        private Controller controller;

        public Reports(){

        }

        public void generateReport()
        {
            //System.out.println("Report Generating");
            try {
                    try {
                        controller.connect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                JasperReport report = JasperCompileManager.compileReport("Main/src/Report/Report_Final.jrxml");
                JasperPrint print = JasperFillManager.fillReport(report,null, controller.getConnection());
               // JasperExportManager.exportReportToPdfFile(print,"C:\\Users\\vinos\\JaspersoftWorkspace\\MyReports\\report1.pdf");
                JasperViewer.viewReport(print);
                controller.disconnect();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

