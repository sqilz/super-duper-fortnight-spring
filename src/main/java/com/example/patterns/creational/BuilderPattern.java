package com.example.patterns.creational;

import lombok.ToString;

@ToString
class BuilderPattern {
    private String a;
    private String b;

    public BuilderPattern(BuilderPatternBuilder builderPatternBuilder) {
        this.a = builderPatternBuilder.a;
        this.b = builderPatternBuilder.b;
    }

    public static BuilderPatternBuilder builder() {
        return new BuilderPatternBuilder();
    }

    public static class BuilderPatternBuilder {

        private String a;
        private String b;

        public BuilderPatternBuilder setA(String a) {
            this.a = a;
            return this;
        }

        public BuilderPatternBuilder setB(String b) {
            this.b = b;
            return this;
        }

        public BuilderPattern build() {
            return new BuilderPattern(this);
        }

    }
}

class AppBuilder {
    public static void main(String[] args) {
        BuilderPattern build = BuilderPattern.builder()
                .setA("hi")
                .setB("hello")
                .build();

        System.out.println(build.toString());
    }
}
