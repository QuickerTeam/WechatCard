package com.dsb.weChat.util.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Max on 2016/7/27.
 * 作用：向微信服务器发起 GET/POST 请求
 */

public class HttpUtil {

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;

    //连接池的配置
    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }


    /**
     * 发送GET HTTPS请求，参数以Map K-V 的形式传递进来
     * @param url
     * @param params
     * @return
     */
    public static String doGetSSL(String url, Map<String, String> params) {
        String apiUrl = url;
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(connMgr)
                .setDefaultRequestConfig(requestConfig)
                .build();
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl +=param;
        String result = null;
        try {
            HttpGet get = new HttpGet(apiUrl);
            CloseableHttpResponse response = httpClient.execute(get);

            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println("微信服务器返回的状态码为：" + statusCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream in = entity.getContent();
                result = IOUtils.toString(in,"UTF-8");
            }
            else {
                System.out.println("响应的response对象为空！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }


    /**
     * 发送POST HTTPS请求，参数以Map K-V 的形式传递进来
     * @param url
     * @param params
     * @return
     */
    public static String doPostSSL(String url, Map<String, Object> params) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(connMgr)
                .setDefaultRequestConfig(requestConfig)
                .build();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String httpStr = null;
        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("响应的response对象为空！");
            }
        }
        return httpStr;
    }



    /**
     * 发送POST HTTPS 请求
     * @param url 请求地址
     * @param json 以json数据格式传输
     * @return 服务器返回数数据
     */
    public static String doPostSSL(String url, String json) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(connMgr)
                .setDefaultRequestConfig(requestConfig)
                .build();

        HttpPost post = new HttpPost(url);
        try {
            StringEntity stringEntity = new StringEntity(json);
            post.setEntity(stringEntity);
            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity httpEntity = response.getEntity();
            String returnJson = IOUtils.toString(httpEntity.getContent(),"UTF-8");
            return returnJson;
        } catch (UnsupportedEncodingException e) {
            System.out.println("doPostSSL(String url, String json)异常");
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.out.println("doPostSSL(String url, String json)异常");
            e.printStackTrace();
            return null;
        }
    }



    /**
     *
     * @param file 上传的文件
     * @param url 请求的地址
     * @return 返回上传图片logo的url
     */
    public static String doPostSSL(File file,String url){
        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(createSSLConnSocketFactory())
                .setConnectionManager(connMgr)
                .setDefaultRequestConfig(requestConfig)
                .build();
        try {
            HttpPost post = new HttpPost(url);
            FileBody pic = new FileBody(file);
            StringBody comment = new StringBody((file.getName()));
            MultipartEntity multipartEntity = new MultipartEntity();
            multipartEntity.addPart("pic", pic);
            multipartEntity.addPart("picName", comment);
            post.setEntity(multipartEntity);

            CloseableHttpResponse response = httpClient.execute(post);

            int statusCode = response.getStatusLine().getStatusCode();

            System.out.println("上传图片接口被调用，微信服务器返回的code码为:" + statusCode);

            HttpEntity entity = response.getEntity();
            String json = IOUtils.toString(entity.getContent(), "UTF-8");
            System.out.println("从微信服务器获得的json为：" + json);
            EntityUtils.consume(entity);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     *建立SLL（HTTPS请求）连接
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }

}
