package com.daniellorenz.streams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ComparisonTests {

    private final Car renault = new Car(UUID.randomUUID(), "renault", "lantern", "green");
    private final Car opel = new Car(UUID.randomUUID(), "opel", "bulldog", "blue");
    private final Car vw = new Car(UUID.randomUUID(), "vw", "kitten", "yellow");
    private final CarDto renaultDto = new CarDto("RENAULT", "lantern", "black");
    private final CarDto vwDto = new CarDto("VW", "kitten", "black");

    @Test
    void should() {

        java.util.List<Car> carsInDb = java.util.List.of(opel, vw, renault);

        java.util.List<CarDto> carsFromOutside = java.util.List.of(renaultDto, vwDto);

        java.util.List<Car> stillInDb = findCarsInDbThatAreAlsoFromOutsideByCodeName(carsInDb, carsFromOutside);
        Predicate<? super Car> codeNameIsUsedInCarsFromOutside = filterBabasasda(carsFromOutside, CarDto::getCodeName);
        Predicate<Car> genericCodeNameIsUsedInCarsFromOutside = filterasdasdasd(carsFromOutside, CarDto::getCodeName, Car::getCodeName);

        assertTrue(codeNameIsUsedInCarsFromOutside.test(renault));
        assertFalse(codeNameIsUsedInCarsFromOutside.test(opel));
        assertTrue(codeNameIsUsedInCarsFromOutside.test(vw));
        assertTrue(genericCodeNameIsUsedInCarsFromOutside.test(renault));
        assertFalse(genericCodeNameIsUsedInCarsFromOutside.test(opel));
        assertTrue(genericCodeNameIsUsedInCarsFromOutside.test(vw));
        assertThat(List.of(renault, vw)).containsExactlyInAnyOrderElementsOf(stillInDb);

    }

    private List<Car> findCarsInDbThatAreAlsoFromOutsideByCodeName(List<Car> carsInDb, List<CarDto> carsFromOutside) {
        Predicate<? super Car> codeNameIsUsedInCarsFromOutside = filterBabasasda(carsFromOutside, CarDto::getCodeName);
        return carsInDb.stream().filter(codeNameIsUsedInCarsFromOutside).collect(Collectors.toList());
    }

    private <T> Predicate<Car> filterBabasasda(List<CarDto> carsFromOutside, Function<CarDto, String> mappingToValue) {
        Predicate<Car> start = (car) -> false;
        return carsFromOutside.stream().map(mappingToValue)
                .map((el) -> (Predicate<Car>) (car) -> car.getCodeName().contentEquals(el))
                .reduce(start, Predicate::or);
    }
    private <T, V, W> Predicate<W> filterasdasdasd(List<T> carsFromOutside, Function<T, V> mappingToValue, Function<W, V> mappingToValueOnOut) {
        Predicate<W> start = (w) -> false;
        return carsFromOutside.stream().map(mappingToValue)
                .map((el) -> (Predicate<W>) (w) -> mappingToValueOnOut.apply(w).equals(el))
                .reduce(start, Predicate::or);
    }

    @AllArgsConstructor
    @Getter
    class CarDto {
        private final String model;
        private final String codeName; //external ID
        private final String color;
    }

    @AllArgsConstructor
    @RequiredArgsConstructor
    @Getter
    class Car {

        private final UUID guid;
        private final String model;
        private final String codeName; //external ID
        private String color = "black";

        public void setColor(String color) {
            this.color = color;
        }

    }
}
