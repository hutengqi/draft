package cn.sincerity.convert;

import cn.sincerity.entity.SourceType;
import cn.sincerity.entity.TargetType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * MapStructConvertor
 *
 * @author Ht7_Sincerity
 * @date 2023/3/29
 */
@Mapper
public interface MapStructConvertor {

    MapStructConvertor INSTANCE = Mappers.getMapper(MapStructConvertor.class);

    TargetType source2Target(SourceType sourceType);
}
