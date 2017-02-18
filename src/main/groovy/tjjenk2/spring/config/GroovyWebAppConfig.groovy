package tjjenk2.spring.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import org.springframework.web.servlet.view.BeanNameViewResolver
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.springframework.web.servlet.view.groovy.GroovyMarkupConfigurer

/**
 * Creates the spring application context and use an annotation (i.e. @EnableWebMvc) to define it as the bean file
 * known as the dispatcher-servlet.xml file.  You still have to hook it into either a web.xml file of a
 * WebAppInitializer file.
 * @author tjjenk2
 */
@Configuration
@EnableWebMvc
@ComponentScan(["tjjenk2.spring.component", "tjjenk2.config.thymeleaf"])
class GroovyWebAppConfig extends WebMvcConfigurationSupport {

//	/**
//	 * Spring does not know how to handle the views like JSPs, so tell it to delegate it to
//	 * the container.
//	 * @param configurer The configurer.
//	 */
//	@Override
//	void configureDefaultServletHandling (
//		DefaultServletHandlerConfigurer configurer
//	) {
//		configurer.enable()
//	}

	/**
	 * Add a view resolver for JSP pages.
	 * @return The JSP view resolver.
	 */
	@Bean
	ViewResolver getInternalResourceViewResolver () {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver()
		resolver.setPrefix("/WEB-INF/views/")
		resolver.setSuffix(".jsp")
		resolver.setOrder(2)
		return resolver
	}

	/**
	 * Adds a bean name view resolver for JSPs (probably use Thymeleaf templates.
	 * @return The bean name view resolver.
	 */
	@Bean ViewResolver getBeanNameViewResolver ( ) {
		return new BeanNameViewResolver()
	}

	/**
	 * Register groovy with the view resolver registry.
	 * @param registry The view resolver registry.
	 */
	@Override
	void configureViewResolvers (
			ViewResolverRegistry registry
	) {
		registry.groovy()
	}

	/**
	 * The Groovy Markup Template Engine is another view technology, supported by Spring. This template engine is a
	 * template engine primarily aimed at generating XML-like markup (XML, XHTML, HTML5, …​​), but that can be used to
	 * generate any text based content.  It requires Groovy 2.3.1+ on the the classpath.
	 * <pre>
	 * Example:
	 * 		yieldUnescaped '<!DOCTYPE html>'
	 *		html(lang:'en') {
	 *			head {
	 *				meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
	 *				title('My page')
	 *			}
	 *			body {
	 *				p('This is an example of HTML contents')
	 *			}
	 *		}
	 * </pre>
	 * @return The GroovyMarkupConfigurer.
	 */
	@Bean GroovyMarkupConfigurer groovyMarkupConfigurer ( ) {
		GroovyMarkupConfigurer configurer = new GroovyMarkupConfigurer()
		configurer.setResourceLoaderPath("/WEB-INF/template/groovy/")
		return configurer
	}

}

