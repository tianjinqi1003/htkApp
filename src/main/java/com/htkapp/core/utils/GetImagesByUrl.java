package com.htkapp.core.utils;

import com.htkapp.core.config.FTPConfig;
import com.xiaoleilu.hutool.http.HttpConnection;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.htkapp.core.utils.FileUploadUtils.appUploadAvatarImg;
import static com.htkapp.core.utils.FileUploadUtils.getFTPClient;
import static com.htkapp.core.utils.FileUploadUtils.uploadFileForFTP;

/**
 * Created by yinqilei on 17-7-12.
 * 通过图片url获取图片数据并写到本地
 */
public class GetImagesByUrl {

    //通过图片url发起http请求获取图片数据
    public static String getImagesByUrl(String avatarUrl, HttpServletRequest request) throws IOException,Exception {
        try {
            InputStream inputStream = getInputStream(avatarUrl);
            //重置文件名
            long time = System.currentTimeMillis();
            String timeStr = String.valueOf(time);
            String fileName = timeStr + ".jpg" ;
            FTPClient client = getFTPClient(FTPConfig.host, FTPConfig.port, FTPConfig.userName, FTPConfig.password);
            String writeTempPath = "D:\\resource";
            FileUtils.copyInputStreamToFile(inputStream, new File(writeTempPath, fileName));
            uploadFileForFTP(client, fileName, writeTempPath + "\\" + fileName, "Resource\\htkApp\\upload\\" + "app\\account");
            String avaPath = Globals.PROJECT_URL + Globals.PHOTO_URL + "app/account/";
            return avaPath + fileName;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }


    private static InputStream getInputStream(String avatarUrl) throws IOException,Exception {
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(avatarUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                //从请求返中获取数据流
                inputStream = httpURLConnection.getInputStream();
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return inputStream;
    }
}
