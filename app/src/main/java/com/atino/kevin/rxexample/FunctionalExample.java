package com.atino.kevin.rxexample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;

public class FunctionalExample {
    public List<Integer> imperativeList() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> output = new ArrayList<>();

        for (int in : input) {
            if (in % 2 != 0) {
                output.add(in);
            }
        }

        return output;
    }

    public List<Integer> declarativeList() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        return input
                .stream()
                .filter(integer -> integer % 2 != 0)
                .collect(Collectors.toList());
    }

    public List<Integer> observableList() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        return Observable
                .fromIterable(input)
                .filter(integer -> integer % 2 != 0)
                .toList()
                .blockingGet();
    }
}
