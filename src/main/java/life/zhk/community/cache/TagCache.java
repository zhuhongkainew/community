package life.zhk.community.cache;

import life.zhk.community.dto.TagDto;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDto> getTag() {
        List<TagDto> TagDtoS = new ArrayList<>();
        TagDto program = new TagDto();
        program.setTagName("开发语言");
        program.setTags(Arrays.asList("javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        TagDtoS.add(program);
        TagDto framework = new TagDto();
        framework.setTagName("平台框架");
        framework.setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        TagDtoS.add(framework);


        TagDto server = new TagDto();
        server.setTagName("服务器");
        server.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        TagDtoS.add(server);

        TagDto db = new TagDto();
        db.setTagName("数据库");
        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
        TagDtoS.add(db);

        TagDto tool = new TagDto();
        tool.setTagName("开发工具");
        tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
        TagDtoS.add(tool);
        return TagDtoS;
    }

    public static String checkTag(String tag){
        //StringUtils.isBlank(a)||
        String[] strings = tag.split(",");
        List<TagDto> tagDtoList =getTag();
        List<String> stringList = tagDtoList.stream().flatMap(s -> s.getTags().stream()).collect(Collectors.toList());
        String collect = Arrays.stream(strings).filter(a ->  !stringList.contains(a)).collect(Collectors.joining(","));
        return collect;
    }
}
