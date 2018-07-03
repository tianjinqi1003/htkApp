package utils;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.htkapp.core.GoEasyResult;
import com.htkapp.core.MethodsParamsEntity.PushMesEntity;
import com.htkapp.core.MoreMethodsUtils;
import com.htkapp.core.OtherUtils;
import com.htkapp.core.config.AlipayConfig;
import com.htkapp.core.rabbit.service.ProducerService;
import com.htkapp.core.solr.BaseSolr;
import com.htkapp.core.springAop.methodService.methodServiceImpl.MoreMethodsServiceImpl;
import com.htkapp.core.utils.FileUploadUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.core.utils.Jpush;
import com.htkapp.core.utils.OrderNumGen;
import com.htkapp.modules.API.dto.ReturnHaveSomeList;
import com.htkapp.modules.admin.common.entity.Admin;
import com.htkapp.modules.merchant.buffetFood.entity.BuffetFoodOrderProduct;
import com.htkapp.modules.merchant.shop.service.AccountShopServiceI;
import io.goeasy.GoEasy;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.swing.*;
import java.awt.print.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

import static com.htkapp.core.OtherUtils.encryptString;
import static com.htkapp.core.OtherUtils.replaceStr;

/**
 * Created by yinqilei on 17-7-8.
 * 单元测试类(运行时spring自动注入失败失败)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring-service.xml", "classpath:spring-mybatis.xml",
        "classpath:spring-cache.xml", "classpath:spring-shiro.xml",
        "classpath:spring-solr.xml"})
public class TestMethods {

    @Resource
    private AccountShopServiceI accountShopService;
    @Resource
    private MoreMethodsUtils methodsUtils;
    @Resource
    private MoreMethodsServiceImpl moreMethodsService;
    //    @Resource
//    private ProducerService producer;
    @Resource
    private BaseSolr baseSolr;


    //测试打印方法输出一
    @Test
    public void testPrint() {
        JFileChooser fileChooser = new JFileChooser(); // 创建打印作业
        int state = fileChooser.showOpenDialog(null);
        if (state == fileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile(); // 获取选择的文件
            // 构建打印请求属性集
            HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            // 设置打印格式，因为未确定类型，所以选择autosense
            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
            // 查找所有的可用的打印服务
            PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
            // 定位默认的打印服务
            PrintService defaultService = PrintServiceLookup
                    .lookupDefaultPrintService();
            // 显示打印对话框
            PrintService service = ServiceUI.printDialog(null, 200, 200,
                    printService, defaultService, flavor, pras);
            if (service != null) {
                try {
                    DocPrintJob job = service.createPrintJob(); // 创建打印作业
                    FileInputStream fis = new FileInputStream(file); // 构造待打印的文件流
                    DocAttributeSet das = new HashDocAttributeSet();
                    Doc doc = new SimpleDoc(fis, flavor, das);
                    job.print(doc, pras);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //测试打印方法输出二
    @Test
    public void print() {

        //    通俗理解就是书、文档
        Book book = new Book();
        //    设置成竖打
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);

        //    通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        p.setSize(590, 840);//纸张大小
        p.setImageableArea(10, 10, 590, 840);//A4(595 X 842)设置打印区域，其实0，0应该是72，72，因为A4纸的默认X,Y边距是72
        pf.setPaper(p);

        //    把 PageFormat 和 Printable 添加到书中，组成一个页面
        book.append(new PrintTest(), pf);
        //获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();

        // 设置打印类
        job.setPageable(book);

        try {
            //可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
//            boolean a=job.printDialog();
            job.print();
//            if(a)
//            {
//            }else{
//                job.cancel();
//            }
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }

    //支付宝单笔转账接口
    @Test
    public void transfer() throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                AlipayConfig.RSA_PRIVATE_KEY, "json", AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();

        //转账单号
        String outBizNo = OrderNumGen.getOutRefundNo("transfer_");
        //收款方账号类型
        String payeeType = "ALIPAY_LOGONID";
        //收款方账号
        String payeeAccount = "futingting108@126.com\n";
//        String payeeAccount = "15133630010";
        //金额
        String amount = "24.86";
        //付款方姓名
        String payerShowName = "测试退款";
        //收款方真实姓名
//        String payeeRealName = "李骆";
//        String payeeRealName = "张文";
        //转账备注
        String remark = "测试接口转账";

        request.setBizContent("{" +
                "\"out_biz_no\":\"" + outBizNo + "\"," +
                "\"payee_type\":\"ALIPAY_LOGONID\"," +
                "\"payee_account\":\"" + payeeAccount + "\"," +
                "\"amount\":\"" + amount + "\"," +
                "\"payer_show_name\":\"" + payerShowName + "\"," +
//                "\"payee_real_name\":\"" + payeeRealName + "\"," +
                "\"remark\":\"" + remark + "\"" +
                "  }");
        try {
            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
            System.out.println(response.getBody());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //测试goEasy推送消息(推送到前端页面)
    @Test
    public void testPushMessage() {
        try {
            GoEasy goEasy = new GoEasy(Globals.goEasyKey);
            String userKey = encryptString(String.valueOf(1));
            GoEasyResult goEasyResult = new GoEasyResult('t', 1, "你有一个新订单");
            JSONObject obj = (JSONObject) JSONObject.toJSON(goEasyResult);
            goEasy.publish(replaceStr(userKey, "=", ""), obj.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    //测试多方法工具类中service 注入是否成功
    @Test
    public void testMoreMethodsUtils() {
        PushMesEntity pushMesEntity = new PushMesEntity("测试消息", "t", "测试消息内容", "dfdfd", 't', 1, "您有一个测试消息", 1);
        try {
//           methodsUtils.pushMesToManagePage(pushMesEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJPushMesByTag() {
        try {
            Jpush.jPushMethod("db5af7ca-e946-483a-8acd-d3b0678a4c8f", String.valueOf(new Date().getTime()), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listRemoveTest() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        try {
//            String date = "2017-11-28 10:00:00";
//            System.out.println(date.substring(5));
            Gson gson = new Gson();
            String json = gson.toJson(list);
            System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shopIdQRCodeTest() throws Exception {
//        AES aesResult = aes(Globals.encryptionKey.getBytes());
//        byte[] key = aesResult.getSecretKey().getEncoded();
////        OtherUtils.byteArrayToInt(key);
//        byte[] shopQRCode = aes(key).encrypt("1");
////        System.out.println("=====================================加密后的值开始");
//        System.out.println(Base64.encodeBase64String(shopQRCode));
//        String baseStr = Base64.encodeBase64String(shopQRCode);
////        System.out.println("=====================================加密后的值结束");
//        //调用方法传入数据，生成二维码图片字节数组
//        byte[] qdCode = OtherUtils.getBarcodeImg(baseStr);
//        //转换为字符串，用于返回给jsp页面
//        String codeData = Base64.encodeBase64String(qdCode);
//        System.out.println(codeData);
        double a = 33.0;
        double b = 20.0;
        System.out.println(b % a);
    }

    @Test
    public void springAnnotationAopTest() {
//        //测试生成消费券码
//        for (int i=0; i<1000; i++){
//            String result = OrderNumGen.getVoucherNumber().toString();
//            if(result.length() < 8){
//                int length = result.length();
//                for (int j=0; j< 8 - length; j++){
//                    result += "0";
//                }
//            }
//            System.out.println(result);
//        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("val", 10);
        jsonObject.put("name", "zhangSan");
        System.out.println(jsonObject);

        Gson gson = new Gson();
        List<ReturnHaveSomeList> someLists = new ArrayList<>();
        List<BuffetFoodOrderProduct> productList = new ArrayList<>();
        ReturnHaveSomeList someList = new ReturnHaveSomeList();
        someList.setCategoryId(1);
        someList.setCategoryName("ddd");

        BuffetFoodOrderProduct product = new BuffetFoodOrderProduct();
        product.setQuantity(1);
        product.setProductId(1);
        product.setProductName("aa");
        product.setCategoryId(11);
        product.setPrice(2.00);
        product.setImgUrl("http://baidu.com/img/2017.jpg");
        productList.add(product);
        someList.setOrderProductList(productList);
        someLists.add(someList);
        String a = gson.toJson(someLists);
        System.out.println(a);
    }

    @Test
    public void httpRequestTest() {
        HttpURLConnection httpURLConnection = null;
        try {
//            String urlAddress = "http://www.baidu.com";
            String urlAddress = "http://1704aa0586.51mypc.cn:32351/htkApp/API/buffetFoodAPI/getGoodsListByCategoryId";
            URL url = new URL(urlAddress);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
//            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpURLConnection.setRequestMethod("POST");
            DataOutputStream out = new DataOutputStream(httpURLConnection
                    .getOutputStream());
            String content = "categoryId=" + URLEncoder.encode("9", "UTF-8");
            out.writeBytes(content);
            out.flush();
            out.close();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                String resultMes = httpURLConnection.getResponseMessage();
//                String content = (String) httpURLConnection.getContent();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                StringBuffer sb = new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
//                byte[] data = new byte[inputStream.available()];
//                inputStream.read(data);
                //转成字符串
//                String a = new String(sb.toString());
                JSONObject jsonObject = JSONObject.parseObject(sb.toString());
                String code = String.valueOf(jsonObject.get("code"));
                String message = (String) jsonObject.get("message");
                System.out.println(sb.toString());
                System.out.println(resultMes);
                httpURLConnection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ftpContentTest() {
        FTPClient ftpClient = null;
        try {
            String host = "120.27.5.36";
            String userName = "htk";
            String password = "123456";
            ftpClient = FileUploadUtils.getFTPClient(host, null, userName, password);
            String serverFilePath = "Resource\\htkApp\\upload\\shop\\takeout";
            String serverFileName = "1.txt";
//            String resultStreamStr = FileUploadUtils.readFileForFTP(ftpClient,serverFilePath,serverFileName);
            String filePath = "D:\\12.jpg";  //本地路径
            String serverUploadFilePath = "Resource\\12.jpg";
            String operatePath = "Resource/htkApp/";
            File file = new File(filePath);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            FileUploadUtils.uploadFileForFTP(ftpClient, serverUploadFilePath, filePath, operatePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rabbitSendMessageTest() {
        Admin admin = new Admin();
        admin.setUserName("18660706071");
        System.out.println(admin);
        try {
            for (int i = 0; i < 10000000; i++) {
//                producer.sendMessage(admin);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //solr连接测试
    @Test
    public void solrTest() throws SolrServerException {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("orderNumber", "12345");
//        jsonObject.put("orderState", "1");
//        jsonObject.put("orderId", "20");
//        jsonObject.put("flag", 0);
//        try {
////            Jpush.jPushMethod("67",jsonObject.toJSONString(),"");
//            for (int i=0; i<10000; i++){
//                Jpush.jPushMethod("67a28162-c150-4989-800f-7aae8e1d5220",jsonObject.toJSONString(),"");
//                Jpush.jPushMethod("67a28162-c150-4989-800f-7aae8e1d5220",jsonObject.toJSONString(),"ALERT");
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

}