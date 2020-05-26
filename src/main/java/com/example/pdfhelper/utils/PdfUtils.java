package com.example.pdfhelper.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.util.Map;

@Component
public class PdfUtils {


    /**
     * 创建生成pdf文件
     * @param templatePath
     * @param params
     * @param destPDFPath
     */
    public void pdfCreateAndWrite(String templatePath, Map<String, String> params, String destPDFPath){

        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfReader reader;
        try {
            // 输出流
            out = new FileOutputStream(destPDFPath);

            // 获取pdfReader
            reader = getReader(templatePath);

            int numberOfPages = reader.getNumberOfPages();

            // 获取pdf字节输出流
            bos = getPdfStream(reader, params);

            /**
             * 生成pdf文件
             */
            writePdf(out, bos, numberOfPages);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成pdf文件
     * @param out
     * @param bos
     * @param numberOfPages
     * @throws DocumentException
     * @throws IOException
     */
    private void writePdf(FileOutputStream out, ByteArrayOutputStream bos, int numberOfPages) throws DocumentException, IOException {
        Document doc = new Document();
        PdfCopy copy = new PdfCopy(doc, out);
        doc.open();
        for (int i = 1; i <= numberOfPages; i++) {
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), i);
            copy.addPage(importPage);
        }
        doc.close();
    }

    /**
     * 获取pdfReader
     * @param templatePath
     * @return
     */
    public PdfReader getReader(String templatePath){
        try {
            PdfReader reader = new PdfReader(templatePath);
            return reader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PdfReader getReader(URL url){
        try {
            return new PdfReader(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PdfReader getReader(InputStream is){
        try {
            return new PdfReader(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取pdf字节输出流
     * @param reader
     * @param params
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    public ByteArrayOutputStream getPdfStream(PdfReader reader, Map<String, String> params) throws DocumentException, IOException {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, bos);
        AcroFields form = stamper.getAcroFields();
        java.util.Iterator<String> it = form.getFields().keySet().iterator();
        while (it.hasNext()) {
            String name = it.next();
            System.out.println(name);
            form.setField(name, params.get(name));
        }
        // 如果为false那么生成的PDF文件还能编辑，一定要设为true
        stamper.setFormFlattening(true);
        stamper.close();
        return bos;
    }


}
