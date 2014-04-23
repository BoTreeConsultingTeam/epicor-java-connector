package com.dehinsystems.api.epicor.parts;


import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dehinsystems.api.epicor.model.Part;

@RestController
@RequestMapping("/parts")
public class PartsController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Part> index(ModelMap model) {
        // TODO: This is a dummy implementation.Replace with actual implementation.
        List<Part> partsList = new ArrayList<Part>();
        partsList.add(new Part("test-id", "test part", "test part description"));
        return partsList;
    }

}
