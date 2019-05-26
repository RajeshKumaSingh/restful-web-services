package my.restful.restfulwebservices;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

// Controller
@RestController
public class HelloWorldController {
	
	@Autowired
	public MessageSource messageSource;
	
	// GET
	// URI - helloWorld
	// method - "Hello World"
	@GetMapping("/")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/Greet")
	public String helloWorldI18n(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
		return messageSource.getMessage("good.morning", null, locale);
	}
}
