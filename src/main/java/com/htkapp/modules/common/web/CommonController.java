package com.htkapp.modules.common.web;

import com.google.code.kaptcha.Producer;
import com.htkapp.core.jsAjax.AjaxResponseModel;
import com.htkapp.core.shiro.common.utils.StringUtils;
import com.htkapp.core.utils.Globals;
import com.htkapp.modules.API.service.SMSBaseServiceI;
import com.htkapp.modules.common.service.CommonService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

import static com.htkapp.core.utils.Globals.ADMIN_KAPTCHAT_KEY;

/**
 * Created by yinqilei on 17-7-13.
 */

@Controller
@RequestMapping("/")
public class CommonController {

    @Resource
    private CommonService commonService;
    @Resource
    private Producer captchaProducer = null;

    private static final String direct = "common/";

    //通过手机号获取验证码
    @RequestMapping("/printMenu")
    @ResponseBody
    public String printMenu(String phone) {
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
                    return "fail";
                }
            }
        }
        return "success";
    }


    //通过手机号获取验证码
    @RequestMapping("/getPhoneVerificationCode")
    @ResponseBody
    public AjaxResponseModel getPhoneVerificationCode(String phone) {
        return commonService.getPhoneVerificationCode(phone);
    }

    //登陆界面的验证码
    @RequestMapping("/login/captcha")
    public void getKaptchaImageByMerchant(HttpSession session, String identity, HttpServletResponse response) {
        response.setDateHeader("Expires", 0);  //设置http头，告诉客户端不要缓存此图像
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();  //创建图片中显示的随机文字
        if (StringUtils.isNotEmpty(identity)) {
            if (identity.equals("admin")) {
                session.setAttribute(ADMIN_KAPTCHAT_KEY, capText);
            } else if (identity.equals("merchant")) {
                session.setAttribute(Globals.MERCHANT_KAPTCHAT_KEY, capText);
            } else {
                session.setAttribute(Globals.MERCHANT_KAPTCHAT_KEY, null);
                session.setAttribute(ADMIN_KAPTCHAT_KEY, null);
            }
            try {
                BufferedImage bi = captchaProducer.createImage(capText);
                ServletOutputStream out = response.getOutputStream();
//                ImageIO.write(bi, "jpg", out);
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(response.getOutputStream());
                encoder.encode(bi);
                try {
                    out.flush();
                } finally {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //404页面
    @RequestMapping(value = "/open/404",method = RequestMethod.GET)
    public String open404Page(){
        return direct + "404";
    }

    //500页面
    @RequestMapping(value = "/open/500", method = RequestMethod.GET)
    public String open500Page(){
        return direct + "404";
    }

}
