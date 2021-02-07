import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author 黄药师
 * @date 2021-02-06 9:43
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   测试 java 代码可以发送http协议
 *     远程访问  rpc-server
 */
public class HttpClientTest {

    /**
     *  java 代码 发送 Post请求
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {

        // 01 创建 httpclient 对象

        CloseableHttpClient httpClient = HttpClients.createDefault();


        // 02 创建httpPost/httpGet对象
        HttpPost httpPost = new HttpPost("http://localhost:8080/demo01");

        //03 设置请求数据

        //给请求参数 http中提供了一个键值对对象
        List<BasicNameValuePair> basicNameValuePairs = new ArrayList<>();

        // 请求的 http的参数设置
        basicNameValuePairs.add(new BasicNameValuePair("name","admin"));

        // Entity 需要请求参数
        // 把 http的参数 转成了 表单是实体对象  Entity
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(basicNameValuePairs, "utf-8");

        // Entity 是请求体的参数
        httpPost.setEntity(urlEncodedFormEntity);

        // 执行  发送请求
        // 返回 响应结果
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //httpClinet 框架中 有 工具类型 直接返回字符串

        // 拿到  getEntity 直接通过提供个工具类型转成字符串
        String s = EntityUtils.toString(response.getEntity());

        System.out.println("s = " + s);
    }

    /**
     *  java 代码 发送 get请求
     */
    @Test
    public void test03() throws URISyntaxException, IOException {

        // 01 获取httpClient

        CloseableHttpClient aDefault = HttpClients.createDefault();

        //创建一个url
        URIBuilder uriBuilder = new URIBuilder("http://localhost:8080/demo01");
        //在url上拼接 数据
        uriBuilder.addParameter("name","tiger");

        // 创建一个httpGet请求
        // get请求参数在url上进行拼接
        // post请求参数 在请求体中 ( UrlEncodedFormEntity )

        HttpGet httpGet = new HttpGet(uriBuilder.build());

        //02 执行方法  发送请求
        CloseableHttpResponse response = aDefault.execute(httpGet);

        // 拿到返回实体
        HttpEntity entity = response.getEntity();

        // 拿到内容
        String s = EntityUtils.toString(entity);
        System.out.println("s = " + s);

    }


    /**
     * 测试 BasicNameValuePair 名称值的对
     */
    @Test
    public void test02(){

        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("name", "scott");
        System.out.println("basicNameValuePair = " + basicNameValuePair);
    }

    /**
     *  get 请求url的参数拼接
     */
    @Test
    public void test04(){

        //创建一个url
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder("http://localhost:8080/demo01");
            //在url上拼接 数据
            uriBuilder.addParameter("name", "hello");

            System.out.println("uriBuilder = " + uriBuilder);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
