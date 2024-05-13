package org.fullstack4.studyforest.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/searchpwd")
    public void searchpwd(){

    }

    @PostMapping("/searchpwd")
    public String searchpwdPost(){

        return "redirect:/member/changepwd";
    }

    @GetMapping("/changepwd")
    public void changepwd(){

    }

    @GetMapping("/regist")
    public void regist(){

    }
    @GetMapping("/mypage")
    public void mypage(){

    }
    @GetMapping("/mystudy")
    public void mystudy(){

    }
    @GetMapping("/myshare")
    public void myshare(){

    }
}
