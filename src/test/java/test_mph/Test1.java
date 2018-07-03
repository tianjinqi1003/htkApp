package test_mph;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.htkapp.core.MD5Utils;
import com.htkapp.core.config.AlipayConfig;
import com.htkapp.core.utils.Globals;
import com.xiaoleilu.hutool.date.DateTime;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/12/28.
 */
public class Test1 {

    @Test
    public void name() {

//        test01();
//        String data = "alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017052607358206&biz_content=%7B%22body%22%3A%22%E5%9B%9E%E5%A4%B4%E5%AE%A2app%E6%94%AF%E4%BB%98%E5%AE%9D%E6%94%AF%E4%BB%98%E5%A4%96%E5%8D%96%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A%221712316724113029%22%2C%22product_code%22%3A%22QUICK_TAKEOUT_PAY%22%2C%22subject%22%3A%22%E5%A4%96%E5%8D%96%E6%94%AF%E4%BB%98%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22-0.98%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F120.27.5.36%3A8080%2FhtkApp%2FAPI%2FpaymentInterfaceAPI%2FaliPayNotifyInterface&sign=ADDfpQs8VQ8F1qfGloHqrF5PVeE24L2Pipz75IxxmI9fCd%2B%2BGQM0JqK%2BsWBr2W7vNolZ%2FLcywGcJNTjtpBsYD%2Fu%2FM3pFFN92Q8okjkDeFLJCFLzJK6MfiFcfFyeeud0VEMxEF67oVymgFqDXgIxBm0yQeHcFvu5wrEkcv4QYBNi9hNW0Kj7NhIC%2BhRHoH85tu7ZCLsHLcHsq1JhomOMAlQkof1XoUDkAbS0eB4%2B3a8q1mPSTjEIhYFI6eH4UHYOJCclcsgsl3VCpXYlthMcPWgNqx7lVYpiu%2B%2BCNWEnhS1AtqqCa5Tuk0No4qPBKBxBTRMjtsApfOsfpoR6lkGjOjg%3D%3D&sign_type=RSA&timestamp=2017-12-31+18%3A40%3A41&version=1.0";
//        String[] result = data.split("&");
//        for (String d :
//                result) {
//            System.out.println(d);
//        }

//        test02();

//        testAplipayTradeState("1801163837510090");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date d = new Date();
        String date = df.format(d);

//        Date date = new Date();
//        Timestamp timestamp = new Timestamp(date.getTime());
        Timestamp timestamp = Timestamp.valueOf(date);
        DateTime dateTime = DateTime.of(d.toString(),"yyyy-MM-dd HH:mm:ss");

    }

    public void testAplipayTradeState(String orderNo){
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, "json",
                AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipayFundTransOrderQueryRequest request2 = new AlipayFundTransOrderQueryRequest();
        request2.setBizContent("{" +
                "    \"out_biz_no\":"+"\""+orderNo +"\"," +
//                            "    \"order_id\":"+"" +
                "  }");
        AlipayFundTransOrderQueryResponse response2 = null;
        try {
            response2 = alipayClient.execute(request2);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(response2.isSuccess()){
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }

    public void test02(){
        String encPwd = MD5Utils.getMD5Encode("123456");
        String token = UUID.randomUUID().toString();
        String encToken = MD5Utils.getMD5Encode(token + Globals.DEFAULT_SALT_TOKEN);
        System.out.println(encPwd);
        System.out.println(token);
    }

    public void test01(){
        A aaa = new A(null,"baby");
    }

    public class A{

        int a ;

        String b ;

        public A(Integer a,String b){
            this.a = a;
            this.b = b;
        }
    }

}
