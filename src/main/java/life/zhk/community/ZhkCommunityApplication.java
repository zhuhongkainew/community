package life.zhk.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("life.zhk.community.mapper")
public class ZhkCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhkCommunityApplication.class, args);
    }

}
