package net.atos.testeatos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * Implements configuration interfaces for SpringMVC and Spring Boot. Launches
 * the main application and sets the configuration for non-default Beans.
 *  
 * @author Tiago de Morais França
 * @version 1.0
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * SpringBootApplication
 */
@SpringBootApplication
public class TesteAtosApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(TesteAtosApplication.class, args);
  }

  /**
   * Returns a {@link org.springframework.web.servlet.i18n.CookieLocaleResolver 
   * CookieLocaleResolver} which will be used as the application's {@link org
   * .springframework.web.servlet.LocaleResolver LocaleResolver}.
   * 
   * @return CookieLocaleResolver Bean to be used by the application.
   */
  @Bean
  public LocaleResolver localeResolver() {
    return new CookieLocaleResolver();
  }

  /**
   * Returns a {@link org.springframework.web.servlet.i18n.LocaleChangeInterceptor
   * LocaleChangeInterceptor}. The user's locale will be
   * changed when the "lang" parameter is added to a request's url.
   * 
   * @return A LocaleChangeInterceptor bean.
   */
  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }	

  /**
   * {@inheritDoc}
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
	  registry.addInterceptor(localeChangeInterceptor());
  }
  
  /**
   * Returns a {@link org.springframework.context.MessageSource Message Source}
   * that indicates the application's messages classpath and default encoding.
   * @return The application's MessageSource.
   */
  @Bean
  public MessageSource messageSource() {
    final ReloadableResourceBundleMessageSource messageSource = 
        new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:/messages/messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }
    
}

