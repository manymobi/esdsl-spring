package com.manymobi.esdsl.spring.boot.autoconfigure;

import com.manymobi.esdsl.Esdsl;
import com.manymobi.esdsl.handler.EsdslFileResourceHandler;
import com.manymobi.esdsl.handler.JsonEncoder;
import com.manymobi.esdsl.handler.ParamHandler;
import com.manymobi.esdsl.handler.RequestHandler;
import com.manymobi.esdsl.handler.ResponseBodyHandler;
import com.manymobi.esdsl.handler.ResponseContextHandler;
import com.manymobi.esdsl.handler.RestHandler;
import com.manymobi.esdsl.handler.VariableHandler;
import com.manymobi.esdsl.handler.impl.DefaultRestHandler;
import com.manymobi.esdsl.handler.impl.DefaultVariableHandler;
import com.manymobi.esdsl.handler.impl.FaultTolerantRequestHandler;
import com.manymobi.esdsl.handler.impl.JacksonJsonEncoder;
import com.manymobi.esdsl.handler.impl.parms.NumberParamHandler;
import com.manymobi.esdsl.handler.impl.parms.ObjectParamHandler;
import com.manymobi.esdsl.handler.impl.parms.StringParamHandler;
import com.manymobi.esdsl.handler.impl.response.ObjectResponseBodyHandler;
import com.manymobi.esdsl.handler.impl.response.ObjectResponseContextHandler;
import org.antlr.v4.runtime.RecognitionException;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;

/**
 * @author 梁建军
 * 创建日期： 2018/11/8
 * 创建时间： 13:58
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableConfigurationProperties({EsdslProperties.class})
@Import(RestClientAutoConfiguration.class)
public class EsdslAutoConfiguration {


    @Autowired
    private ApplicationContext applicationContext;


    @Bean
    @ConditionalOnMissingBean
    public RestHandler restHandler(RestClient restClient) {
        return new DefaultRestHandler(restClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public JsonEncoder jsonHandler() {
        return new JacksonJsonEncoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public VariableHandler variableHandler() {
        return new DefaultVariableHandler();
    }


    @Bean
    @ConditionalOnMissingBean
    public EsdslFileResourceHandler esdslFileResourceHandler() {

        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        return fileName -> {
            Resource resource = resourceResolver.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + fileName);
            try {
                return resource.getInputStream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        };
    }


    @Bean
    @Order(1)
    public ParamHandler.Build stringParamHandler() {
        return new StringParamHandler.Build();
    }

    @Bean
    @Order(2)
    public ParamHandler.Build numberParamHandler() {
        return new NumberParamHandler.Build();
    }

    @Bean
    @Order
    public ParamHandler.Build objectParamHandler() {
        return new ObjectParamHandler.Build();
    }


    @Bean
    public ResponseBodyHandler responseBodyHandlers() {
        return new ObjectResponseBodyHandler();
    }

    @Bean
    public ResponseContextHandler responseContextHandlers() {
        return new ObjectResponseContextHandler();
    }


    @Bean
    public RequestHandler faultTolerantRequestHandler() {
        return new FaultTolerantRequestHandler();
    }

    @PreDestroy
    public void close() throws Exception {
        RestHandler restHandler = applicationContext.getBean(RestHandler.class);
        restHandler.close();
    }


    @Bean
    public Esdsl esdsl(JsonEncoder jsonEncoder,
                       VariableHandler variableHandler,
                       RestHandler restHandler,
                       EsdslFileResourceHandler esdslFileResourceHandler,
                       List<ParamHandler.Build> paramHandlers,
                       List<ResponseBodyHandler> responseBodyHandlers,
                       List<ResponseContextHandler> responseContextHandlers,
                       List<RequestHandler> requestHandlers) {
        Esdsl.Build build = new Esdsl.Build();

        build.setRestHandler(restHandler);
        build.setEsdslFileResourceHandler(esdslFileResourceHandler);
        build.setJsonEncoder(jsonEncoder);
        build.setVariableHandler(variableHandler);
        build.setParamHandlers(paramHandlers);
        build.setResponseBodyHandlers(responseBodyHandlers);
        build.setResponseContextHandlers(responseContextHandlers);
        build.setRequestHandlers(requestHandlers);

        return build.build();
    }

    static class FileRecognitionException extends BeanCreationException {

        private final String fileName;
        private final RecognitionException e;

        public FileRecognitionException(String fileName, RecognitionException e) {
            super("文件识别异常");
            this.fileName = fileName;
            this.e = e;
        }

        public String getFileName() {
            return fileName;
        }

        public RecognitionException getE() {
            return e;
        }
    }

}
