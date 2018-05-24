package com.alex;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * Created by caozhennan on 2018/3/28.
 */
public class TestClosure {
    public List<Supplier<Integer>> squares() {
        List<Supplier<Integer>> sqr = new ArrayList<>(10);
        IntStream.range(0, 10).forEach(v -> sqr.add(() -> v * v));
        return sqr;
    }

    public static void main(String[] args) {
        TestClosure tc = new TestClosure();
        tc.squares().stream().forEach(v -> System.out.println(v.get()));
    }
}
