package com.laioffer.onlineorder;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebMvc
//创建SessionHibernate所需要的变量
public class ApplicationConfig {
    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        String PACKAGE_NAME = "com.laioffer.onlineorder.entity";//根据本地名称
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PACKAGE_NAME);//要扫描的路径在哪
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;//创建sessionFactory的object
    }

    @Bean(name = "dataSource")//@Bean的作用是告诉spring在启动时执行代码并创建object， 该object存在于spring的container中
    //一个MySQL的instance
    public DataSource dataSource() {
        String RDS_ENDPOINT = "database-1.cdcbbdljbe81.us-west-1.rds.amazonaws.com";//一些config
        String USERNAME = "admin";
        String PASSWORD = "12345678";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + RDS_ENDPOINT + ":3306/onlineOrder?createDatabaseIfNotExist=true&serverTimezone=UTC");
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;//通过@bean创建dataSource object
    }
    @Bean(name = "passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();//用来做加密用的object
    }


    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");//class里面的内容有变化才会更新到数据库里
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        return hibernateProperties;
    }

}
