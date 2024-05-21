package org.fullstack4.studyforest.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller

public class BbsController {
    @GetMapping("/bbs")
    public void list(){

    }

}
