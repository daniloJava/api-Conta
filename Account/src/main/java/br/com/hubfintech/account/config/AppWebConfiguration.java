package br.com.hubfintech.account.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.hubfintech.account.controllers.ContaController;
import br.com.hubfintech.account.controllers.PessoaController;
import br.com.hubfintech.account.controllers.TransferenciaController;
import br.com.hubfintech.account.daos.ContaDao;
import br.com.hubfintech.account.daos.PessoaFisicaDao;
import br.com.hubfintech.account.daos.PessoaJuridicaDao;
import br.com.hubfintech.account.daos.TransferenciaDao;

@EnableWebMvc
@ComponentScan(basePackageClasses={
		ContaController.class, ContaDao.class, 
		PessoaController.class, PessoaFisicaDao.class, PessoaJuridicaDao.class,
		TransferenciaController.class, TransferenciaDao.class})
public class AppWebConfiguration extends WebMvcConfigurerAdapter{
	
	/**Configuração para que o spring reconheça 
	 * configurações.
	 * 
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver(){
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");//Mapeando as views
		resolver.setSuffix(".jsp");//Mapeando as paginas como jsp
//		resolver.setExposedContextBeanNames("carrinhoCompras");//deixa disponivel apenas um beans especifico  para JSP 
		
		return resolver; 
	}
	
	/**Configuração de arquivo de mensagens.
	 * para validações dos campos de formulários
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("/WEB-INF/message");
		source.setDefaultEncoding("UTF-8");
		source.setCacheSeconds(1);
		
		return source;
	}
	
	/**Metodo para conversão de dados 
	 * que o spring automatico
	 * 
	 */
	@Bean
	public FormattingConversionService mvcConversionService(){
		
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);
		
		return conversionService;
		
	}
	@Bean
	public MultipartResolver multipartResolver(){
		return new StandardServletMultipartResolver();
		
	}
	
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
}
