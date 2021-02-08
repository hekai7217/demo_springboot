package com.jutixueyuan;

import com.jutixueyuan.bean.Menu;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;

import java.util.List;

/**
 * @author 黄药师
 * @date 2021-02-08 15:57
 * @desc 巨梯学院 http://www.jutixueyuan.com
 *
 *   测试springdata 项目下面的 操作 solr
 */
@SpringBootTest
public class SolrTest {

    /**
     *  springdata  进行操作操作
     *
     *   提供了很多 xxxTemplate 进行数据操作  自动集成 可以直接使用
     *   SolrTemplate 对solr 进行模板操作
     *
     *
     */
    @Autowired
    private SolrTemplate solrTemplate;


    /**
     *
     *  private String baseSolrUrl = "http://192.168.154.111:8983/solr/testcore";
     *
     *  springdata 的host的配置到 solr 中 需要配置 coreAdminName
     *
     *  查询的solr 中的数据的名称
     *
     */
    private String coreAdminName = "testcore";
    /**
     * 使用模板 操作 solr
     *
     *  和之前的方法都是差不多的
     */
    @Test
    public void test01(){

        // 条件查询
        SimpleQuery simpleQuery = new SimpleQuery();

        // Criteria 条件查询对象
        // where  条件查询的字段
        // is     条件查询的值
//        simpleQuery.addCriteria(Criteria.where("myfield").is("系统"));
        simpleQuery.addCriteria(Criteria.where("id").is("777"));


        //之前的写法 返回doc对象 自己解析

        // 可以把返回的字段 封装到一个对象中 传入封装的 Class 就可以

        //设置分页
//        simpleQuery.setOffset(0L);
//        simpleQuery.setRows(3);

        Page<Menu> query = solrTemplate.query(coreAdminName, simpleQuery, Menu.class);

        // 拿到查询的内容
        List<Menu> content = query.getContent();

        System.out.println(content);

    }

    //添加
    @Test
    public void insert(){

        //创建一个 SolrInputDocument
        SolrInputDocument solrInput = new SolrInputDocument();

        solrInput.addField("id","777");
        solrInput.addField("myfield","中了500w");

        UpdateResponse updateResponse = solrTemplate.saveDocument(coreAdminName, solrInput);


        //事务
        solrTemplate.commit(coreAdminName);
        int status = updateResponse.getStatus();

        if (status == 0){
            System.out.println("添加成功..");
        }
    }

    //删除
    @Test
    public void detele(){

        UpdateResponse updateResponse = solrTemplate.deleteByIds(coreAdminName, "777");

        //设置条件的删除
//        SimpleQuery solrDataQuery = new SimpleQuery();
//        solrDataQuery.addCriteria()
//        solrTemplate.delete(coreAdminName,query);

        //事务
        solrTemplate.commit(coreAdminName);
        int status = updateResponse.getStatus();

        if (status == 0){
            System.out.println("删除成功..");
        }

    }

    @Test
    public void test03(){

        //设置高亮查询

        HighlightQuery param = new SimpleHighlightQuery();
        //设置查询的属性
        //查询的条件和属性
        param.addCriteria(Criteria.where("myfield").is("系统"));

        param.setOffset(0L);
        param.setRows(2);

        // 设置对象
        HighlightOptions options = new HighlightOptions();

        //设置高亮的字段
        options.addField("myfield");

        //设置高亮的前缀和后缀
        options.setSimplePrefix("<span style='color:red'>");
        options.setSimplePostfix("</span>");

        //设置高亮查询的配置
        param.setHighlightOptions(options);
        /**
         *  coreAdminName 查询的数据库
         *  options 高亮查询的设置
         *
         *  Menu 查询介绍的对象
         */
        // 返回的查询结果
        HighlightPage<Menu> highlightPage = solrTemplate.queryForHighlightPage(coreAdminName, param, Menu.class);

        // 返回查询的结果 没有高亮
        List<Menu> content = highlightPage.getContent();
        System.out.println("content = " + content);

        //getTotalPages
        // 分页的总数
        int totalPages = highlightPage.getTotalPages();
        System.out.println("分页的总数:totalPages = " + totalPages);

        //查询所有的数据总数
        long totalElements = highlightPage.getTotalElements();
        System.out.println("查询所有的数据总数: totalElements = " + totalElements);


        System.out.println(" ================== 获取高亮的数据 ==================== ");
        // 高亮的查询结果的数据:
        // "115":{"myfield":["<span style-='color:red'>系统</span>接口"]}  不会存储到 list中

        // 从 page结果中获取高亮的数据
        List<HighlightEntry<Menu>> highlightList = highlightPage.getHighlighted();

        for (HighlightEntry<Menu> menuHighlightEntry : highlightList) {
            // 拿到 每个数据 "115":{"myfield":["<span style-='color:red'>系统</span>接口"]}

            // 拿到集合的entry 结果
            List<HighlightEntry.Highlight> highlights = menuHighlightEntry.getHighlights();

            // 遍历每一个entry
            for (HighlightEntry.Highlight highlight : highlights) {
                // 拿到 高亮的数据
                // 高亮的字符串可以是多个部分 当前这里只获取第一个
                List<String> snipplets = highlight.getSnipplets();
                String s = snipplets.get(0);
                System.out.println("s = " + s);
            }
        }

    }
}
