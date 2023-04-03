package cn.sincerity.entity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * SourceType
 *
 * @author Ht7_Sincerity
 * @date 2023/3/29
 */
@Data
@Accessors(chain = true)
public class SourceType {

    private Long id;

    private String name;
}
