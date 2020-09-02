package com.daniellorenz.validator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@ExtendWith(RandomBadUrlResolver.class)
class InappropriateWordUrlValidatorTest {

    UrlValidator cut;
    private static final List<String> badUrlsCollection = List.of("www.shit.com", "www.my.shit");
    private static final int reps = badUrlsCollection.size();

    private final Iterator<String> badUrls = List.of("www.shit.com", "www.my.shit").iterator();
    private static final Iterator<String> staticBadUrls = List.of("www.shit.com", "www.my.shit").iterator();

    @BeforeEach
    void setup(){
        cut = new InappropriateWordUrlValidator();
    }

    @Test
    void validUrlShouldPass(){
        String url = "www.daniellorenz.com";
        boolean result = cut.check(url);
        Assertions.assertTrue(result, "Valid URL should pass.");
    }

    @Test
    void inappropriateUrlShouldFail(){
        String url = "www.daniellorenz.com/shit";
        boolean result = cut.check(url);
        Assertions.assertFalse(result, "Inappropriate URL should fail.");
    }

    @RepeatedTest(5)
    void inappropriateUrlShouldFail_iterator(){
        String url = badUrls.next();
        System.out.println(url);
        boolean result = cut.check(url);
        Assertions.assertFalse(result, "Inappropriate URL should fail.");
    }

    @RepeatedTest(2)
    void staticInappropriateUrlShouldFail_iterator(){
        String url = staticBadUrls.next();
        System.out.println(url);
        boolean result = cut.check(url);
        Assertions.assertFalse(result, "Inappropriate URL should fail.");
    }

    @RepeatedTest(10)
    void randomInappropriateUrlShouldFail_local(){
        String url = badUrlsCollection.get(ThreadLocalRandom.current().nextInt(0, badUrlsCollection.size()));
        System.out.println(url);
        boolean result = cut.check(url);
        Assertions.assertFalse(result, "Inappropriate URL should fail.");
    }

    @RepeatedTest(10)
    void randomInappropriateUrlShouldFail(@RandomBadUrl String url){
        boolean result = cut.check(url);
        Assertions.assertFalse(result, "Inappropriate URL("+url+") should fail.");
    }

}