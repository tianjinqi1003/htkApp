package com.htkapp.core.utils;

import com.htkapp.core.OtherUtils;
import com.htkapp.core.config.FTPConfig;
import com.htkapp.core.shiro.common.utils.LoggerUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.SocketException;

/**
 * 文件上传工具类
 *
 * @author lenovo
 */
public class FileUploadUtils {

    private static Class<? extends Object> cls = FileUploadUtils.class;

    //处理上传图片
    public static String appUploadAvatarImg(MultipartFile myFile, String folder, Integer port) throws Exception {
        try {
            //重置文件名
            long time = System.currentTimeMillis();
            String timeStr = String.valueOf(time);
            String[] originalFileName = myFile.getOriginalFilename().split("\\.");
            String fileName = timeStr + "." + originalFileName[1];
            FTPClient client = getFTPClient(FTPConfig.host, port, FTPConfig.userName, FTPConfig.password);
            String writeTempPath = "D:\\resource";
            FileUtils.copyInputStreamToFile(myFile.getInputStream(), new File(writeTempPath, fileName));
            uploadFileForFTP(client, fileName, writeTempPath + "\\" + fileName, "Resource\\htkApp\\upload\\" + folder);
            String avaPath = Globals.PROJECT_URL + Globals.PHOTO_URL + folder;
            return avaPath + fileName;
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
            throw new IOException("");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("");
        }
    }

    /**
     * @author 马鹏昊
     * @desc 处理上传标题图片
     * @date 2018-1-15
     */
    public static String appUploadMsgTitleImg(MultipartFile myFile, String folder) throws Exception {
        try {
            //重置文件名
            long time = System.currentTimeMillis();
            String timeStr = String.valueOf(time);
            String[] originalFileName = myFile.getOriginalFilename().split("\\.");
            String fileName = timeStr + "." + originalFileName[1];
            FTPClient client = getFTPClient(FTPConfig.host, FTPConfig.port_to, FTPConfig.userName, FTPConfig.password);
            String writeTempPath = "D:\\resource";
//            String writeTempPath = "/home/terabithia";

            /**
             * @author 马鹏昊
             * @desc 裁剪图片
             */
            File storeFile =  new File(writeTempPath, fileName);
            FileUtils.copyInputStreamToFile(myFile.getInputStream(),storeFile );
            String newName = ChangeImageSize.scale(storeFile.getAbsolutePath(),360,360,originalFileName[1]);

//            FileUtils.copyInputStreamToFile(myFile.getInputStream(), new File(writeTempPath, fileName));
//            uploadFileForFTP(client, fileName, writeTempPath + "\\" + fileName, "Resource\\htkApp\\upload\\" + folder);
            uploadFileForFTP(client, newName, writeTempPath + "\\" + newName, "Resource\\htkApp\\upload\\" + folder);
//            uploadFileForFTP(client, fileName, writeTempPath + "/" + fileName, "Resource\\htkApp\\upload\\" + folder);
            String avaPath = Globals.PROJECT_URL + Globals.PHOTO_URL + folder;

            return avaPath + newName;
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
            throw new IOException("");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("");
        }
    }

    //资讯内容上传的图片(by 石超)
    public static String appUploadContentImg(MultipartFile myFile, String folder) throws Exception {
        try {
            //重置文件名
            long time = System.currentTimeMillis();
            String timeStr = String.valueOf(time);
            String[] originalFileName = myFile.getOriginalFilename().split("\\.");
            String fileName = timeStr + "." + originalFileName[1];
            FTPClient client = getFTPClient(FTPConfig.host, FTPConfig.port_to, FTPConfig.userName, FTPConfig.password);
            String writeTempPath = "D:\\resource";
//            String writeTempPath = "/home/terabithia";


            /**
             * @author 马鹏昊
             * @desc 裁剪图片
             */
            File storeFile =  new File(writeTempPath, fileName);
            FileUtils.copyInputStreamToFile(myFile.getInputStream(),storeFile );
            String newName = ChangeImageSize.scale(storeFile.getAbsolutePath(),360,360,originalFileName[1]);


//            uploadFileForFTP(client, fileName, writeTempPath + "\\" + fileName, "Resource\\htkApp\\upload\\" + folder);
            uploadFileForFTP(client, newName, writeTempPath + "\\" + newName, "Resource\\htkApp\\upload\\" + folder);
//            uploadFileForFTP(client, fileName, writeTempPath + "/" + fileName, "Resource\\htkApp\\upload\\" + folder);
//            String avaPath = OtherUtils.getRootDirectory() + Globals.PROJECT_URL + Globals.PHOTO_URL + folder + fileName;
            String avaPath = OtherUtils.getRootDirectory() + Globals.PROJECT_URL + Globals.PHOTO_URL + folder + newName;
//            String avaPath = Globals.PROJECT_URL + Globals.PHOTO_URL + folder+fileName;
//            String rjson = "{\"code\": 0,\"msg\": \"成功\",\"data\": {\"src\": \"" + avaPath + "\"}}";
            JSONObject map = new JSONObject();
            map.put("code", 0);
            map.put("msg", "成功");
            JSONObject js = new JSONObject();
            js.put("src", avaPath);
            map.put("data", js);
            return map.toString();
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
            throw new IOException("");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("");
        }
    }

    //剪切后的图片上传
    public static String cutUploadImg(MultipartFile file, String folder) throws Exception {
        try {
            //重置文件名
            long time = System.currentTimeMillis();
            String timeStr = String.valueOf(time);
            String[] originalFileName = file.getOriginalFilename().split("\\.");
            String fileName = timeStr + "." + originalFileName[1];
//            String localPath = "/home/terabithia/Downloads";
//            String localOutPath = "/home/terabithia/Downloads";  //输入文件地址
            String localPath = "D:\\resource";
            String localOutPath = "D:\\resource";
            File upload = new File(localPath, fileName);
            FileUtils.copyInputStreamToFile(file.getInputStream(), upload);
            String outFileName = String.valueOf(System.currentTimeMillis());
            FTPClient client = getFTPClient(FTPConfig.host, FTPConfig.port_to, FTPConfig.userName, FTPConfig.password);
            uploadFileForFTP(client, outFileName + "." + originalFileName[1], localOutPath + "\\" + outFileName + "." + originalFileName[1], "Resource\\htkApp\\upload\\" + folder);
//            uploadFileForFTP(client, outFileName+ "." + originalFileName[1], localOutPath + "/" + outFileName + "." + originalFileName[1], "Resource\\htkApp\\upload\\" + folder);
            String avaPath = Globals.PROJECT_URL + Globals.PHOTO_URL + folder;
            return avaPath + outFileName + "." + originalFileName[1];
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    //接收上传的apk文件
    public static String uploadApk(MultipartFile file, HttpServletRequest request, String folder, String appName) throws Exception {
        HttpSession session = request.getSession();
        try {
            String[] originalFileName = file.getOriginalFilename().split("\\.");
            String fileName = appName + "." + originalFileName[1];
            String getPath = session.getServletContext().getRealPath("/upload");
            String getStorePath = "F:\\yinqilei\\workspace\\huitoukezhongzuoxiangmu\\src\\main\\webapp\\upload";  //windows  本地
            String path = getPath + "/" + folder;
            String storePath = getStorePath + "/" + folder;
            File file1 = new File(storePath);
            if (!file1.exists()) {
                boolean result = file1.mkdir();
            }
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path, fileName));
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(storePath, fileName));
            String avaPath = Globals.PROJECT_URL + Globals.PHOTO_URL + folder;
            System.out.println("===============================" + avaPath + fileName);
            return avaPath + fileName;
        } catch (IOException ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
            throw new IOException(ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    //获取ftp连接成功对象，连接失败则抛出异常
    public static FTPClient getFTPClient(String ftpHost, Integer ftpPort, String userName, String password) throws Exception {
        try {
            //创建ftp对象
            FTPClient ftpClient = new FTPClient();
            int port = ftpPort == null ? 21 : ftpPort;
            //传入主机和端口建立连接
            ftpClient.connect(ftpHost, port);
            //用户名、密码登陆
            ftpClient.login(userName, password);
            ;
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                LoggerUtils.fmtDebug(cls, ftpClient.getReplyString());
                ftpClient.disconnect();
                throw new Exception(ftpClient.getReplyString());
            }
            return ftpClient;
        } catch (SocketException e1) {
            LoggerUtils.fmtError(cls, e1, e1.getLocalizedMessage());
            throw new SocketException(e1.getMessage());
        } catch (IOException e2) {
            LoggerUtils.fmtError(cls, e2, e2.getLocalizedMessage());
            throw new IOException(e2.getMessage());
        } catch (Exception e3) {
            LoggerUtils.fmtError(cls, e3, e3.getLocalizedMessage());
            throw new Exception(e3.getMessage());
        }
    }

    //读取ftp服务器上文件方法
    public static String readFileForFTP(FTPClient ftpClient, String ftpPath, String fileName) {
        StringBuffer resultBuffer = new StringBuffer();
        FileInputStream inFile = null;
        InputStream in = null;
        try {
            //设置编码
            ftpClient.setControlEncoding("UTF-8");
            //文件类型
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //
            ftpClient.enterLocalPassiveMode();
            //改变访问的ftp服务器目录
            ftpClient.changeWorkingDirectory(ftpPath);
            //根据当前文件下的文件名接收文件
            in = ftpClient.retrieveFileStream(fileName);
        } catch (FileNotFoundException e1) {
            LoggerUtils.fmtError(cls, e1, "没有找到" + ftpPath + "文件");
            return "下载配置文件失败，请联系管理员.";
        } catch (SocketException e2) {
            LoggerUtils.fmtError(cls, e2, "连接FTP失败.");
        } catch (IOException e3) {
            LoggerUtils.fmtError(cls, e3, "文件读取错误。");
            return "配置文件读取失败，请联系管理员.";
        }
        //处理接收到的输入流
        if (in != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            String data = null;
            try {
                while ((data = bufferedReader.readLine()) != null) {
                    resultBuffer.append(data).append("\n");
                }
            } catch (IOException e1) {
                LoggerUtils.fmtError(cls, e1, "文件读取错误。");
            } finally {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    LoggerUtils.fmtError(cls, e, e.getMessage());
                }
            }
        } else {
            return null;
        }
        return resultBuffer.toString();
    }


    //上传至ftp服务器文件方法
    public static void uploadFileForFTP(FTPClient ftpClient, String ftpFileName, String writeTempFilePath, String operatePath) throws Exception {
        try {
            //设置passiveMode传输
            ftpClient.enterLocalPassiveMode();
            //设置传输方式
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //对远程目录的处理
            String remoteFileName = ftpFileName;
            //改变操作目录
            ftpClient.changeWorkingDirectory(operatePath);
            //本地写入成功
            File file = new File(writeTempFilePath);
            InputStream in = new FileInputStream(file);
            ftpClient.storeFile(remoteFileName, in);
            in.close();
            file.delete();
            return;
        } catch (Exception e) {
            ftpClient.disconnect();
            throw new Exception("上传图片到服务器失败");
        } finally {
            try {
                ftpClient.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
