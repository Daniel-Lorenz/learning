package com.daniellorenz.validator;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBadUrlResolver implements ParameterResolver {

    private static final List<String> badUrls = List.of("www.shit.com", "www.my.shit", "www.sh1t.com");


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(RandomBadUrl.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return randomBadUrl();
    }

    private String randomBadUrl() {
        return badUrls.get(ThreadLocalRandom.current().nextInt(0, badUrls.size()));
    }
}
