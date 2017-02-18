package tjjenk2.spring.config.thymeleaf

import groovy.text.markup.TemplateResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import org.thymeleaf.spring4.SpringTemplateEngine
import org.thymeleaf.spring4.view.ThymeleafViewResolver
import org.thymeleaf.templateresolver.ServletContextTemplateResolver
/**
 * The spring configuration file for all things related to Thymeleaf.
 */
@Configuration
class ThymeleafSpringConfiguration extends WebMvcConfigurationSupport {
	// add thymeleaf (all can replace jsps)

	/**
	 * Define the template resolver for Thymeleaf.
	 * @return The Thymeleaf template resolver
	 */
	@Bean
	TemplateResolver templateResolver() {
		def resolver = new ServletContextTemplateResolver(this.servletContext) as TemplateResolver

		resolver.properties.prefix = '/WEB-INF/templates/'
		resolver.properties.suffix = '.html'
		resolver.properties.templateMode = 'HTML5'

		return resolver
	}

	/**
	 * Wire the template resolver to the engine that will be used as a spring view resolver.
	 * @return The spring template engine.
	 */
	@Bean
	SpringTemplateEngine templateEngine() {
		def engine = new SpringTemplateEngine()
		engine.properties.templateResolver = templateResolver()
		return engine
	}

	/**
	 * Define Thymeleaf as a Spring view resolver.
	 * @return A Spring view resolver
	 */
	@Bean
	ViewResolver viewResolver() {
		def resolver = new ThymeleafViewResolver()
		resolver.templateEngine = templateEngine()
		resolver.order = 2
		resolver.viewNames = "*.html, *.xhtml"
		return resolver
	}


}
