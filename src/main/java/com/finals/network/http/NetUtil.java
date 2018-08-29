package com.finals.network.http;

import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.uupaotui.openapi.Log;
import com.uupaotui.openapi.TextUtils;

public class NetUtil {

	public static final String TAG = "Finals";

	public static final String HTTPS = "https";

	public static final String HTTP = "http";

	public static final String GET = "GET";

	public static final String POST = "POST";

	public static int READTIMEOUT = 30 * 1000;

	public static int CONNECTTIMEOUT = 30 * 1000;

	// ��Щ��Э����
	public static String end = "\r\n";

	public static String twoHyphens = "--";

	public static String boundary = "----WebKitFormBoundaryabu2ewelZdmyik5w";

	public static Map<String, String> headsMap;

	/**
	 * �õ�URL����
	 * 
	 * @param url
	 * @param type
	 * @return
	 */
	public static HttpURLConnection GetHttpURLConnection(String url, String type) {
		if (TextUtils.isEmpty(url)) {
			Log.e("Finals", "URL Ϊ��");
			return null;
		}
		// ������
		URL mUrl = null;
		try {
			mUrl = new URL(url);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("Finals", "������ʧ��");
		}

		if (mUrl == null) {
			Log.e("Finals", "URL ��ʧ��");
			return null;
		}

		// ��ʼ������
		HttpURLConnection connection = null;
		// �õ���Ӧ������
		if (url.startsWith(HTTPS)) {
			connection = GetHttpsURLConnection(mUrl);
		} else if (url.startsWith(HTTP)) {
			connection = GetHttpURLConnection(mUrl);
		}
		if (connection == null) {
			Log.e("Finals", "connection Ϊ��");
			return null;
		}

		InitUrlConnection(connection, type);
		return connection;
	}

	/**
	 * �õ�HTTPS����
	 * 
	 * @param url
	 */
	private static HttpURLConnection GetHttpsURLConnection(URL url) {
		HttpsURLConnection sconn = null;
		try {
			sconn = (HttpsURLConnection) url.openConnection();
		} catch (Exception e) {
			Log.e("Finals", "openConnection �����쳣");
			return sconn;
		}

		// ȥ��֤����֤
		NoTrustManager manager = new NoTrustManager();
		TrustManager[] managers = { manager };
		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			Log.e(TAG, "û��TLS�㷨");
			return sconn;
		}
		try {
			ctx.init(null, managers, new java.security.SecureRandom());
		} catch (KeyManagementException e) {
			e.printStackTrace();
			Log.e(TAG, "��Կ�������");
			return sconn;
		}
		SSLSocketFactory factory = ctx.getSocketFactory();
		sconn.setHostnameVerifier(new HostnameVerifier() {

			@Override
			public boolean verify(String arg0, SSLSession arg1) {
				return true;
			}
		});
		sconn.setSSLSocketFactory(factory);
		return sconn;
	}

	/**
	 * �õ�HTTP����
	 * 
	 * @param url
	 * @return
	 */
	private static HttpURLConnection GetHttpURLConnection(URL url) {
		HttpURLConnection conn = null;
		try {
			conn = (HttpURLConnection) url.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "����IO�쳣");
			return conn;
		}
		return conn;
	}

	/**
	 * ��ʼ����������
	 * 
	 * @param connection
	 */
	private static void InitUrlConnection(HttpURLConnection connection, String type) {
		connection.setConnectTimeout(CONNECTTIMEOUT);
		connection.setReadTimeout(READTIMEOUT);
		try {
			connection.setRequestMethod(type);
		} catch (ProtocolException e) {
			e.printStackTrace();
			Log.e("Finals", "���÷���ʧЧ");
		}
		if (type.equals(POST)) {
			connection.setDoOutput(true);
		}
		connection.setDoInput(true);
		connection.setUseCaches(false);
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setRequestProperty("User-agent", "Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1");

		// Finals 2016-7-29 ����Head
		if (headsMap != null) {
			Iterator<String> mIterator = headsMap.keySet().iterator();
			while (mIterator.hasNext()) {
				String itemName = mIterator.next();
				String value = headsMap.get(itemName);
				connection.setRequestProperty(itemName, value);
			}
		}
	}

	public static void InitHead(Map<String, String> headsMap) {
		NetUtil.headsMap = headsMap;
	}
}
