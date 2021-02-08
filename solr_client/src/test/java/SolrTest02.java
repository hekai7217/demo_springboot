import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient.Builder;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 黄药师
 * @date 2021-02-08 14:34
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *  solr 的条件查询
 */
public class SolrTest02 {

    private String baseSolrUrl = "http://192.168.154.111:8983/solr/testcore";

    /**
     * 条件查询
     */
    @Test
    public void test01(){
        HttpSolrClient client = new HttpSolrClient.Builder(baseSolrUrl).build();

        SolrQuery solrQuery = new SolrQuery();

        //设置条件查询
        solrQuery.setQuery("myfield:系统");

        //分页的条件查询
        solrQuery.setStart(0);

        solrQuery.setRows(3);

        // 条件查询
        try {
            // 返回查询的结果
            QueryResponse response = client.query(solrQuery);

            // 查询的结果集 docList对象
            SolrDocumentList results = response.getResults();

            //分页查询可以拿到其他的数据
            /**
             * 查询的结果的总数
             */
            long numFound = results.getNumFound();
            System.out.println("numFound = " + numFound);

            /**
             *  分页的数据
             */
            int size = results.size();
            System.out.println("size = " + size);

            for (SolrDocument result : results) {
                getDocValue(result);
            }

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  高亮查询
     */
    @Test
    public void test02(){


        HttpSolrClient client = new HttpSolrClient.Builder(baseSolrUrl).build();

        SolrQuery solrQuery = new SolrQuery();

        //设置条件查询
        solrQuery.setQuery("myfield:系统");

        // 开启高亮查询
        solrQuery.setHighlight(true);

        //设置高亮查询的字段
        solrQuery.addHighlightField("myfield");

        // 前缀
        solrQuery.setHighlightSimplePre("<span style='color:red'>");

        // 后缀
        solrQuery.setHighlightSimplePost("</span>");

        //执行返回的结果
        try {
            QueryResponse response = client.query(solrQuery);

            // 高亮的查询结果的数据:
            // "115":{"myfield":["<span style-='color:red'>系统</span>接口"]}

            //查询的是普通的数据
            SolrDocumentList results = response.getResults();
            for (SolrDocument result : results) {
                //查询的字段的id  是 主键 是高亮中的存储的key

                Object id = result.getFieldValue("id");
                Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

                // 通过 id  获取高亮的数据
                Map<String, List<String>> stringListMap = highlighting.get(id);

                //高亮的数据
                List<String> colorTexts= stringListMap.get("myfield");
                String s = colorTexts.get(0);
                System.out.println("获取高亮的数据 s = " + s);
            }

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 打印 SolrDocument 中的数据
     *
     * @param doc
     * @throws SolrServerException
     * @throws IOException
     */
    private void getDocValue(SolrDocument doc) throws SolrServerException, IOException {

        Object id = doc.getFieldValue("id");
        System.out.println("id = " + id);

        Object myfield = doc.getFieldValue("myfield");
        System.out.println("myfield = " + myfield);
    }
}


