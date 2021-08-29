package life.zhk.community.controller;

import life.zhk.community.dto.AccessTokenDto;
import life.zhk.community.dto.GithubUser;
import life.zhk.community.mapper.UserMapper;
import life.zhk.community.model.User;
import life.zhk.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller

public class AuthorizeController {
    @Value("${github.community.clientid}")
    private String clientid;
    @Value("${github.community.secretid}")
    private String secretid;
    @Value("${github.community.redirect}")
    private String redirect;

    @Autowired
    private GitHubProvider GitHubProvider;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setClient_id(clientid);
        accessTokenDto.setClient_secret(secretid);
        accessTokenDto.setRedirect_uri(redirect);
        accessTokenDto.setState(state);
        String accesstoken = GitHubProvider.getAccessToken(accessTokenDto);
        System.out.println("accesstoken" + accesstoken);
        GithubUser  githubUser= GitHubProvider.getUser(accesstoken);
        System.out.println("姓名-----------------" + githubUser.getName());
        if(githubUser.getName()!=null){
            User user =new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatarUrl());
            user.setName(githubUser.getName());
            user.setBio(githubUser.getBio());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //无需写入session
            //request.getSession().setAttribute("user",githubUser);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }   else {
            return "redirect:/";
        }
    }
}
