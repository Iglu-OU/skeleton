package com.example.greeter;

import ee.iglu.skeleton.greeter.Greeter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class SampleController {

    private final Greeter greeter;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        // Pebble classes are not visible thanks to implementation scope

        return greeter.hello("World");
    }

}
