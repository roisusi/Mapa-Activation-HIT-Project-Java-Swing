package Model;

import Controller.ActivationSipController;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Reports{
        private ActivationSipController activationSipController;

        public Reports(){

        }

        public void InstallReport()
        {
            activationSipController = new ActivationSipController();
            try {
                    try {
                        activationSipController.connect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                JasperReport report = JasperCompileManager.compileReport("D:\\OneDrive - Holon Institute of Technology\\Java\\Project\\Main\\src\\Report\\InstallationReport.jrxml");
                JasperPrint print = JasperFillManager.fillReport(report,null, activationSipController.getConnection());
               // JasperExportManager.exportReportToPdfFile(print,"C:\\Users\\vinos\\JaspersoftWorkspace\\MyReports\\report1.pdf");
                JasperViewer.viewReport(print,false);
                activationSipController.disconnect();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    public void ActivationReport()
    {
        activationSipController = new ActivationSipController();
        try {
            try {
                activationSipController.connect();
            } catch (Exception e) {
                e.printStackTrace();
            }

            JasperReport report = JasperCompileManager.compileReport("D:\\OneDrive - Holon Institute of Technology\\Java\\Project\\Main\\src\\Report\\ActivationReport.jrxml");
            JasperPrint print = JasperFillManager.fillReport(report,null, activationSipController.getConnection());
            // JasperExportManager.exportReportToPdfFile(print,"C:\\Users\\vinos\\JaspersoftWorkspace\\MyReports\\report1.pdf")
            JasperViewer.viewReport(print,false);
            activationSipController.disconnect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    }

