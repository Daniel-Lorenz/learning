package com.daniellorenz.validator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

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
    @Nested
    class RepeatedTests {
        @RepeatedTest(5)
        void inappropriateUrlShouldFail_iterator() {
            String url = badUrls.next();
            System.out.println(url);
            boolean result = cut.check(url);
            Assertions.assertFalse(result, "Inappropriate URL should fail.");
        }

        @RepeatedTest(2)
        void staticInappropriateUrlShouldFail_iterator() {
            String url = staticBadUrls.next();
            System.out.println(url);
            boolean result = cut.check(url);
            Assertions.assertFalse(result, "Inappropriate URL should fail.");
        }

        @RepeatedTest(10)
        void randomInappropriateUrlShouldFail_local() {
            String url = badUrlsCollection.get(ThreadLocalRandom.current().nextInt(0, badUrlsCollection.size()));
            System.out.println(url);
            boolean result = cut.check(url);
            Assertions.assertFalse(result, "Inappropriate URL should fail.");
        }

        @RepeatedTest(10)
        void randomInappropriateUrlShouldFail(@RandomBadUrl String url) {
            boolean result = cut.check(url);
            Assertions.assertFalse(result, "Inappropriate URL(" + url + ") should fail.");
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class ParameterizedTests{

        @ParameterizedTest
        @ValueSource(strings = { "www.shit.com", "www.daniellorenz.com/shit"})
        void valueSourceInappropriateUrlShouldFail(String url){
            boolean result = cut.check(url);
            Assertions.assertFalse(result, "Inappropriate URL(" + url + ") should fail.");
        }

        /**
         * This is difficult in nested test classes.
         * Typically, the method (allBadUrls) has to be static. However, nested classes cannot have static members.
         * Moving the static method to the parent class works, but we have to use the fully qualified name.
         * A possible workaround is the @TestInstance(TestInstace.Lifecycle.PER_CLASS) annotation, which allows the
         * method source to be non-static, however now all tests of this nested class are executed using the same
         * class instance.
         *
         */
        @ParameterizedTest
        @MethodSource("allBadUrls")
        void methodSourceInappropriateUrlShouldFail(String url){
            boolean result = cut.check(url);
            Assertions.assertFalse(result, "Inappropriate URL(" + url + ") should fail.");
        }

        private  Stream<String> allBadUrls(){
            return badUrlsCollection.stream();
        }

        @ParameterizedTest
        @MethodSource("com.daniellorenz.validator.InappropriateWordUrlValidatorTest#allBadUrls")
        void staticMethodSourceInappropriateUrlShouldFail(String url){
            boolean result = cut.check(url);
            Assertions.assertFalse(result, "Inappropriate URL(" + url + ") should fail.");
        }

        @ParameterizedTest
        @CsvSource({
                "www.shit.com",
                "www.daniellorenz.com/shit"
        })
        void csvSourceInappropriateUrlShouldFail(String url) {
            boolean result = cut.check(url);
            Assertions.assertFalse(result, "Inappropriate URL(" + url + ") should fail.");
        }

        @ParameterizedTest
        @CsvFileSource(resources = {"/allValidUrls.csv"})
        void allValidUrlsShouldPass(String url){
            boolean result = cut.check(url);
            Assertions.assertTrue(result, "Valid URL should pass.");
        }

        @ParameterizedTest
        @CsvFileSource(resources = {"/allBadUrls.csv"})
        void allInvalidUrlsShouldFail(String url){
            boolean result = cut.check(url);
            Assertions.assertFalse(result, "Inappropriate URL should fail.");
        }

    }

    private static Stream<String> allBadUrls(){
        return badUrlsCollection.stream();
    }


}