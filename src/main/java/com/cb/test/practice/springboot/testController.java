package com.cb.test.practice.springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {

    @Autowired
    People people;

    @GetMapping("/get")
    public String get(){
        return people.getName()+" "+people.getAge()+people.getHouse().getAddress()+" "+people.getHouse().getSize();
    }

    @GetMapping("getP")
    public String peo(Model model){
        model.addAttribute("peoples",people);

        return "user/user";
    }
}
