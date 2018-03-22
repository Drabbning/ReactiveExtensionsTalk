package com.atino.kevin.rxexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;

public class FunctionalExample {
    public static List<Integer> inputValues() {
        return Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    public static List<Integer> imperativeList() {
        List<Integer> output = new ArrayList<>();

        for (int in : inputValues()) {
            if (in % 2 != 0) {
                output.add(in);
            }
        }

        return output;
    }

    public static List<Integer> declarativeList() {
        return inputValues()
                .stream()
                .filter(integer -> integer % 2 != 0)
                .collect(Collectors.toList());
    }

    public static List<Integer> observableBlockingList() {
        return Observable
                .fromIterable(inputValues())
                .filter(integer -> integer % 2 != 0)
                .toList()
                .blockingGet();
    }

    public static Observable<Integer> observableList() {
        return Observable
                .fromIterable(inputValues())
                .filter(integer -> integer % 2 != 0);
    }
}
