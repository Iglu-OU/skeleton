package ee.iglu.autoui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AutouiController {

	@RequestMapping("/")
	String home(Model model) {
		model.addAttribute("test", "value");
		return "index";
	}
}

