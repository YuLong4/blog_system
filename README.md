# 个人博客系统

该个人博客系统是一个基于Spring Boot的Web应用程序，旨在为用户提供一个简单而强大的博客平台。该项目采用了许多现代技术，以提高应用程序的性能和可靠性。

首先，该项目使用了Spring Boot框架，这是一个用于创建基于Java的Web应用程序的强大框架。Spring Boot提供了许多功能和工具，可以大大简化开发过程，并加快应用程序的部署和运行速度。此外，Spring Boot还内置了许多常用的库和组件，例如Tomcat Web服务器、Jackson JSON库等。

在数据层面上，该项目使用了MyBatis框架进行数据库查询。MyBatis是一个流行的ORM框架，可以帮助开发者将Java对象映射到关系型数据库中。该框架提供了许多便利的工具，例如注解、XML配置文件等，使得数据访问层的开发变得更加容易。

为了展示数据，该项目使用了Thymeleaf模板引擎。Thymeleaf是一种基于HTML的模板引擎，可以帮助开发者更方便地在前端展示动态数据。与其他模板引擎不同的是，Thymeleaf可以直接在HTML文件中编写动态数据，因此在应用程序开发过程中非常方便。

为了提高应用程序的性能，该项目使用了Redis进行缓存管理。Redis是一种流行的内存数据库，可以高效地存储和检索数据。使用Redis可以大大提高应用程序的响应速度，尤其是在大量数据的情况下。

最后，为了保障应用程序的安全性，该项目使用了Spring Security进行权限管理。Spring Security是一个功能强大的安全框架，可以帮助开发者构建安全的Web应用程序。该框架提供了许多功能，例如身份验证、授权、角色管理等，可以帮助开发者有效地保护应用程序的安全性。

综上所述，该个人博客项目采用了许多现代技术，旨在为用户提供一个简单而强大的博客平台。无论是在性能、可靠性还是安全性方面，该项目都具备出色的表现，是一款非常值得尝试的Web应用程序。




## SpringBoot开发步骤

### 1.使用Spring Initializr创建项目

![image-20220725122533765](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220725122533765.png)

预先不清楚依赖时可以先勾选上Spring Web

![image-20220725122637251](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220725122637251.png)

### 2.在pom.xml中添加依赖

添加一些必要的依赖项

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.11</version>
</dependency>

<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.2.2</version>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<dependency>
    <groupId>org.thymeleaf.extras</groupId>
    <artifactId>thymeleaf-extras-springsecurity5</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.8</version>
</dependency>

<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.5</version>
</dependency>

<dependency>
    <groupId>com.atlassian.commonmark</groupId>
    <artifactId>commonmark</artifactId>
    <version>0.11.0</version>
</dependency>

<dependency>
    <groupId>com.atlassian.commonmark</groupId>
    <artifactId>commonmark-ext-gfm-tables</artifactId>
    <version>0.11.0</version>
</dependency>

<dependency>
    <groupId>com.vdurmont</groupId>
    <artifactId>emoji-java</artifactId>
    <version>4.0.0</version>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
</dependency>
```

### 3.添加/修改配置文件

配置文件为/src/main/resources/application.properties，可以修改文件为.yml后缀进行项目核心配置，选用.yml的原因是.yml语法比.properties较简单。

```yml
server:
  port: 80
spring:
  profiles:
    # 外置JDBC、Redis和Mail配置文件
    # 在这个全局配置文件中引入其他三个配置文件
    active: jdbc,redis,mail
  # 关闭Thymeleaf页面缓存
  thymeleaf:
    cache: false
  # 配置国际化资源文件
  messages:
    basename: i18n.logo
#Mybatis配置
mybatis:
  configuration:
    # 开启驼峰命名配置映射
    map-underscore-to-camel-case: true
  # 配置MyBatis的XML映射文件路径
  mapper-locations: classpath:mapper/*.xml
  # 配置XML映射文件中指定的实体类别名路径
  type-aliases-package: com.yyl.model.entity
# Pagehelper分页设置
pagehelper:
  helper-dialect: mysql
  reasonable: true
  support-methods-arguments: true
  params: count=countSql
# 浏览器Cookie相关设置
COOKIE:
  #设置Cookie默认时长为30分钟
  VALIDITY: 1800
```

全局配置文件application.yml和其他三个配置文件都放在src/main/resources中（3个配置文件自行创建）

![image-20220725175335818](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220725175335818.png)

### 4.引入前端资源

![image-20220725181932358](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220725181932358.png)

​	static文件夹中存放的是静态资源文件，js、css、img等，templates文件夹中存放的是页面展示所需的Thymeleaf模板页面, back.clien.comm对应后台、前台和公用页面。 i18n存放的是国际化资源文件。 application-*.properties是引入的项目配置文件。

### 5.引入后端基础代码

#### (1)config(配置类)

##### RedisConfig配置类用于对Redis进行自定义配置

#### (2)model

model目录下对应domain和ResponseData两个子目录

##### domain(存放实体类)

domain存放对应于数据库表的映射实体类。

在domain中写好实体类Article,Comment,Statistic的成员变量，Getter和setter和toString方法。

##### ResponseData(消息响应主体)

对应前端请求的响应封装数据

有着与服务器相关的成员变量，getter、setter方法，和多种不同参的重载构造方法。

![image-20220726105542210](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220726105542210.png)

```java
/**
 * 前端请求响应的封装类
 */
public class ArticleResponseData<T> {
    private T payload;          //服务器响应数据
    private boolean success;    //请求是否成功
    private String msg;         //错误信息
    private int code = -1;      //状态码
    private long timestamp;     //服务器响应时间

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ArticleResponseData() {
        this.timestamp = System.currentTimeMillis() / 1000;
    }

    public ArticleResponseData(boolean success) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
    }

    public ArticleResponseData(boolean success, T payload) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
    }

    public ArticleResponseData(boolean success, T payload, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.payload = payload;
        this.code = code;
    }

    public ArticleResponseData(boolean success, String msg) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
    }

    public ArticleResponseData(boolean success, String msg, int code) {
        this.timestamp = System.currentTimeMillis() / 1000;
        this.success = success;
        this.msg = msg;
        this.code = code;
    }

    public static ArticleResponseData ok() {
        return new ArticleResponseData(true);
    }

    public static <T> ArticleResponseData ok(T payload) {
        return new ArticleResponseData(true, payload);
    }

    public static <T> ArticleResponseData ok(int code) {
        return new ArticleResponseData(true,null, code);
    }

    public static <T> ArticleResponseData ok(T payload, int code) {
        return new ArticleResponseData(true, payload, code);
    }

    public static ArticleResponseData fail() {
        return new ArticleResponseData(false);
    }

    public static ArticleResponseData fail(String msg) {
        return new ArticleResponseData(false, msg);
    }

    public static ArticleResponseData fail(int code) {
        return new ArticleResponseData(false, null, code);
    }

    public static ArticleResponseData fail(int code, String msg) {
        return new ArticleResponseData(false, msg, code);
    }
}
```

![image-20220726105641946](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220726105641946.png)

```java
/**
 * 全站服务统计类
 */
public class StaticticsBo {
    private Integer articles;           //文章
    private Integer comment;            //评论

    public Integer getArticles() {
        return articles;
    }

    public void setArticles(Integer articles) {
        this.articles = articles;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "StaticticsBo{" +
                "articles=" + articles +
                ", comment=" + comment +
                '}';
    }
}
```

#### (3)utils(工具类库)

##### Commons工具类用于转换和展示前端页面数据

```java
/**
 * 页面数据展示封装类
 */
@Component
public class Commons {
    /**
     * 网站链接
     * 无参
     * @return String
     */
    public static String site_url() {
        return site_url("/page/1");
    }

    /**
     * 返回网站链接下的全址
     *
     * @param sub 后面追加的地址
     * @return String
     */
    public static String site_url(String sub) {
        return site_option("site_url") + sub;
    }

    /**
     * 网站配置项
     *
     * @param key
     * @return String
     */
    public static String site_option(String key) {
        return site_option(key, "");
    }

    /**
     * 网站配置项
     *
     * @param key
     * @param defalutValue 默认值
     * @return
     */
    public static String site_option(String key, String defalutValue) {
        if(StringUtils.isBlank(key)) { //判断某字符串是否为空或长度为0或由空白符(whitespace) 构成
            return "";
        }
        return defalutValue;
    }

    /**
     * 截取字符串
     *
     * @param str
     * @param len
     * @return
     */
    public static String substr(String str, int len) {
        if (str.length() > len) {
            return str.substring(0,len);
        }
        return str;
    }

    /**
     * 返回日期
     *
     * @return
     */
    public static String dateFormat(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     *  返回文章链接地址
     *
     * @param aid article id
     * @return
     */
    public static String permalink(Integer aid) {
        return site_url("/article/" + aid.toString());
    }

    /**
     * 截取文章摘要
     *
     * @param article 文章
     * @param len   要截取文字的个数
     * @return
     */
    public static String intro(Article article, int len) {
        String value = article.getContent();
        int pos = value.indexOf("<!--more-->");
        if(pos != -1) {
            String html = value.substring(0,pos);
            return MyUtils.htmlToText(MyUtils.mdToHtml(html));
        } else {
            String text = MyUtils.htmlToText(MyUtils.mdToHtml(value));
            if (text.length() > len) {
                return text.substring(0, len)+"......";
            }
            return text;
        }
    }

    /**
     * 对文章内容进行格式转换，将Markdown为Html
     * @param value
     * @return  ok
     */
    public static String article(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = value.replace("<!--more-->", "\r\n");
            return MyUtils.mdToHtml(value);
        }
        return "";
    }

    /**
     * 显示文章缩略图，顺序为：文章第一张图 -> 随机获取
     *
     * @return
     */
    public static String show_thumb(Article article) {
        if (StringUtils.isNotBlank(article.getThumbnail())){
            return article.getThumbnail();
        }
        int cid = article.getId();
        int size = cid % 24;
        size = size == 0 ? 1 : size;
        return "/user/img/rand/" + size + ".png";
    }

    /**
     * 这种格式的字符转换为emoji表情
     *
     * @param value
     * @return
     */
    public static String emoji(String value) {
        return EmojiParser.parseToUnicode(value);
    }
}
```

##### MyUtils工具类用于处理Markdown文件

```java
/**
 * 文章处理工具类
 */
public class MyUtils {

    /**
     *  提取html中的文字
     *
     * @param html
     * @return
     */
    public static String htmlToText(String html) {
        if(StringUtils.isNoneBlank(html)) {
            //replaceAll() 方法使用给定的参数 replacement 替换字符串所有匹配给定的正则表达式的子字符串
            return html.replaceAll("(?s)<[^>]*>(\\s*<[^>]*>)*"," ");
        }
        return "";
    }

    /**
     * Markdown转换为Html
     * @param markdown
     * @return ok
     */
    public static String mdToHtml(String markdown) {
        if(StringUtils.isBlank(markdown)) {
            return "";
        }
        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(extensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .attributeProviderFactory(context -> new LinkAttributeProvider())
                .extensions(extensions).build();
        String content = renderer.render(document);
        content = Commons.emoji(content);
        return content;
    }

    /**
     * 替换HTML脚本
     *
     * @param value
     * @return
     */
    public static String cleanXSS(String value) {
        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }

    private static class LinkAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
        }
    }
}
```

### 6.前台管理模块

开始开发个人博客系统业务功能

主要功能有：文章分页展示、文章详情查看、文章评论管理、用户登录控制。

#### 一、文章分页展示

##### 1.数据访问层实现(dao层 Data Access Object)

与数据库打交道，完成增删改查操作

每一个实体类对应一个Dao接口文件和一个mybatis文件

实现文章分类展示效果需要同时实现文章查询以及文章统计数据查询

先编写文章类Article和统计类Statistic对应的数据访问方法

###### 1)创建Dao层接口文件

创建com.yyl.dao包，在该包下使用MyBatis框架分别创建文章类Article、统计类Statistic对应的Mapper接口文件。

要加上@Mapper注解

```java
ArticleMapper.java
@Mapper
public interface ArticleMapper {
    // 根据id查询文章信息
    @Select("SELECT * FROM t_article WHERE id=#{id}")
    public Article selectArticleWithId(Integer id);

    // 发表文章(插入操作)，同时使用@Options注解获取自动生成的主键id
    @Insert("INSERT INTO t_article (title, created, modified, tags, categories," +
            " allow_comment, thumbnail, content)" +
            " VALUES (#{title},#{created}, #{modified}, #{tags}, #{categories}," +
            " #{allowComment}, #{thumbnail}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public Integer publishArticle(Article article);

    // 文章分页查询
    @Select("SELECT * FROM t_article ORDER BY id DESC")
    public List<Article> selectArticleWithPage();

    //通过id删除文章
    @Delete("DELECT FROM t_article WHERE id=#{id}")
    public void deleteArticleWithId(int id);

    // 站点服务统计，统计文章数量
    @Select("SELECT COUNT(1) FROM t_article")
    public Integer countArticle();

    // 通过id更新文章
//    @Update()
    public Integer updateArticleWithId(Article article);
}
```

```java
StatisticMapper.java
@Mapper
public interface StatisticMapper {
    // 新增文章对应的统计信息
    @Insert("INSERT INTO t_statistic(article_id,hits,comments_num) values (#{id},0,0)")
    public void addStatistic(Article article);

    // 根据文章id查询点击量和评论量相关信息
    @Select("SELECT * FROM t_statistic WHERE article_id=#{articleId}")
    public Statistic selectStatisticWithArticleId(Integer articleId);

    // 通过文章id更新点击量
    @Update("UPDATE t_statistic SET hits=#{hits} " +
            "WHERE article_id=#{articleId}")
    public void updateArticleHitsWithId(Statistic statistic);

    // 通过文章id更新评论量
    @Update("UPDATE t_statistic SET comments_num=#{commentsNum} " +
            "WHERE article_id=#{articleId}")
    public void updateArticleCommentsWithId(Statistic statistic);

    // 根据文章id删除统计数据
    @Delete("DELETE FROM t_statistic WHERE article_id=#{aid}")
    public void deleteStatisticWithId(int aid);

    // 统计文章热度信息
    @Select("SELECT * FROM t_statistic WHERE hits !='0' " +
            "ORDER BY hits DESC, comments_num DESC")
    public List<Statistic> getStatistic();

    // 统计博客文章总访问量
    @Select("SELECT SUM(hits) FROM t_statistic")
    public long getTotalVisit();

    // 统计博客文章总评论量
    @Select("SELECT SUM(comments_num) FROM t_statistic")
    public long getTotalComment();
}
```

###### 2)创建MyBatis对应的XML映射文件

因为updateArticleWithId()方法用注解的方式不方便动态拼接SQL语句，所以这里额外写一个对应的ArticleMapper.xml来使用XML文件配置SQL语句

在resources目录下创建名为mapper的包，在包中创建ArticleMapper.xml文件

namespace属性声明了文章类接口对应的位置

```xml
ArticleMapper.xml
        <?xml version="1.0" encoding="UTF-8" ?>
        <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yyl.mapper.ArticleMapper">
    <update id="updateArticleWithId" parameterType="Article">
        update t_article
        <set>
            <if test="title != null">
                title = #{title},
            </if>
            <if test="created != null">
                created = #{created},
            </if>
            <if test="modified != null">
                modified = #{modified},
            </if>
            <if test="tags != null">
                tags = #{tags},
            </if>
            <if test="categories != null">
                categories = #{categories},
            </if>
            <if test="hits != null">
                hits = #{hits},
            </if>
            <if test="commentsNum != null">
                comments_num = #{commentsNum},
            </if>
            <if test="allowComment != null">
                allow_comment = #{allowComment},
            </if>
            <if test="thumbnail != null">
                thumbnail = #{thumbnail},
            </if>
            <if test="content != null">
                content = #{content},
            </if>
        </set>
        where id = #{id}
    </update>
</mapper>
```

##### 2.业务处理层实现

###### 1)创建Service层接口文件

首先创建com.yyl.service包，在该包下创建用于文章操作的接口类

起名为IArticleService.java 前面的I代表是Interface

```java
/**
 * @Classname IArticleService
 * @Description TODO
 * @Date 2022-7-26 16:00
 * @Created by Yulong
 */
public interface IArticleService {
    // 分页查询文章列表
    public PageInfo<Article> selectArticleWithPage(Integer page, Integer count);

    // 统计热度排名前十的文章信息
    public List<Article> getHeatArticles();

}
```

###### 2）创建Service层接口实现类文件

在com.yyl.service包下创建一个impl包，在该包下创建接口文件对应的实现类，并实现接口中的方法。

```java
@Service   		//目前还不明白该注解的意思
@Transactional
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private StatisticMapper statisticMapper;
    // 分页查询文章列表
    @Override
    public PageInfo<Article> selectArticleWithPage(Integer page, Integer count) {
        PageHelper.startPage(page, count);
        List<Article> articleList = articleMapper.selectArticleWithPage();
        // 封装文章统计数据
        for (int i=0;i < articleList.size(); i++){
            Article article = articleList.get(i);
            Statistic statistic = statisticMapper.selectStatisticWithArticleId(article.getId());
            article.setHits(statistic.getHits());
            article.setCommentsNum(statistic.getCommentsNum());
        }
        PageInfo<Article> pageInfo=new PageInfo<>(articleList);
        return pageInfo;
    }

    // 统计前10的热度文章信息
    @Override
    public List<Article> getHeatArticles() {
        List<Statistic> list = statisticMapper.getStatistic();
        List<Article> articlelist=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Article article = articleMapper.selectArticleWithId(list.get(i).getArticleId());
            article.setHits(list.get(i).getHits());
            article.setCommentsNum(list.get(i).getCommentsNum());
            articlelist.add(article);
            if(i>=9){
                break;
            }
        }
        return articlelist;
    }
}
```

##### 3.请求处理层实现

###### 1)实现Controller控制层处理类

创建com.yyl.web.client包用于客户端文章统一管理

```java
/**
 * @Classname IndexController
 * @Description TODO
 * @Date 2022-7-26 17:00
 * @Created by Yulong
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private IArticleService articleServiceImpl;
    // 博客首页，会自动跳转到文章页
    @GetMapping(value = "/")
    private String index(HttpServletRequest request) {
        return this.index(request, 1, 5);
    }

    // 文章页
    public String index(HttpServletRequest request, @PathVariable("p") int page, @RequestParam(value = "count", defaultValue = "5") int count) {
        PageInfo<Article> articles = articleServiceImpl.selectArticleWithPage(page, count);
        // 获取文章热度统计信息
        List<Article> articleList = articleServiceImpl.getHeatArticles();
        request.setAttribute("articles", articles);
        request.setAttribute("articleList", articleList);
        logger.info("分页获取文章信息: 页码 "+page+",条数 "+count);
        return "client/index";
    }
}
```

###### 2）实现自定义拦截器Interceptor？？

在web目录下新建一个interceptor包，在包中实现HandlerInterceptor接口

```java
/**
 * 自定义的Interceptor拦截器类，用于封装请求后的数据类到request域中，供html页面使用
 * 注意：自定义Mvc的Interceptor拦截器类
 *  1、使用@Configuration注解声明
 *  2、自定义注册类将自定义的Interceptor拦截器类进行注册使用
 */
@Configuration
public class BaseInterceptor implements HandlerInterceptor {
    @Autowired
    private Commons commons;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 用户将封装的Commons工具返回页面
        request.setAttribute("commons",commons);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
```

实现自定义拦截器Interceptor后，还需要通过Spring框架提供的WebMvcConfigurer接口类进行注册.

##### 4.实现前端页面功能

此时运行此SpringBoot程序会使用之前在application.yml中配置的server port ： 8080端口。成功运行时，在浏览器中输入localhost:8080即可访问到主页，但是由于在pom的依赖中加入了Security的相关依赖文件，所以会默认启动Security的安全功能。

![image-20220727173817952](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220727173817952.png)

默认的账号为user，密码会在idea控制台中随机打印出来：

![image-20220727173937824](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220727173937824.png)

输入账号user 和并复制密码即可登录到主页中。

##### 可能遇到的相关问题：

1.启动程序时遇到的主要错误为:`java.nio.charset.MalformedInputException: Input length = 1` 

可能的原因是：文件的编码问题。

解决方案：在设置-编辑器-文件编码中，将三处编码都设置为UTF-8。![image-20220727174708486](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220727174708486.png)

同时勾选上`自动转换成ASCII但显示原生的内容`，这样在yml中中文注释就会正常显示。

2.要注意服务端口要不能被其他程序占用，80端口大概率会被占用，可用8080

3.运行时报错`Relying upon circular references is discouraged and they are prohibited by default. Update your application to remove the dependency cycle between beans. As a last resort, it may be possible to break the cycle automatically by setting spring.main.allow-circular-references to true.`

其意为出现了循环依赖，解决方法可以是在application.yml中设置允许循环依赖即可，

在application.yml中加入spring: main : allow-circular-references: true.

##### 5.将主页index.html、header.html、footer.html稍微修改

![image-20220727190330140](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220727190330140.png)

#### 二、文章详情查看

##### 1.数据访问层实现

###### 1)创建Dao层接口文件

先补充编写评论类Comment对应的接口文件

```java
/**
 * @Classname CommentMapper
 * @Description TODO
 * @Date 2022-7-27 19:35
 * @Created by Yulong
 */
public interface CommentMapper {
    // 分页展示某个文章的评论
    @Select("SELECT * FROM t_comment WHERE article_id=#{aid} ORDER BY id DESC")
    public List<Comment> selectCommentWithPage(Integer aid);

    // 后台查询最新几条评论
    @Select("SELECT * FROM t_comment ORDER BY id DESC")
    public List<Comment> selectNewComment();

    // 发表评论
    @Insert("INSERT INTO t_comment (article_id,created,author,ip,content)"+
            " VALUES (#{articleId}, #{created},#{author},#{ip},#{content})")
    public void pushComment(Comment comment);

    // 站点服务统计，统计评论数量
    @Select("SELECT COUNT(1) FROM t_comment")
    public Integer countComment();

    // 通过文章id删除评论信息
    @Delete("DELETE FROM t_comment WHERE article_id=#{aid}")
    public void deleteCommentWithId(Integer aid);
}
```

##### 2.业务处理层实现

###### 1)创建Service层接口文件

在IArticleService中新增了根据文章id查询文章详情的方法

新创建了两个Service层接口，ICommentService和ISiteService

```java
/**
 * 文章评论业务处理接口
 */
public interface ICommentService {
    // 获取文章下的评论
    public PageInfo<Comment> getComments(Integer aid, int page, int count);
}
```

```java
/**
 * 博客站点统计服务
 */
public interface ISiteService {
    //最新收到的评论
    public List<Comment> recentComments(int count);

    //最新发表的文章
    public List<Article> recentArticles(int count);

    //获取后台统计数据
    public StaticticsBo getStatistics();

    //更新某个文章的统计数据
    public void updateStatistics(Article article);
}
```

###### 2）创建Service层接口实现类文件

1.实现新增的文章查询方法，并嵌入Redis缓存管理

```java
@Override
// 根据id查询单个文章详情，并使用Redis进行缓存管理
public Article selectArticleWithId(Integer id) {
    Article article = null;
    Object o = redisTemplate.opsForValue().get("article_" + id);
    if(o!=null){
        article=(Article)o;
    }else{
        article = articleMapper.selectArticleWithId(id);
        if(article!=null){
            redisTemplate.opsForValue().set("article_" + id,article);
        }
    }
    return article;
}
```

```java
@Service
@Transactional
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentMapper commentMapper;

    // 根据文章id分页查询评论
    @Override
    public PageInfo<Comment> getComments(Integer aid, int page, int count) {
        PageHelper.startPage(page,count);
        List<Comment> commentList = commentMapper.selectCommentWithPage(aid);
        PageInfo<Comment> commentInfo = new PageInfo<>(commentList);
        return commentInfo;
    }
}
```

```java
@Service
@Transactional
public class SiteServiceImpl implements ISiteService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private StatisticMapper statisticMapper;

    public void updateStatistics(Article article) {
        Statistic statistic = statisticMapper.selectStatisticWithArticleId(article.getId());
        statistic.setHits(statistic.getHits()+1);
        statisticMapper.updateArticleHitsWithId(statistic);
    }

    @Override
    public List<Comment> recentComments(int limit) {
        PageHelper.startPage(1, limit>10 || limit<1 ? 10:limit);
        List<Comment> byPage = commentMapper.selectNewComment();
        return byPage;
    }

    @Override
    public List<Article> recentArticles(int limit) {
        PageHelper.startPage(1, limit>10 || limit<1 ? 10:limit);
        List<Article> list = articleMapper.selectArticleWithPage();
        // 封装文章统计数据
        for (int i = 0; i < list.size(); i++) {
            Article article = list.get(i);
            Statistic statistic = statisticMapper.selectStatisticWithArticleId(article.getId());
            article.setHits(statistic.getHits());
            article.setCommentsNum(statistic.getCommentsNum());
        }
        return list;
    }

    @Override
    public StaticticsBo getStatistics() {
        StaticticsBo staticticsBo = new StaticticsBo();
        Integer articles = articleMapper.countArticle();
        Integer comments = commentMapper.countComment();
        staticticsBo.setArticles(articles);
        staticticsBo.setComments(comments);
        return staticticsBo;
    }

}
```

##### 3.请求处理层实现

在IndexController中添加查询文章详情的方法

```java
/**
 * @Date 2022-7-27 21:00
 */
//文章详情查询
@GetMapping(value = "/article/{id}")
public String getArticleById(@PathVariable("id") Integer id, HttpServletRequest request){
    Article article = articleServiceImpl.selectArticleWithId(id);
    if(article!=null){
        // 查询封装评论相关数据
        getArticleComments(request, article);
        // 更新文章点击量
        siteServiceImpl.updateStatistics(article);
        request.setAttribute("article",article);
        return "client/articleDetails";
    }else {
        logger.warn("查询文章详情结果为空，查询文章id: "+id);
        // 未找到对应文章页面，跳转到提示页
        return "comm/error_404";
    }
}

// 查询文章的评论信息，并补充到文章详情里面
private void getArticleComments(HttpServletRequest request, Article article) {
    if (article.getAllowComment()) {
        // cp表示评论页码，commentPage
        String cp = request.getParameter("cp");
        cp = StringUtils.isBlank(cp) ? "1" : cp;
        request.setAttribute("cp", cp);
        PageInfo<Comment> comments = commentServiceImpl.getComments(article.getId(),Integer.parseInt(cp),3);
        request.setAttribute("cp", cp);
        request.setAttribute("comments", comments);
    }
}
```

##### 4.运行程序查看文章详情

前期文章详情页面articleDetails.html已经添加，已经可以直接访问localhost:8080进入主页浏览文章详情了。还可以开启Redis缓存服务，将打开过的页面进行缓存。

下载解压Redis程序，运行redis-server.exe打开数据库服务。

![image-20220727213716922](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220727213716922.png)

![image-20220727213723786](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220727213723786.png)

下载安装Redis Desktop Manager软件

连接到本机的Redis

![image-20220727213859140](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220727213859140.png)

Host默认为127.0.0.1 port默认为6379

连接成功后如下图

![image-20220727213934626](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220727213934626.png)

且需保证application-redis.properties配置正确且在application.yml中active

![image-20220727214050272](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220727214050272.png)

![image-20220727214100755](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220727214100755.png)

还需保证前期所写的RedisConfig类的自定义RedisTemplate正确

这时运行程序，点击首页中的文章标题，在正确加载文章详情页面后，刷新Redis Manager的0号数据库，这是就会将文章内容缓存到Redis数据库中。

![image-20220727214346193](https://github.com/YuLong4/blog_system/blob/main/README.assets/image-20220727214346193.png)

#### 三、文章评论管理

已经写好了数据访问层CommentMapper文件，直接实现业务数据层

##### 1.业务处理层实现

###### 1)编写Service层接口方法

在ICommentService中添加方法

```java
// 用户发表评论
public void pushComment(Comment comment);
```

###### 2）编写Service层接口实现类方法

在CommentServiceImpl中实现方法

```java
@Autowired
public StatisticMapper statisticMapper;
//用户发表评论
@Override
public void pushComment(Comment comment){
    commentMapper.pushComment(comment);
    //更新文章评论数据量
    Statistic statistic = statisticMapper.selectStatisticWithArticleId(comment.getArticleId());
    statistic.setCommentsNum(statistic.getCommentsNum()+1);
    statisticMapper.updateArticleHitsWithId(statistic);
}
```

##### 2.请求处理层实现

在com.yyl.web.client中新建一个用户评论管理的控制类CommentController,并编写相应的请求控制方法。

```java
/**
 * @Classname CommentController
 * @Description TODO
 * @Date 2022-07-27 22:00
 * @Created by Yulong
 */
@Controller
@RequestMapping("/comments")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    @Autowired
    private ICommentService commentServcieImpl;

    // 发表评论操作
    @PostMapping(value = "/publish")
    @ResponseBody
    public ArticleResponseData publishComment(HttpServletRequest request, @RequestParam Integer aid, @RequestParam String text) {
        // 去除js脚本
        text = MyUtils.cleanXSS(text);
        text = EmojiParser.parseToAliases(text);
        // 获取当前登录用户
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 封装评论信息
        Comment comments = new Comment();
        comments.setArticleId(aid);
        comments.setIp(request.getRemoteAddr());
        comments.setCreated(new Date());
        comments.setAuthor(user.getUsername());
        comments.setContent(text);
        try {
            commentServcieImpl.pushComment(comments);
            logger.info("发布评论成功，对应文章id: "+aid);
            return ArticleResponseData.ok();
        } catch (Exception e) {
            logger.error("发布评论失败，对应文章id: "+aid +";错误描述: "+e.getMessage());
            return ArticleResponseData.fail();
        }
    }
}
```

### 7.后台管理模块

后台管理模块包括数据展示、文章发布、文章修改、文章删除、用户登录控制

创建com.yyl.web.admin包

#### 一、数据展示

新建一个后台管理的控制类AdminController,用于处理前端页面请求，对应的是index.html文件。

```java
/**
 * 后台管理模块
 *
 * 用于处理前端页面请求
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger longger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ISiteService siteServiceImpl;
    //管理中心起始页
    @GetMapping(value = {"", "/index"})
    public String index(HttpServletRequest request) {
        //获取最新的5篇博客、评论以及统计数据
        List<Article> articles = siteServiceImpl.recentArticles(5);
        List<Comment> comments = siteServiceImpl.recentComments(5);
        StaticticsBo staticticsBo = siteServiceImpl.getStatistics();
        //向Request域中存储数据
        request.setAttribute("comments", comments);
        request.setAttribute("articles", articles);
        request.setAttribute("statistics", staticticsBo);
        return "back/index";
    }
}
```

#### 二、文章发布

单击左侧的“发布文章”可以跳转到文章编辑界面，在文章编辑页面完成文章编辑后可以跳转到“文章管理”页面，分页展示出所有文章信息。

##### 1.业务处理层实现

###### 1)编写Service层接口方法

在service中的IArticleService中新增一个发布文章的方法

```java
// 发布文章
public void publish(Article article);
```

2)编写Service层接口实现类方法

在文章业务层接口实现类ArticleServiceImpl中实现publish()方法。

```java
//发布文章
@Override
public void publish(Article article) {
    // 去除表情
    article.setContent(EmojiParser.parseToAliases(article.getContent()));
    article.setCreated(new Date());
    article.setHits(0);
    article.setCommentsNum(0);
    //插入文章、同时插入文章统计数据   插入到数据库中
    articleMapper.publishArticle(article);
    statisticMapper.addStatistic(article);
}
```

##### 2.请求处理层实现

在AdminController中添加页面跳转请求的方法。

```java
@Autowired
private IArticleService articleServiceImpl;
//向文章发表页面跳转
@GetMapping(value = "/article/toEditPage")
public String newArticle() {
    return "back/article_edit";
}

//发表文章
@PostMapping(value = "/article/publish")
@ResponseBody
public ArticleResponseData publishArticle(Article article) {
    if(StringUtils.isBlank(article.getCategories())) {
        article.setCategories("默认分类");
    }
    try {
        articleServiceImpl.publish(article);
        longger.info("文章发布成功");
        return ArticleResponseData.ok();
    } catch (Exception e) {
        longger.error("文章发布失败，错误信息:" + e.getMessage());
        return ArticleResponseData.fail();
    }
}

//跳转到后台文章列表页面
@GetMapping(value = "/article")
public String index(@RequestParam(value = "page", defaultValue = "1") int page,
                    @RequestParam(value = "count", defaultValue = "10")int count,
                    HttpServletRequest request) {
    PageInfo<Article> pageInfo = articleServiceImpl.selectArticleWithPage(page, count);
    request.setAttribute("articles", pageInfo);
    return "back/article_list";
}
```

在编辑文章界面，点击保存文章按钮时，会触发执行subArticle()方法，该方法在article.js文件中。

```js
/**
 * 保存文章
 * @param status
 */
function subArticle(status) {
    var title = $('#articleForm input[name=title]').val();
    var content =  mditor.value;
    if (title == '') {
        tale.alertWarn('请输入文章标题');
        return;
    }
    if (title .length>25) {
        tale.alertWarn('文章标题不能超过25个字符！');
        return;
    }
    if (content == '') {
        tale.alertWarn('请输入文章内容');
        return;
    }
    $('#content-editor').val(content);
    $("#articleForm #status").val(status);
    $("#articleForm #categories").val($('#multiple-sel').val());
    var params = $("#articleForm").serialize();
    var url = $('#articleForm #id').val() != '' ? '/admin/article/modify' : '/admin/article/publish';
    tale.post({
        url:url,
        data:params,
        success: function (result) {
            if (result && result.success) {
                tale.alertOk({
                    text:'文章保存成功',
                    then: function () {
                        setTimeout(function () {
                            window.location.href = '/admin/article';
                        }, 500);
                    }
                });
            } else {
                tale.alertError(result.msg || '保存文章失败！');
            }
        }
    });
}
```

#### 三、文章修改

##### 1.业务处理层

###### 1)编写Service层接口方法

在IArticleService中添加修改文章方法

```java
// 修改文章
public void updateArticleWithId(Article article);
```

###### 2)编写Service层接口实现类方法

在ArticleServiceImpl中实现

```java
//修改文章
@Override
public void updateArticleWithId(Article article) {
    article.setModified(new Date());
    articleMapper.updateArticleWithId(article);
    redisTemplate.delete("article_" + article.getId());
}
```

对文章进行修改后，又调用redisTemplate删除指定id的文章缓存信息。

##### 2.请求处理层

在AdminController中定义两个方法，分别用于处理跳转到文章修改页面和保存修改文章的操作。

```java
// 向文章修改页面跳转
@GetMapping(value = "/article/{id}")
public String editArticle(@PathVariable("id") String id, HttpServletRequest request) {
    Article article = articleServiceImpl.selectArticleWithId(Integer.parseInt(id));
    request.setAttribute("contents", article);
    request.setAttribute("categories", article.getCategories());
    return "back/article_edit";
}

// 修改处理文章
@PostMapping(value = "/article/modify")
@ResponseBody
public ArticleResponseData modifyArticle(Article article) {
    try {
        articleServiceImpl.updateArticleWithId(article);
        logger.info("文章更新成功");
        return ArticleResponseData.ok();
    } catch (Exception e) {
        logger.info("文章更新失败，错误信息:" + e.getMessage());
        return ArticleResponseData.fail();
    }
}
```

#### 四、文章删除

##### 1.业务处理层

```java
// 根据主键删除文章
public void deleteArticleWithId(int id);
```

```java
@Autowired
private CommentMapper commentMapper;

// 删除文章
@Override
public void deleteArticleWithId(int id) {
    // 删除文章的同时，删除对应的缓存
    articleMapper.deleteArticleWithId(id);
    redisTemplate.delete("article_" + id);
    // 同时删除对应文章的统计数据
    statisticMapper.deleteStatisticWithId(id);
    // 同时删除对应文章的评论数据
    commentMapper.deleteCommentWithId(id);
}
```

##### 2.请求处理层

在AdminController中添加处理文章删除的方法。

```java
// 文章删除
@PostMapping(value = "/article/delete")
@ResponseBody
public ArticleResponseData delete(@RequestParam int id) {
    try {
        articleServiceImpl.deleteArticleWithId(id);
        logger.info("文章删除成功");
        return ArticleResponseData.ok();
    } catch (Exception e) {
        logger.error("文章删除失败，错误信息"+e.getMessage());
        return ArticleResponseData.fail();
    }
}
```

### 8.用户登录控制

#### 1.请求处理层实现

在com.yyl.web.client包下创建一个用户登录管理的控制类LoginController

```java
@Controller
public class LoginController {
    //向登录页面跳转，同时封装原始页面地址
    @GetMapping(value = "/login")
    public String login(HttpServletRequest request, Map map) {
        //分别获取请求头和参数url中的原始非拦截的访问路径
        String referer = request.getHeader("Referer");
        String url = request.getParameter("url");
        // 如果参数url中已经封装了原始页面路径，直接返回该路径
        if (url!=null && !url.equals("")) {
            map.put("url",url);
            //如果请求头本身包含登录，将重定向url为空，让后台通过用户角色进行选择跳转
        }else if (referer!=null && referer.contains("/login")) {
            map.put("url","");
            //否则的话，就记住请求头中的原始访问路径
        }else {
            map.put("url", referer);
        }
        return "comm/login";
    }
    //对Security拦截的无权限访问异常处理路径映射
    @GetMapping(value = "/errorPage/{page}/{code}")
    public String AccessExceptionHandler(@PathVariable("page") String page,
                                         @PathVariable("code") String code) {
        return page+"/+code";
    }
}
```

2.编写 Security认证授权配置类

在com.yyl.web.config包下创建一个用于整合Security进行安全控制的配置类SecurityConfig,并重写自定义用户认证和授权方法。

```java
@EnableWebSecurity      //开启MVC security安全支持
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Value("${COOKIE.VALIDITY}")
    private Integer COOKIE_VALIDITY;
    /**
     * 重写configure(HttpSecurity http)方法，进行用户授权管理
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1、自定义用户访问控制
        http.authorizeRequests()
                .antMatchers("/","/page/**","/article/**","/login").permitAll()
                .antMatchers("/back/**","/assets/**","/user/**","/article_img/**").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                .anyRequest().authenticated();
        // 2、自定义用户登录控制
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        String url = httpServletRequest.getParameter("url");
                        // 获取被拦截的原始访问路径
                        RequestCache requestCache = new HttpSessionRequestCache();
                        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest,httpServletResponse);
                        if(savedRequest !=null){
                            // 如果存在原始拦截路径，登录成功后重定向到原始访问路径
                            httpServletResponse.sendRedirect(savedRequest.getRedirectUrl());
                        } else if(url != null && !url.equals("")){
                            // 跳转到之前所在页面
                            URL fullURL = new URL(url);
                            httpServletResponse.sendRedirect(fullURL.getPath());
                        }else {
                            // 直接登录的用户，根据用户角色分别重定向到后台首页和前台首页
                            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                            boolean isAdmin = authorities.contains(new SimpleGrantedAuthority("ROLE_admin"));
                            if(isAdmin){
                                httpServletResponse.sendRedirect("/admin");
                            }else {
                                httpServletResponse.sendRedirect("/");
                            }
                        }
                    }
                })
                // 用户登录失败处理
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        // 登录失败后，取出原始页面url并追加在重定向路径上
                        String url = httpServletRequest.getParameter("url");
                        httpServletResponse.sendRedirect("/login?error&url="+url);
                    }
                });
        // 3、设置用户登录后cookie有效期，默认值
        http.rememberMe().alwaysRemember(true).tokenValiditySeconds(COOKIE_VALIDITY);
        // 4、自定义用户退出控制
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
        // 5、针对访问无权限页面出现的403页面进行定制处理
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                // 如果是权限访问异常，则进行拦截到指定错误页面
                RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/errorPage/comm/error_403");
                dispatcher.forward(httpServletRequest, httpServletResponse);
            }
        });
    }

    /**
     * 重写configure(AuthenticationManagerBuilder auth)方法，进行自定义用户认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //  密码需要设置编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //  使用JDBC进行身份认证
        String userSQL ="select username,password,valid from t_user where username = ?";
        String authoritySQL ="select u.username,a.authority from t_user u,t_authority a," +
                "t_user_authority ua where ua.user_id=u.id " +
                "and ua.authority_id=a.id and u.username =?";
        auth.jdbcAuthentication().passwordEncoder(encoder)
                .dataSource(dataSource)
                .usersByUsernameQuery(userSQL)
                .authoritiesByUsernameQuery(authoritySQL);
    }
}
```
