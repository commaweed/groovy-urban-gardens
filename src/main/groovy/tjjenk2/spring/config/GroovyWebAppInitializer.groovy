package tjjenk2.spring.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.ServletRegistration

/**
 * Servlet 3.0 Engine allows us to replace the web.xml with this file.  The code below simulates Spring
 * dispatcher servlet creation in the web.xml.
 * @author tjjenk2
 */
class GroovyWebAppInitializer implements WebApplicationInitializer {

	@SuppressWarnings("unused")
	static Logger LOGGER = LoggerFactory.getLogger(GroovyWebAppInitializer.class)

	/**
	 * {@inheritDoc}
	 */
	@Override
	void onStartup(ServletContext servletContext) throws ServletException {
		WebApplicationContext context = getContext()

		// listen to registration events from our servlet context
		servletContext.addListener(new ContextLoaderListener(context))

		// add the dispatcher servlet and configure it to load on startup and to accept all requests
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
			"DispatcherServlet",
			new DispatcherServlet(context)
		)

		dispatcher.setLoadOnStartup(1)
		dispatcher.addMapping("/")
	}

	/**
	 * Return the web application context that is defined by the annotation @EnableWebMvc
	 * @return A reference to the application context (i.e. WebAppConfig)
	 */
	private static AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext()
		context.setConfigLocation(GroovyWebAppConfig.class.getName())
		return context
	}
}
