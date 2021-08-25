package life.zhk.community.controller;

import life.zhk.community.dto.AccessTokenDto;
import life.zhk.community.dto.GithubUser;
import life.zhk.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setCode(code);
        accessTokenDto.setClient_id(clientid);
        accessTokenDto.setClient_secret(secretid);
        accessTokenDto.setRedirect_uri(redirect);
        accessTokenDto.setState(state);
        String accesstoken=GitHubProvider.getAccessToken(accessTokenDto);
        System.out.println("accesstoken"+accesstoken);
        GithubUser githubUser=GitHubProvider.getUser(accesstoken);
        System.out.println("姓名-----------------"+githubUser.getName());
        return "redirect:/";
    }
}
