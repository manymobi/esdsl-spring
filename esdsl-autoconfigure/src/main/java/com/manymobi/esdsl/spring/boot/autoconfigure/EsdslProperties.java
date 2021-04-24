package com.manymobi.esdsl.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 梁建军
 * 创建日期： 2018/11/7
 * 创建时间： 13:26
 * @version 1.0
 * @since 1.0
 */
@ConfigurationProperties(prefix = EsdslProperties.ESDSL_PREFIX)
public class EsdslProperties {

    public static final String ESDSL_PREFIX = "esdsl";
}
