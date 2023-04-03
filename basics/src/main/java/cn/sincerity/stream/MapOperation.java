package cn.sincerity.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * MapOperation
 *
 * @author Ht7_Sincerity
 * @date 2023/3/21
 */
public class MapOperation {

    public static void main(String[] args) {
//        List<MultipleFieldType> list = new ArrayList<>(10);
//        for (int i = 0; i < 10; i++) {
//            list.add(new MultipleFieldType("first: " + i, "second: " + i, "third: " + i));
//        }
//        Map<String, List<MultipleFieldType>> collect = list.stream()
//                .collect(Collectors.groupingBy(MultipleFieldType::getFirst));
//        System.out.println(collect);
    }

    @Data
    @AllArgsConstructor
    static class MultipleFieldType {

        private String first;

        private String second;

        private String third;
    }
}
