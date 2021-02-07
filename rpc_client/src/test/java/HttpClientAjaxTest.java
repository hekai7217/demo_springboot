import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author 黄药师
 * @date 2021-02-06 10:26
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *  发送 ajax  请求
 *
 */
public class HttpClientAjaxTest {

    /**
     *
     *  细节:
     *
     *    01 client 发送数据的是 内容为  json  (格式 )
     *
     *    02  设置json格式 ContentType.APPLICATION_JSON
     *
     *    03 服务器拿到json的数据内容的需要进行 转换 直接绑定时不行的
     *
     *
     * @throws IOException
     */
    @Test
    public void test01() throws IOException {

        // 01 创建一个 httpclient

        CloseableHttpClient aDefault = HttpClients.createDefault();

        //02 httpPost 请求
        HttpPost httpPost = new HttpPost("http://localhost:8080/demo02");

        //03 创建一个字符串请求体

        String  jsonStr = "{\"name\":\"zhangsan\"}";

        //发送数据的内行 是 json数据格式
        ContentType contentType = ContentType.APPLICATION_JSON;
        // 发送数据登录类型
        StringEntity stringEntity = new StringEntity(jsonStr, contentType);

        httpPost.setEntity(stringEntity);

        //执行 请求
        CloseableHttpResponse response = aDefault.execute(httpPost);

        HttpEntity entity = response.getEntity();

        String s = EntityUtils.toString(entity);

        System.out.println("s = " + s);
    }

    @Test
    public void test02() throws IOException {

        // 01 创建一个 httpclient

        CloseableHttpClient aDefault = HttpClients.createDefault();

        //02 httpPost 请求
        HttpPost httpPost = new HttpPost("http://localhost:8080/demo03");

        //03 创建一个字符串请求体

        String  jsonStr = "{\"name\":\"zhangsan\",\"age\":20}";

        //发送数据的内行 是 json数据格式
        ContentType contentType = ContentType.APPLICATION_JSON;
        // 发送数据登录类型
        StringEntity stringEntity = new StringEntity(jsonStr, contentType);

        httpPost.setEntity(stringEntity);

        //执行 请求
        CloseableHttpResponse response = aDefault.execute(httpPost);

        HttpEntity entity = response.getEntity();

        String s = EntityUtils.toString(entity);

        System.out.println("s = " + s);
    }
}
