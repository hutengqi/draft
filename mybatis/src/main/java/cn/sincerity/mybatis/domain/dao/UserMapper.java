package cn.sincerity.mybatis.domain.dao;

import cn.sincerity.mybatis.domain.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * UserMapper
 *
 * @author Ht7_Sincerity
 * @date 2023/8/10
 */
@Repository
public interface UserMapper {

    User getUserById(@Param("id") Long id);
}
