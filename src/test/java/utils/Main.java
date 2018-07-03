package utils;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;

import static com.htkapp.core.OtherUtils.encryptString;

/**
 * Created by terabithia on 12/16/17.
 */
public class Main {

    public static void main(String[] args) {
        try {
            JSONObject jsonObject = new JSONObject();
            String shopIdStr = encryptString("1");
            jsonObject.put("shopKey", shopIdStr);
            jsonObject.put("type", 1);  //0关注－收藏   1自助点餐
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.MARGIN, 0);  //margin 边框距离
            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new QRCodeWriter().encode(jsonObject.toJSONString(), BarcodeFormat.QR_CODE, 500, 500, hints);
//            String getPath = session.getServletContext().getRealPath("/upload") + folder;
            String fileName = "shop_1"+ ".png";
            String writeTempPath = "/data";
            File file = new File(writeTempPath, fileName);
            Path path = file.toPath();
            MatrixToImageWriter.writeToPath(matrix, "PNG", path);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
