package com.bachev.s3.example.s3_example;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.query.JsonQueryExecuterFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.util.HashMap;

@Service()
@Slf4j
public class ReportService {

    public byte[] getReportAsByte() throws Exception {
        log.info("==>> creating report");
        var jasperReport = JasperCompileManager.compileReport(ResourceUtils.getFile("classpath:jasperreport/test.jrxml").getAbsolutePath());
        var helloWorld = new HelloWorld("Hello", "World");
        var objectMapper = new ObjectMapper();
        var helloWorldAsByte = objectMapper.writeValueAsBytes(helloWorld);
        var inputStream = new ByteArrayInputStream(helloWorldAsByte);
        var params = new HashMap<String, Object>();
        params.put(JsonQueryExecuterFactory.JSON_INPUT_STREAM, inputStream);
        var jasperPrint = JasperFillManager.fillReport(jasperReport, params);
        var report = JasperExportManager.exportReportToPdf(jasperPrint);
        log.info("===>> created report");
        return report;
    }

    private record HelloWorld(String hello, String world) {

    }
}
