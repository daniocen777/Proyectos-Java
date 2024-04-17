package com.danicode.app;

import net.sf.jasperreports.engine.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Para el reporte
    @Bean
    CommandLineRunner init() {
        return args -> {
            // Definir la ruta de destino
            String destinationPath = "src" +
                    File.separator + "main" +
                    File.separator + "resources" +
                    File.separator + "static" +
                    File.separator + "ReportGenerated.pdf";
            // Definir la ruta donde esta el xml
            String filePath = "src" +
                    File.separator + "main" +
                    File.separator + "resources" +
                    File.separator + "templates" +
                    File.separator + "report" +
                    File.separator + "report.jrxml";
            // paramatros
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("voucher_id", "00004534");
            parameters.put("current_date", formatter.format(localDateTime));
            parameters.put("pAmountPage", new BigDecimal(3900));
            parameters.put("pPaymentMethod", "Efectivo");
            parameters.put("pParentName", "Santiago Pérez");
            parameters.put("pchildName", "Sofía Pérez");
            // imagenes => se debe modificar en el xml
            parameters.put("imageDir", "classpath:/static/images/");
            // Generar el reporte
            // Leer el xml y convertirlo a un objeto JasperReport
            JasperReport report = JasperCompileManager.compileReport(filePath);
            // llenar reporte
            // JREmptyDataSource() => no se usa una BD
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(print, destinationPath);
            System.out.println("Report created successfully");
        };
    }
}