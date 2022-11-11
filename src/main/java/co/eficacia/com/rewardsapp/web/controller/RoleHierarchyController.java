package co.eficacia.com.rewardsapp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RoleHierarchyController {

    @GetMapping("/role-hierarchy")
    public ModelAndView roleHierarchy() {
        ModelAndView model = new ModelAndView();
        model.addObject("adminMessage","Admin content available");
        model.addObject("staffMessage","Staff content available");
        model.addObject("userMessage","User content available");
        model.setViewName("role-hierarchy");
        return model;
    }
}
