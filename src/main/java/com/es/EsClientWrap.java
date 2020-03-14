package com.es;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by lijizhong on 2017/9/5.
 */
public class EsClientWrap {

    private static Logger LOG = LoggerFactory.getLogger(EsClientWrap.class);

    public static SearchResultVo getDocumentByContent(String index,String type,String content) throws UnsupportedEncodingException {
//        DSLSearchVo vo = new DSLSearchVo();
//        vo.setIndex(index);
//        vo.setType(type);
//        vo.setContent(content);
//        LOG.error("查询客户列表esQuery: {}", JSON.toJSONString(vo));
//        SearchResultVo list = EsClient.dslSearch(vo);
//        // LOG.error("查询客户列表es返回: {}", JSON.toJSONString(list));
        return null;
    }
}

