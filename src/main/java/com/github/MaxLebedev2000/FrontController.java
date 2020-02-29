package com.github.MaxLebedev2000;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {

    @RequestMapping({"/main", "/"})
    public String redirect(){
        return "forward:index.html";
    }
}
