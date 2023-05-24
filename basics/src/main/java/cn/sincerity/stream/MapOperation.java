package cn.sincerity.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * MapOperation
 *
 * @author Ht7_Sincerity
 * @date 2023/3/21
 */
public class MapOperation {

    public static void main(String[] args) {
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    @Data
    @AllArgsConstructor
    static class MultipleFieldType {

        private String first;

        private String second;

        private String third;
    }

    @Data
    @AllArgsConstructor
    static class Before {
        private Integer sort;
    }

    @Data
    @AllArgsConstructor
    static class After {
        private Integer sort;
    }
}
