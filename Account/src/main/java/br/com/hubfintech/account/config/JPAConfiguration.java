package br.com.hubfintech.account.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JPAConfiguration {
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManangerFactory(){
		
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
			
		factoryBean.setJpaVendorAdapter(adapter);
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		dataSource.setUrl("jdbc:mysql://localhost/accounts");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

		factoryBean.setDataSource(dataSource);
		
		Properties props = new Properties();
        props.setProperty("hibernate.dialect" , "org.hibernate.dialect.MySQL5InnoDBDialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "update");
		
		factoryBean.setJpaProperties(props);

		factoryBean.setPackagesToScan("br.com.hubfintech.account.models");

        return factoryBean;
		
	}
	
	@Bean
	public JpaTransactionManager transactionMananger(EntityManagerFactory emf){
		return new JpaTransactionManager(emf);
	}

}
