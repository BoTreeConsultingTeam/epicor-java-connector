package com.dehinsystems.api.epicor.parts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/parts")
public class PartsController {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("allParts", "All Parts");
        return "partsIndex";
    }

}
