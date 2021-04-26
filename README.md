Support for using Esdsl in Spring boot apps
==========================================



# 如何使用?
-   Maven:
    ```xml
    <dependency>
        <groupId>com.manymobi</groupId>
        <artifactId>esdsl-spring-boot-starter</artifactId>
        <version>1.0.0-beta1</version>
    </dependency>
    <dependency>
        <groupId>org.elasticsearch.client</groupId>
        <artifactId>elasticsearch-rest-client</artifactId>
        <version>7.12.0</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-core</artifactId>
        <version>2.12.3</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-annotations</artifactId>
        <version>2.12.3</version>
    </dependency>
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.12.3</version>
    </dependency>
    ```
-   Gradle
    ```groovy
    implementation 'com.manymobi:esdsl-spring-boot-starter:1.0.0-beta1'
    implementation 'org.elasticsearch.client:elasticsearch-rest-client:7.12.0'
    implementation('com.fasterxml.jackson.core:jackson-core:2.12.3')
    implementation('com.fasterxml.jackson.core:jackson-annotations:2.12.3')
    implementation('com.fasterxml.jackson.core:jackson-databind:2.12.3')
    ```
    在 application.yml中配置
    
        spring:
            elasticsearch:
                rest:
                    uris: 172.20.20.210:9200
    在 Application 中添加 @EnableEsdsl 即可启动
