package cn.sincerity.shardingsphere.service.impl;

import cn.sincerity.shardingsphere.domain.entity.UserInfo;
import cn.sincerity.shardingsphere.domain.mapper.UserInfoMapper;
import cn.sincerity.shardingsphere.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ht7_Sincerity
 * @since 2023-07-27
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
