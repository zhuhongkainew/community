package life.zhk.community.provider;

import life.zhk.community.dto.AccessTokenDto;
import org.springframework.stereotype.Component;

@Component
public class GitHubProvider {
public  String getAccessToken(AccessTokenDto AccessTokenDto){
    AccessTokenDto.setCode("");
    return "";
}

}
