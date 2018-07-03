package API;

import com.htkapp.core.LogUtil;
import com.htkapp.core.dto.APIResponseModel;
import com.htkapp.modules.API.service.ShopDataService;
import com.htkapp.modules.merchant.pay.service.OrderCommonService;
import com.htkapp.modules.merchant.shop.entity.Shop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:spring-service.xml", "classpath:spring-mybatis.xml"})
public class ShopDataAPITest {

    @Resource
    private ShopDataService shopDataService;
    @Resource
    private OrderCommonService orderCommonService;

    final static Class<? extends Object> log = ShopDataAPITest.class;

    //测试获取店铺总数量
    //搜索关键字接口， 总数量用来设置随机数的最大值,在范围内获取一个整数值
    @Test
    public void getSearchKeywords() {
        try {
            for (int i = 0; i < 50; i++) {
                APIResponseModel res = shopDataService.getSearchKeywords(0);
                Object obj = res.getData();
                Shop shop = (Shop) obj;
                System.out.println("店铺名:" + shop.getShopName() + "   ,店铺类型:" + shop.getMark());
            }
        } catch (Exception e) {
            LogUtil.error(log, e.getMessage(), e);
        }
    }

    @Test
    public void getAllOrder(){
        String token = "da7e6e5b-7567-4576-a524-f10eeb1d2849";
        int pageNumber = 1;
        try {
//            APIResponseModel result = shopDataService.getOrderRecordList(token,pageNumber,null);
//            System.out.println(JSON.toJSON(result));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testttt(){
        String accountToken = "da7e6e5b-7567-4576-a524-f10eeb1d2849";
        try {
            APIResponseModel res = shopDataService.getOrderRecordList(accountToken,1);
            System.out.println("===============没有抛出异常");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
