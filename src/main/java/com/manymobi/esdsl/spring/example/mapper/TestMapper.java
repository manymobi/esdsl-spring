package com.manymobi.esdsl.spring.example.mapper;

import com.manymobi.esdsl.annotations.Mapper;
import com.manymobi.esdsl.annotations.Param;
import com.manymobi.esdsl.annotations.RequestBody;
import com.manymobi.esdsl.annotations.RequestMapping;
import com.manymobi.esdsl.annotations.RequestMethod;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author 梁建军
 * 创建日期： 2021/4/18
 * 创建时间： 上午 11:28
 * @version 1.0
 * @since 1.0
 */
@Repository
@Mapper("esdsl/test.esdsl")
public interface TestMapper {

    @RequestMapping(value = "/test/_doc", method = RequestMethod.POST)
    Map add(@RequestBody Bean content);

    @RequestMapping(value = "/test/_doc/${id}", method = RequestMethod.GET)
    Map get(@Param("id") String id);

    @RequestMapping(value = "/test/_doc/${id}", method = RequestMethod.DELETE)
    Map delete(@Param("id") String id);

    Map analyze();

    class Bean {
        String key;

        String content;


        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
