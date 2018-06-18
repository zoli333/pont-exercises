package eu.pontsystems.configuration;


import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("eu.pontsystems.repository")
@ComponentScan(basePackages = {"eu.pontsystems"})
public class AppConfiguration {
	
	@Bean
	@Qualifier("entityManagerFactory")
	LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean lfb = new LocalContainerEntityManagerFactoryBean();
		lfb.setDataSource(getDataSource());
		lfb.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		lfb.setPackagesToScan("eu.pontsystems.entity");
		lfb.setJpaProperties(hibernateProperties());
		System.out.println("DONE");
		return lfb;
	}
    
	
    @Bean
    @Qualifier("dataSource")
    public DataSource getDataSource() {
      DriverManagerDataSource dataSource=new DriverManagerDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/rideit?useSSL=false");
      dataSource.setUsername("root");
      dataSource.setPassword("jelszo");
      return dataSource;
    }
    
    
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        System.out.println("DONE HIBERNATE");
        return properties;        
    }
    
    @Bean
  	public JpaTransactionManager transactionManager() {
  		JpaTransactionManager transactionManager = new JpaTransactionManager();
  		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
  		System.out.println("DONE TRANS");
  		return transactionManager;
  	}
    
   
	
}
