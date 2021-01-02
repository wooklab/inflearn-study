package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";     // ViewResolver
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";    // ViewResolver
    }

    @GetMapping("hello-string")
    @ResponseBody   // for API
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello spring"
        // HttpMessageConvertor -> StringConverter(StringHttpMessageConverter)
    }

    @GetMapping("hello-api")
    @ResponseBody   // for API
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;   // HttpMessageConvertor -> JsonConverter(MappingJackson2HttpMessageConverter)
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
