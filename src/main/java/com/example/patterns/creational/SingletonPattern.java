package com.example.patterns.creational;


import lombok.Getter;
import lombok.Setter;

final class SingletonPattern {

    private static SingletonPattern instance;

    @Getter
    @Setter
    private String data = "Some data";

    private SingletonPattern() {
    }

    public synchronized static SingletonPattern getInstance() {
        if (instance == null)  instance = new SingletonPattern();
        return instance;

    }
}

class App {

    public static void main(String[] args) {
        SingletonPattern instance = SingletonPattern.getInstance();
        System.out.println(instance.getData());

        SingletonPattern instance2 = SingletonPattern.getInstance();
        instance2.setData("new Data");

        System.out.println("i1 " + instance.getData());
        System.out.println("i2 " + instance2.getData());
    }
}



















final class SingletonPatternV2 {

    @Getter
    @Setter
    private String data = "Some data";

    private static class Loader {
        static final SingletonPatternV2 INSTANCE = new SingletonPatternV2();
    }

    private SingletonPatternV2() {}

    public static SingletonPatternV2 getInstance() {
        return Loader.INSTANCE;
    }
}
