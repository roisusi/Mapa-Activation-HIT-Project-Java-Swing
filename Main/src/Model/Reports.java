package Model;

import Controller.Controller;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Reports{
        private Controller controller;

        public Reports(){

        }

        public void InstallReport()
        {
            controller = new Controller();
            //System.out.println("Report Generating");
            try {
                    try {
                        controller.connect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                JasperReport report = JasperCompileManager.compileReport("D:\\OneDrive - Holon Institute of Technology\\Java\\Project\\Main\\src\\Report\\InstallationReport.jrxml");
                JasperPrint print = JasperFillManager.fillReport(report,null, controller.getConnection());
               // JasperExportManager.exportReportToPdfFile(print,"C:\\Users\\vinos\\JaspersoftWorkspace\\MyReports\\report1.pdf");
                JasperViewer.viewReport(print);
                controller.disconnect();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    public void ActivationReport()
    {
        controller = new Controller();
        //System.out.println("Report Generating");
        try {
            try {
                controller.connect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            JasperReport report = JasperCompileManager.compileReport("D:\\OneDrive - Holon Institute of Technology\\Java\\Project\\Main\\src\\Report\\ActivationReport.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report,null, controller.getConnection());
            // JasperExportManager.exportReportToPdfFile(print,"C:\\Users\\vinos\\JaspersoftWorkspace\\MyReports\\report1.pdf")
            JasperViewer.viewReport(print);
            controller.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }

