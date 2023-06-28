package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
       model.addAttribute("data", "springo!");
       return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String msg, Model model){
        model.addAttribute("name", msg);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String msg){
        return "hello " + msg;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello HelloApi(@RequestParam("name") String msg){
        Hello hello = new Hello();
        hello.setMsg(msg);
        return hello;
    }
    static class Hello {
        private String msg;

        public String getMsg(){
            return msg;
        }
        public void setMsg(String msg){
            this.msg = msg;
        }
    }
}
