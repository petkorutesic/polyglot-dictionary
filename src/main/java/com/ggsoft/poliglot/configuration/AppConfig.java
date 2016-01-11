package com.ggsoft.poliglot.configuration;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.ggsoft.poliglot.converter.FormLanguageToLanguageConverter;
import com.ggsoft.poliglot.converter.FormLinkTypeToLinkTypeConverter;
import com.ggsoft.poliglot.converter.FormMeaningToMeaningConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.ggsoft.poliglot")
public class AppConfig extends WebMvcConfigurerAdapter {
	
	public static final Logger logger = Logger.getLogger(WebMvcConfigurerAdapter.class);

	@Autowired
	FormLanguageToLanguageConverter formLanguageToLanguageConverter;
	@Autowired
	FormLinkTypeToLinkTypeConverter formLinkTypeToLinkTypeConverter;
	@Autowired
	FormMeaningToMeaningConverter formMeaningToMeaningConverter;

	/**
	 * Configure ViewResolvers to deliver preferred views.
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}

	/**
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	/**
	 * Configure Converter to be used. In our example, we need a converter to
	 * convert string values[Roles] to Words in newUser.jsp
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(formLanguageToLanguageConverter);
		registry.addConverter(formLinkTypeToLinkTypeConverter);
	//	registry.addConverter(formMeaningToMeaningConverter);
	}

	/**
	 * Configure MessageSource to lookup any validation/error message in
	 * internationalized property files
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	
//	@Bean
//	public FilterRegistrationBean filterRegistrationBean() {
//	    CharacterEncodingFilter filter = new CharacterEncodingFilter();
//	    filter.setEncoding("UTF-8");
//	    filter.setForceEncoding(true);
//
//	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//	    registrationBean.setFilter(filter);
//	    registrationBean.addUrlPatterns("/*");
//	    return registrationBean;
//	}
	
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

	    // http
	    HttpMessageConverter<?> converter = new StringHttpMessageConverter();
	    converters.add(converter);
	    logger.info("HttpMessageConverter added");

	    // string
	    converter = new FormHttpMessageConverter();
	    converters.add(converter);
	    logger.info("FormHttpMessageConverter added");

	    // json
	    converter = new MappingJackson2HttpMessageConverter();
	    converters.add(converter);
	    logger.info("MappingJackson2HttpMessageConverter added");

	}

	/**
	 * Optional. It's only required when handling '.' in @PathVariables which
	 * otherwise ignore everything after last '.' in @PathVaidables argument.
	 * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164],
	 * still present in Spring 4.1.7. This is a workaround for this issue.
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}
}
