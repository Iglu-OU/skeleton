package ee.iglu.autoui;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
class AutouiController {

    @Autowired
    private MethodList methodList;

    @RequestMapping("/")
    String home(@RequestParam(required = false) String method, Model model) {
        model.addAttribute("test", "value");
        model.addAttribute("methods", methodList.getMethodNames());
        model.addAttribute("activeMethod", method);
        model.addAttribute("fields", methodList.methodDescription(method));


        return "index";
    }

}

