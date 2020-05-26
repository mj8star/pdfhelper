package com.example.pdfhelper.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pdf")
public class TestController {

    @Autowired
    TestService testService;

    Map<String, String> params = new HashMap<>();
    {
        params.put("buyer", "詹姆斯.邦德");
        params.put("seller", "招银云创科技有限公司");
        params.put("specification", "规");
        params.put("count", "1000");
        params.put("calcUnit", "CNY");
        params.put("calcMethod", "Visa");
        params.put("standar", "ISO9001");
        params.put("unit", "千克");
        params.put("deliveryWay", "空运");
        params.put("receiver", "招商银行招银云创科技公司");
        params.put("acceptanceWay", "一手交钱一手交货");
        params.put("liquidatedDamagesPercent", "60");
    }

    /**
     * 创建生成pdf
     * @return
     */
    @RequestMapping("/create")
    public String createPdf(){
        testService.createPdf(params);
        return "pdf created";
    }

    /**
     * 创建预览pdf（未生成pdf文件）
     * @return
     */
    @RequestMapping("/createAndView1")
    public ResponseEntity<byte[]> pdfCreateAndView1() {
        ByteArrayOutputStream byteArrayOutputStream = testService.readTemplateAndStream(params);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_PDF);
        return new ResponseEntity<>(byteArrayOutputStream.toByteArray(), httpHeaders, HttpStatus.OK);
    }


    @RequestMapping("/view")
    public void viewPdf(){

    }
}
