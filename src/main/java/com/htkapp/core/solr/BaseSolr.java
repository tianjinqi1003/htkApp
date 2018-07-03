package com.htkapp.core.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BaseSolr {

    @Resource
    private HttpSolrServer httpSolrServer;


    public Object getUser(String key) throws SolrServerException {

        //创建查询条件
        SolrQuery query = new SolrQuery();
        query.setQuery("user_name:" + key);

        QueryResponse response = this.httpSolrServer.query(query);
        SolrDocumentList list = response.getResults();
        return list.size() > 0 ? list.get(0) : null;
    }
}
