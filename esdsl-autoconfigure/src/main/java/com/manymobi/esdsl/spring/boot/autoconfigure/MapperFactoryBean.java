package com.manymobi.esdsl.spring.boot.autoconfigure;

import com.manymobi.esdsl.Esdsl;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author 梁建军
 * 创建日期： 2018/11/8
 * 创建时间： 11:57
 * @version 1.0
 * @since 1.0
 */
public class MapperFactoryBean implements FactoryBean<Object> {

    private Class<Object> type;

    private Esdsl esdsl;

    @Override
    public Object getObject() throws Exception {
        return esdsl.target(type);
    }

    @Override
    public Class<?> getObjectType() {
        return this.type;
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<Object> type) {
        this.type = type;
    }

    public Esdsl getEsdsl() {
        return esdsl;
    }

    public void setEsdsl(Esdsl esdsl) {
        this.esdsl = esdsl;
    }
}
