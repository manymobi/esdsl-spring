package com.manymobi.esdsl.spring.boot.autoconfigure;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * @author 梁建军
 * 创建日期： 2019/2/27
 * 创建时间： 17:15
 * @version 1.0
 * @since 1.0
 */
public final class FileRecognitionFailureAnalyzer
        extends AbstractFailureAnalyzer<EsdslAutoConfiguration.FileRecognitionException> {


    @Override
    protected FailureAnalysis analyze(Throwable rootFailure,
                                      EsdslAutoConfiguration.FileRecognitionException cause) {
        return getFailureAnalysis(cause);
    }

    private FailureAnalysis getFailureAnalysis(EsdslAutoConfiguration.FileRecognitionException cause) {
        String description = getDescription(cause);
        String action = getAction(cause);
        return new FailureAnalysis(description, action, cause);
    }

    private String getDescription(EsdslAutoConfiguration.FileRecognitionException cause) {
        StringBuilder description = new StringBuilder();
        description.append("Failed to configure a EsdslResource: ");

        RecognitionException exception = cause.getE();
        Token offendingToken = exception.getOffendingToken();
        description.append(String.format("File (%s) Error:(%s,%s) 附近存在语法问题%n", cause.getFileName(), offendingToken.getLine(), offendingToken.getCharPositionInLine()));


        description.append(String.format("%nReason: %s%n", cause.getMessage()));
        return description.toString();
    }

    private String getAction(EsdslAutoConfiguration.FileRecognitionException cause) {
        StringBuilder action = new StringBuilder();
        action.append(String.format("Consider the following:%n"));
        RecognitionException exception = cause.getE();
        Token offendingToken = exception.getOffendingToken();
        action.append(String.format("修改 (%s(%s,%s))  附近存在语法问题", cause.getFileName(), offendingToken.getLine(), offendingToken.getCharPositionInLine()));
        return action.toString();
    }
}
