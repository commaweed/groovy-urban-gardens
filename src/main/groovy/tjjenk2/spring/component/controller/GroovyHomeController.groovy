package tjjenk2.spring.component.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/home")
class GroovyHomeController {

	static Logger LOGGER = LoggerFactory.getLogger(GroovyHomeController.class)


	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@SuppressWarnings("SpringMVCViewInspection")
	@RequestMapping(value = "/time", method = RequestMethod.GET)
	String home(Locale locale, Model model) {
		LOGGER.info("Welcome home! The client locale is {}.", locale)
		String formattedDate = DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss").format(LocalDateTime.now())
		model.addAttribute("serverTime", formattedDate)
		return "home"
	}

}
