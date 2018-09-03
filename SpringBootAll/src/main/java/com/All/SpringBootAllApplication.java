package com.All;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

/*--------------------------------Redis Cache---------------------------------------------*/
/**
 * 一、搭建基本环境
 * 1、导入数据库文件 创建出department和employee表
 * 2、创建javaBean封装数据
 * 3、整合MyBatis操作数据库
 * 		1.配置数据源信息
 * 		2.使用注解版的MyBatis；
 * 			1）、@MapperScan指定需要扫描的mapper接口所在的包
 * 二、快速体验缓存
 * 		步骤：
 * 			1、开启基于注解的缓存 @EnableCaching
 * 			2、标注缓存注解即可
 * 				@Cacheable
 * 				@CacheEvict
 * 				@CachePut
 * 默认使用的是ConcurrentMapCacheManager==ConcurrentMapCache；将数据保存在	ConcurrentMap<Object, Object>中
 * 开发中使用缓存中间件；redis、memcached、ehcache；
 * 三、整合redis作为缓存
 * Redis 是一个开源（BSD许可）的，内存中的数据结构存储系统，它可以用作数据库、缓存和消息中间件。
 * 	1、安装redis：使用docker；
 * 	2、引入redis的starter
 * 	3、配置redis
 * 	4、测试缓存
 * 		原理：CacheManager===Cache 缓存组件来实际给缓存中存取数据
 *		1）、引入redis的starter，容器中保存的是 RedisCacheManager；
 *		2）、RedisCacheManager 帮我们创建 RedisCache 来作为缓存组件；RedisCache通过操作redis缓存数据的
 *		3）、默认保存数据 k-v 都是Object；利用序列化保存；如何保存为json:
 *   			1、引入了redis的starter，cacheManager变为 RedisCacheManager；
 *   			2、默认创建的 RedisCacheManager 操作redis的时候使用的是 RedisTemplate<Object, Object>
 *   			3、RedisTemplate<Object, Object> 是 默认使用jdk的序列化机制
 *      4）、自定义CacheManager；
 *
 */

/*--------------------------------RabbitMQ---------------------------------------------*/
/**
 * 自动配置
 *  1、RabbitAutoConfiguration
 *  2、有自动配置了连接工厂ConnectionFactory；
 *  3、RabbitProperties 封装了 RabbitMQ的配置
 *  4、 RabbitTemplate ：给RabbitMQ发送和接受消息；
 *  5、 AmqpAdmin ： RabbitMQ系统管理功能组件; 这样就不用去网页面板去创建了
 *  	AmqpAdmin：创建和删除 Queue，Exchange，Binding
 *  6、@EnableRabbit +  @RabbitListener 监听消息队列的内容
 *
 */

/*--------------------------------Elasticsearch---------------------------------------------*/
/**
 * SpringBoot默认支持两种技术来和ES交互；
 * 1、Jest（默认不生效）
 * 	需要导入jest的工具包（io.searchbox.client.JestClient）
 * 2、SpringData ElasticSearch【ES版本有可能不合适, 会出错】
 * 		版本适配说明：https://github.com/spring-projects/spring-data-elasticsearch
 *		如果版本不适配：2.4.6
 *			1）、升级SpringBoot版本
 *			2）、安装对应版本的ES
 *
 * 		1）、Client 节点信息clusterNodes；clusterName
 * 		2）、ElasticsearchTemplate 操作es
 *		3）、编写一个 ElasticsearchRepository 的子接口来操作ES；
 *	两种用法：https://github.com/spring-projects/spring-data-elasticsearch
 *
 */



//@MapperScan指定com.All.mapper包下的东西作为mapper的主包，
// 这样就不用在mapper包下每个interface上面都添加@Mapper注解了
@MapperScan(value = "com.All.mybatis.mapper")
@SpringBootApplication
@EnableCaching  //开启基于注解的缓存
@EnableRabbit   //开启基于注解的RabbitMQ
@ServletComponentScan  //
public class SpringBootAllApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAllApplication.class, args);
	}



	/**
	 * 文件上传配置
	 * @return
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//设置单个文件最大
		factory.setMaxFileSize("10240KB"); //KB,MB
		// 设置总上传数据总大小
		factory.setMaxRequestSize("102400KB");
		return factory.createMultipartConfig();
	}
}
