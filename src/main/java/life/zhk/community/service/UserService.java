package life.zhk.community.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import life.zhk.community.mapper.UserMapper;
import life.zhk.community.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public Map<String, Object> register(User user) {
        String salt= RandomUtil.randomString(6);
        String md5Pwd= SecureUtil.md5(user.getPassword()+salt);
        user.setPassword(md5Pwd);
        user.setSalt(salt);
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        user.setStatus(0);
        //String token = IdUtil.getSnowflake(1,1).nextIdStr();
        String token = UUID.randomUUID().toString();
        user.setToken(token);
        Map<String,Object> objectMap =new HashMap<>();
        int result=userMapper.insert(user);
        if(result>0){
            objectMap.put("code",200);
            objectMap.put("message","注册成功");
        }else{
            objectMap.put("code",400);
            objectMap.put("message","注册失败");
        }
        return objectMap;
    }

}
