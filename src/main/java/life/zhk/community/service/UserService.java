package life.zhk.community.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import life.zhk.community.mapper.UserMapper;
import life.zhk.community.model.User;
import life.zhk.community.model.UserExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public Map<String, Object> register(User user) {
        Map<String, Object> objectMap = new HashMap<>();
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> userSize = userMapper.selectByExample(example);
        if (userSize.size() > 0) {
            objectMap.put("code", 400);
            objectMap.put("message", "注册失败,帐号已被占用，请换一个！");
            return objectMap;
        }
        String salt = RandomUtil.randomString(6);
        String md5Pwd = SecureUtil.md5(user.getPassword() + salt);
        user.setPassword(md5Pwd);
        user.setSalt(salt);
        user.setGmtCreate(System.currentTimeMillis());
        user.setGmtModified(user.getGmtCreate());
        user.setStatus(0);
        //String token = IdUtil.getSnowflake(1,1).nextIdStr();
        String token = UUID.randomUUID().toString();
        user.setToken(token);

        int result = userMapper.insert(user);
        if (result > 0) {
            objectMap.put("code", 200);
            objectMap.put("message", "注册成功，请登录");
        } else {
            objectMap.put("code", 400);
            objectMap.put("message", "注册失败");
        }
        return objectMap;
    }

    public Map<String, Object> loginUser(User user, HttpServletResponse httpServletResponse) {
        Map<String, Object> objectMap = new HashMap<>();
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> userList = userMapper.selectByExample(example);
        if (userList.size() == 0) {
            objectMap.put("code", "400");
            objectMap.put("message", "用户名不存在");
            return objectMap;
        }
        if (userList.size() > 1) {
            objectMap.put("code", "400");
            objectMap.put("message", "帐号异常，请联系管理员");
            return objectMap;
        }
        String salt =userList.get(0).getSalt();
        String pwd =user.getPassword();
        String password = SecureUtil.md5(user.getPassword() + salt);
        UserExample userExample =new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId()).andPasswordEqualTo(password);
        List<User> userList1  = userMapper.selectByExample(userExample);
        if (userList1.size() == 0) {
            objectMap.put("code", "400");
            objectMap.put("message", "密码错误，请重试");
            return objectMap;
        }
        if (userList1.size() == 1) {
            objectMap.put("code", "200");
            objectMap.put("message", "登录成功");
            httpServletResponse.addCookie(new Cookie("token", userList1.get(0).getToken()));

            return objectMap;
        }
        return objectMap;
    }
}
