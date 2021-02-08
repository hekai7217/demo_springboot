package com.jutixueyuan.bean;

import lombok.Data;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * @author 黄药师
 * @date 2021-02-08 16:09
 * @desc 巨梯学院 http://www.jutixueyuan.com
 */
@Data
@SolrDocument(collection = "testcore")  // 标记 当前的类的对应的solr中的库
public class Menu {

    @Id
    @Field(value = "id")   // 当前的字段应solr中查询的返回字段
    private Long menu_id;

    @Field(value = "myfield") // 当前的字段应solr中查询的返回字段
    private String menu_name;

}

