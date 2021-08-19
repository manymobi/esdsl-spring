package com.manymobi.esdsl.spring.boot.autoconfigure;

import com.manymobi.esdsl.Esdsl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author 梁建军
 * 创建日期： 2018/11/8
 * 创建时间： 11:57
 * @version 1.0
 * @since 1.0
 */
public class MapperFactoryBean implements FactoryBean<Object>, ApplicationContextAware {

    private Class<Object> type;


    private ApplicationContext applicationContext;

    @Override
    public Object getObject() throws Exception {

        Esdsl esdsl = applicationContext.getBean(Esdsl.class);
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

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
