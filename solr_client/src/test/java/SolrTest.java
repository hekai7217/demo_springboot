import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author 黄药师
 * @date 2021-02-08 14:18
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *  通过java的api操作solr
 */
public class SolrTest {

    private String baseSolrUrl = "http://192.168.154.111:8983/solr/testcore";

    /**
     *   测试 通过java代码连接solr 查询数据
     */
    @Test
    public void test01(){

        // 01 创建solrClient
        HttpSolrClient client = new HttpSolrClient.Builder(baseSolrUrl).build();

        //02 调用方法

        // 返回的是doc对象
        try {
            SolrDocument doc = client.getById("101");
            getDocValue(doc);

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  打印  doc中的对象数据
     * @param SolrDocument
     * @throws SolrServerException
     * @throws IOException
     */
    private void getDocValue(SolrDocument doc) throws SolrServerException, IOException {

        Object id = doc.getFieldValue("id");
        System.out.println("id = " + id);

        Object myfield = doc.getFieldValue("myfield");
        System.out.println("myfield = " + myfield);
    }

    /**
     * 通过多个id查询对象
     */
    @Test
    public void test02(){

        HttpSolrClient client = new HttpSolrClient.Builder(baseSolrUrl).build();

        try {
            SolrDocumentList docList = client.getById(Arrays.asList("101", "102", "103"));

            for (SolrDocument document : docList) {
                getDocValue(document);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  给solr中添加数据
     */
    @Test
    public void test03(){

        // 01 创建solrClient
        HttpSolrClient client = new HttpSolrClient.Builder(baseSolrUrl).build();

        SolrInputDocument solrDocument = new SolrInputDocument ();

        //设置字段和值
        //如果 id存在 添加就是更新操作
        solrDocument.addField("id","666");
        solrDocument.addField("myfield","中了500万");
        //添加数据
        try {

            //添加返回 UpdateResponse
            UpdateResponse response = client.add(solrDocument);

            // 拿到状态 == 0 成功
            int status = response.getStatus();

            if (status ==0){
                System.out.println(" 添加成功 " );
            }
            // 添加数据 需要提交事务
            client.commit();

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  给solr中删除数据
     */
    @Test
    public void test04(){

        // 01 创建solrClient
        HttpSolrClient client = new HttpSolrClient.Builder(baseSolrUrl).build();

        UpdateResponse updateResponse = null;
        try {
            updateResponse = client.deleteById("900");

            int status = updateResponse.getStatus();
            if (status == 0){
                System.out.println(" 删除成功 ..."  );
            }

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
