package cn.sincerity.entity;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * TargetType
 *
 * @author Ht7_Sincerity
 * @date 2023/3/29
 */
@Data
@Accessors(chain = true)
public class TargetType {

    private Long id;

    private String name;
}
