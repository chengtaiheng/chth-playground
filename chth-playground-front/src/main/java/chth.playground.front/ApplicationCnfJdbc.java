package chth.playground.front;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author: 程泰恒
 * @date: 2019/5/20 17:35
 */
@Slf4j
@Configuration
@EnableTransactionManagement
@MapperScan(annotationClass = Mapper.class, basePackages = Constants.BASE_PACKAGE)
@ImportResource("classpath:/spring/datasource.xml")
public class ApplicationCnfJdbc {

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        log.info("--------");
        log.info("数据库类型 = {}", dataSource.getClass());
        log.info("-------");
        return new DataSourceTransactionManager(dataSource);
    }
}


