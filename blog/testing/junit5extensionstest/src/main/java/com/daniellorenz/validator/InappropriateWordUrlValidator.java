package com.daniellorenz.validator;

public class InappropriateWordUrlValidator implements UrlValidator {

    @Override
    public boolean check(String url) {
        return !url.contains("shit");
    }
}
