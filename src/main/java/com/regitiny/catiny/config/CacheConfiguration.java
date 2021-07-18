package com.regitiny.catiny.config;

import com.regitiny.catiny.GeneratedByJHipster;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.redisson.Redisson;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.config.JHipsterProperties;
import tech.jhipster.config.cache.PrefixedKeyGenerator;

@Configuration
@EnableCaching
@GeneratedByJHipster
public class CacheConfiguration {

  private GitProperties gitProperties;
  private BuildProperties buildProperties;

  @Bean
  public javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration(JHipsterProperties jHipsterProperties) {
    MutableConfiguration<Object, Object> jcacheConfig = new MutableConfiguration<>();

    URI redisUri = URI.create(jHipsterProperties.getCache().getRedis().getServer()[0]);

    Config config = new Config();
    if (jHipsterProperties.getCache().getRedis().isCluster()) {
      ClusterServersConfig clusterServersConfig = config
        .useClusterServers()
        .setMasterConnectionPoolSize(jHipsterProperties.getCache().getRedis().getConnectionPoolSize())
        .setMasterConnectionMinimumIdleSize(jHipsterProperties.getCache().getRedis().getConnectionMinimumIdleSize())
        .setSubscriptionConnectionPoolSize(jHipsterProperties.getCache().getRedis().getSubscriptionConnectionPoolSize())
        .addNodeAddress(jHipsterProperties.getCache().getRedis().getServer());

      if (redisUri.getUserInfo() != null) {
        clusterServersConfig.setPassword(redisUri.getUserInfo().substring(redisUri.getUserInfo().indexOf(':') + 1));
      }
    } else {
      SingleServerConfig singleServerConfig = config
        .useSingleServer()
        .setConnectionPoolSize(jHipsterProperties.getCache().getRedis().getConnectionPoolSize())
        .setConnectionMinimumIdleSize(jHipsterProperties.getCache().getRedis().getConnectionMinimumIdleSize())
        .setSubscriptionConnectionPoolSize(jHipsterProperties.getCache().getRedis().getSubscriptionConnectionPoolSize())
        .setAddress(jHipsterProperties.getCache().getRedis().getServer()[0]);

      if (redisUri.getUserInfo() != null) {
        singleServerConfig.setPassword(redisUri.getUserInfo().substring(redisUri.getUserInfo().indexOf(':') + 1));
      }
    }
    jcacheConfig.setStatisticsEnabled(true);
    jcacheConfig.setExpiryPolicyFactory(
      CreatedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, jHipsterProperties.getCache().getRedis().getExpiration()))
    );
    return RedissonConfiguration.fromInstance(Redisson.create(config), jcacheConfig);
  }

  @Bean
  public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cm) {
    return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cm);
  }

  @Bean
  public JCacheManagerCustomizer cacheManagerCustomizer(javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration) {
    return cm -> {
      createCache(cm, com.regitiny.catiny.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.User.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Authority.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.User.class.getName() + ".authorities", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MessageGroup.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MessageContent.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MessageGroup.class.getName() + ".messageContents", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MessageGroup.class.getName() + ".masterUsers", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.HanhChinhVN.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myPages", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myFiles", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myNotifications", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myFriends", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myFollowUsers", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myFollowGroups", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myFollowPages", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myNewsFeeds", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myTodoLists", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myPosts", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myGroupPosts", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".messageGroups", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".topicInterests", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myLikes", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myComments", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.UserProfile.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.AccountStatus.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.AccountStatus.class.getName() + ".deviceStatuses", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.DeviceStatus.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Friend.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.FollowUser.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.FollowGroup.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.FollowPage.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.FileInfo.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.PagePost.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.PagePost.class.getName() + ".myPostInPages", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.PagePost.class.getName() + ".topicInterests", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.PageProfile.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.GroupPost.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.GroupPost.class.getName() + ".myPostInGroups", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.GroupPost.class.getName() + ".topicInterests", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.GroupPost.class.getName() + ".userInGroups", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Post.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Post.class.getName() + ".postComments", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Post.class.getName() + ".postLikes", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Post.class.getName() + ".postShareChildren", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Post.class.getName() + ".newsFeeds", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Post.class.getName() + ".topicInterests", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.PostComment.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.PostComment.class.getName() + ".commentReplies", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.PostLike.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.GroupProfile.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.NewsFeed.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.RankUser.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.RankGroup.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.RankGroup.class.getName() + ".rankUsers", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Notification.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Album.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Album.class.getName() + ".images", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Video.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Video.class.getName() + ".videoProcesseds", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Image.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Image.class.getName() + ".imageProcesseds", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Image.class.getName() + ".albums", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.VideoStream.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.VideoStream.class.getName() + ".videoLiveStreamBuffers", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.VideoLiveStreamBuffer.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.TopicInterest.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.TopicInterest.class.getName() + ".posts", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.TopicInterest.class.getName() + ".pagePosts", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.TopicInterest.class.getName() + ".groupPosts", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.TopicInterest.class.getName() + ".masterUsers", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.TodoList.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Event.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Event.class.getName() + ".otherImages", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Event.class.getName() + ".otherVideos", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.BaseInfo.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.PagePost.class.getName() + ".followPages", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.GroupPost.class.getName() + ".followGroups", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myBaseInfoCreateds", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".myBaseInfoModifieds", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".ownerOfs", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.MasterUser.class.getName() + ".permissions", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Post.class.getName() + ".comments", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Post.class.getName() + ".likes", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.PostComment.class.getName() + ".likes", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.BaseInfo.class.getName() + ".permissions", jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.Permission.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.ClassInfo.class.getName(), jcacheConfiguration);
      createCache(cm, com.regitiny.catiny.domain.ClassInfo.class.getName() + ".baseInfos", jcacheConfiguration);
      // jhipster-needle-redis-add-entry
    };
  }

  private void createCache(
    javax.cache.CacheManager cm,
    String cacheName,
    javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration
  ) {
    javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
    if (cache != null) {
      cache.clear();
    } else {
      cm.createCache(cacheName, jcacheConfiguration);
    }
  }

  @Autowired(required = false)
  public void setGitProperties(GitProperties gitProperties) {
    this.gitProperties = gitProperties;
  }

  @Autowired(required = false)
  public void setBuildProperties(BuildProperties buildProperties) {
    this.buildProperties = buildProperties;
  }

  @Bean
  public KeyGenerator keyGenerator() {
    return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
  }
}
