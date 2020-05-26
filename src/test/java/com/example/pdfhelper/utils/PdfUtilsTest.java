package com.example.pdfhelper.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class PdfUtilsTest {

    @Autowired
    public PdfUtils pdfUtils;


    @Test
    void createPdf() {
        // 模板路径
        String templatePath = "d:/pdfTemplate.pdf";
        // 生成的新文件路径
        String newPDFPath = "d:/pdfTemplateResult.pdf";

        Map<String, String> params = new HashMap<>(4);
        params.put("name", "李稳超");
        params.put("age", "18");
        params.put("gender", "男1");
        params.put("hobby", "游泳");

        pdfUtils.pdfCreateAndWrite(templatePath, params, newPDFPath);
    }

    @Test
    void createHt() {
        // 模板路径
        String templatePath = "d:/htTemplate.pdf";
        // 生成的新文件路径
        String newPDFPath = "d:/htResult.pdf";

        Map<String, String> params = new HashMap<>();
        params.put("buyName", "Joron");
        params.put("seller", "mbcloud");
        params.put("guige", "x123");
        params.put("count", "1000");
        params.put("unit", "kg");
        params.put("method", "pay");
        params.put("1", "1");
        params.put("2", "2");
        params.put("3", "3");
        params.put("4", "4");
        params.put("5", "5");
        params.put("6", "6");

        pdfUtils.pdfCreateAndWrite(templatePath, params, newPDFPath);
    }

    @Test
    void createSimpleContrct() {
        // 模板路径
        String templatePath = "d:/simpleContrct.pdf";
        // 生成的新文件路径
        String newPDFPath = "d:/simpleContrctResult.pdf";

        Map<String, String> params = new HashMap<>();
        params.put("buyer", "詹姆斯.邦德");
        params.put("seller", "招银云创科技有限公司");
        params.put("specification", "规格x123");
        params.put("count", "1000");
        params.put("calcUnit", "CNY");
        params.put("calcMethod", "Visa");
        params.put("standar", "ISO9001");
        params.put("unit", "千克");
        params.put("deliveryWay", "空运");
        params.put("receiver", "中国James");
        params.put("acceptanceWay", "面交");
        params.put("liquidatedDamagesPercent", "60");

        pdfUtils.pdfCreateAndWrite(templatePath, params, newPDFPath);
    }

}