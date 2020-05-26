package com.example.pdfhelper.test;

import com.example.pdfhelper.utils.PdfUtils;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class TestService {

    @Autowired
    PdfUtils pdfUtils;

    public void createPdf(Map<String, String> params){

        String templateFile = "d:/simpleContrct.pdf";
        String pdfFile = String.format("d:/simpleContrctResult-%s.pdf", UUID.randomUUID().toString());
        pdfUtils.pdfCreateAndWrite(templateFile, params, pdfFile);
    }


    public ByteArrayOutputStream readTemplateAndStream(Map<String, String> params){

        String templateFile = "d:/simpleContrct.pdf";
        PdfReader reader = pdfUtils.getReader(templateFile);
        try {
            ByteArrayOutputStream pdfStream = pdfUtils.getPdfStream(reader, params);
            return pdfStream;
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void viewPdf(){

    }
}
