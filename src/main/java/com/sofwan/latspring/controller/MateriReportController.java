package com.sofwan.latspring.controller;

import com.sofwan.latspring.dao.MateriDao;
import com.sofwan.latspring.entity.Materi;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Date;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MateriReportController {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MateriReportController.class);
    //Logger LOGGER = LoggerFactory.getLogger(MateriReportController.class);

    @Value("classpath:reports/materi.jrxml")
    private Resource templateDaftarMateri;

    private JasperReport reportDaftarMateri;

    @Autowired
    private MateriDao md;

    public void generateReportMateri() {
        Iterable<Materi> m = md.findAll();
    }

    @RequestMapping(value = "/materi/reports")
    public void daftarMateriPdf(HttpServletResponse response, @RequestParam(value = "format", required = false) String format) {
        try {
            // 1. Datasource untuk ditampilkan di band detail
            JRBeanCollectionDataSource dataMateri = new JRBeanCollectionDataSource(md.semuaMateri());
            JRBeanCollectionDataSource dataMateri2 = new JRBeanCollectionDataSource(md.semuaMateri());

            // 2. Parameter report
            Map<String, Object> params = new HashMap<>();
            params.put("tanggalUpdate", new Date());

            // 3. Merge template dengan data
            JasperPrint jrPrint = JasperFillManager
                    .fillReport(getReport(), params, dataMateri);

            JasperPrint jrPrint2 = JasperFillManager
                    .fillReport(getReport(), params, dataMateri2);

            // 4. Render PDF default
            if (format != null && !format.isEmpty()) {

                response.setContentType("application/vnd.ms-excel");
                response.setHeader("Content-Disposition",
                        "attachment; filename=daftar-materi-versi-lain-"
                        + new Date()
                        + "." + format);

                JRXlsExporter exporter = new JRXlsExporter();
                exporter.setExporterInput(new SimpleExporterInput(jrPrint2));
                try {
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
                } catch (IOException ex) {
                    Logger.getLogger(MateriReportController.class.getName()).log(Level.SEVERE, null, ex);
                }

                SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
                configuration.setOnePagePerSheet(true);
                configuration.setDetectCellType(true);
                configuration.setCollapseRowSpan(false);
                exporter.setConfiguration(configuration);

                exporter.exportReport();

            } else {

                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition",
                        "attachment; filename=daftar-materi-"
                        + new Date()
                        + ".pdf");
                try {
                    JasperExportManager.exportReportToPdfStream(jrPrint, response.getOutputStream());
                } catch (IOException ex) {
                    Logger.getLogger(MateriReportController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (JRException err) {
            LOGGER.error(err.getMessage(), err);
        }
    }

    private JasperReport getReport() {
        if (reportDaftarMateri == null) {
            try {
                reportDaftarMateri = JasperCompileManager.compileReport(templateDaftarMateri.getInputStream());
            } catch (IOException | JRException e) {
                LOGGER.error(e.getMessage(), e);

            }
        }
        return reportDaftarMateri;
    }

}
