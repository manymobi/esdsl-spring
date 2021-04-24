package com.manymobi.esdsl.spring.example.mapper;

import com.manymobi.esdsl.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author 梁建军
 * 创建日期： 2021/4/24
 * 创建时间： 下午8:43
 * @version 1.0
 * @since 1.0
 */
@SpringBootTest
class TestMapperTest {


    @Autowired
    private TestMapper testMapper;

    @Test
    void add() {
        TestMapper.Bean bean = new TestMapper.Bean();
        bean.setContent("content");
        bean.setKey("key");
        Map add = testMapper.add(bean);

        Object id = add.get("_id");

        Map map = testMapper.get(id.toString());

        System.out.println(map);


        Map delete = testMapper.delete(id.toString());
        System.out.println(delete);
    }

    @Test
    void delete() {
        Assertions.assertThrows(NotFoundException.class, () -> testMapper.delete("111"));
    }

    @Test
    void analyze() {
        Map analyze = testMapper.analyze();

        System.out.println(analyze);
    }
}
