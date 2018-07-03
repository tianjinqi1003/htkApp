package com.htkapp.core.config;

/**
 * Created by yinqilei on 17-6-28.
 * 接入支付宝的信息配置
 */
public class AlipayConfig {

    // 商户appid
    public static String APPID = "2017052607358206";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCux2NPNQ00hF8ubrDsYex15Hg2iAmL0ydDyQCjnbR6zPWBh8fRxpgNA3CUKoXrBJ1J2dDSSCnvm7N+K+Mpdxad1qkgpj5EH7OkxntlZZsiTiRBMCz6IpZSt1ikpRtI9FmS9QPGXlkVi/l0fPCKGocITGIqVOpKyEGvWY6hn3Wiimn0YVZh9CL39KKdm+Zkb/6zGO49d8Emi2vNfYWMye3MxqBdMo34DpArgmj6O1ebz30Seb4DJAKAe0JFEH6jE6WfTfL0le95j7l4ZOOmtNZABz/ZZqF1oS5nMxgGsWbEYr1Gl0xxC+0ifmyYRwGOSA58bV1AdduJYRJpb7dnimgXAgMBAAECggEBAItjBHZkztU1RFtZd8vtBJG41Y5Xy5UxnrzWjqXt3cOZtanJybgo85ZdSelASHD3yyDcj0dEuf0XZXyYL6AgE29phJQ/QKU/yxr8F+jzsVcYjp1WeGg8MTpiclVMP4hdJxqmQI5xLAadDSKziXn6UfpyQE+WTQu6zXe0qqWG8F/hRNYjOtM4UBuhKhooBQrB16bciU6J7jyO1ExLe+KoFn7pvqqMblim+phAI1Dj2E2GffVjRWWApBgn2qY4UEDfM6SLRz0snkptTTZmcuWEqwPGW7Oy1nS4Nn50AghsHb7qUuxfhVVbHTUvuocItSbH4Alc7o6O2diqz2OAgcPxcKECgYEA5L4+3d8FWzLJhQ/TcRy9/YbfOD7CRzGPErxrOPAoo4m1oYFKS5p9HT1uD1Pd9Lh5O0zoYv3ZidSI+VrN66ovi+rsvJjESpAw16fEtSg7gdZYDEfEjsm1OiMWuvoBPi8+TqliJiMiz21s8a9HIJMHSx/EBNW+1phDlaUZFYv6xTkCgYEAw5r5jEcVSDdYSUvnb+h0MIX8U/11b5Sgautbl6YaWo7E31+DAMGPRyU8x9SqPX1lOlFK44ZVgPIHDuwRTrem6+3PNpJsOnZyCP2j41HB0+B0ZfqiXbYgOs3ZunjqANrFsgHopNrppGvHjP/tD0o0PWKc3Zi4VHpxVNi29djDZ88CgYACICZzZ1wkYc9vlhsP5QV68qLmYebqRHKg6PFSISEvi9sNOidXloLtH6IDMHNSj9hIjclnChbYagovzwE03Gr+sNssUXsZekeJ6XwxdsM+zKAa1Rvle8hcVvEiIaqbSlMY5ggMdK0hIlIt2MQfcF5T+2KV7OqOEfIm1Nbr1VYqOQKBgHsUyy3flJ6qZJ1Ka8jZBjN6O7pF5F1fsklU639S/4Y4C91aZj5VjWiBH+vF/5FNlCLMAEmGv8qxgIt9M67KlPgj3Z1DKWLoIP1TTIq/aS1/MP7yZLR+42zdFsHlT6lV/8vws4j4TsgtIZWxfGOAl7qAkKBHdsWBfkqbSxULXnm1AoGAdviyCdy0ajXo2c5FA6S7MKQqkee2c7ZPBD7U7vaOs9vKqLsGfrUXy3jgXul0428+pWtNDabQFG1J7KRbCeNhdZIuTHnGuTH6x+KJmhXv7yzfwnd9vBnKwfEsnGhLFL1cmq+FnZJ8OwOP+LdGpweV8Gxm7q32lbo/td+ALIV1gqk=";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
//    public static String notify_url = "http://1704aa0586.51mypc.cn:32351/htkApp/API/paymentInterfaceAPI/aliPayNotifyInterface";
    public static String notify_url = "http://120.27.5.36:8080/htkApp/API/paymentInterfaceAPI/aliPayNotifyInterface";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://192.168.100.6:8300/return_url.jsp";
    // 请求网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
    // 日志记录目录
    public static String log_path = "/log";
    // RSA2
    public static String SIGNTYPE = "RSA";
}
