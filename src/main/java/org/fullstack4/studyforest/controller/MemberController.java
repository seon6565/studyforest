package org.fullstack4.studyforest.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.dto.MemberDTO;
import org.fullstack4.studyforest.service.MemberServiceIf;
import org.fullstack4.studyforest.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Log4j2
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberServiceIf memberServiceIf;
    private final CommonUtil commonUtil;
    @GetMapping("/view")
    public void view(@RequestParam(name="member_idx", defaultValue = "0") int member_idx, Model model){
        MemberDTO memberDTO = memberServiceIf.view(member_idx);
        model.addAttribute("memberDTO",memberDTO);
    }
    @GetMapping("/regist")
    public void registGET(){
    }
    @PostMapping("/regist")
    public String registPOST(@Valid MemberDTO memberDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        memberDTO.setPassword(commonUtil.encryptPwd(memberDTO.getPassword()));
        log.info("memberDTO : " + memberDTO);
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("memberDTO",memberDTO);
            return "redirect:/member/regist";
        }

        int result = memberServiceIf.regist(memberDTO);
        if(result > 0 ){
            return "redirect:/login";
        }
        else{
            return "/member/regist";
        }
    }
    @GetMapping("/duplecheck")
    public void duplecheckGET(HttpServletRequest request, HttpServletResponse response){
        String user_id = request.getParameter("user_id");
        log.info("user_id=" +user_id);
        int count = 0;
        if(memberServiceIf.idCheck(user_id) >0){
            count = memberServiceIf.idCheck(user_id);
        }
        if(count > 0) {
            try {
                response.getWriter().print("N");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else {
            try {
                response.getWriter().print("Y");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @GetMapping("/modify")
    public void modifyGET(@RequestParam(name="member_idx", defaultValue = "") int member_idx, Model model){
        log.info("============================");
        log.info("MemberController modifyGET");
        log.info("============================");
        MemberDTO memberDTO = memberServiceIf.view(member_idx);
        model.addAttribute("memberDTO",memberDTO);
    }
    @PostMapping("/modify")
    public String modifyPOST(MemberDTO memberDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("============================");
        log.info("MemberController modifyPOST");
        log.info("============================");
        memberDTO.setPassword(commonUtil.encryptPwd(memberDTO.getPassword()));
        if(bindingResult.hasErrors()){
            log.info("bindingResult Errors : " +memberDTO);
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("memberDTO",memberDTO);
            redirectAttributes.addAttribute("member_idx",memberDTO.getMember_idx());
            return "redirect:/member/modify";
        }
        memberServiceIf.modify(memberDTO);
        return "redirect:/member/view?member_idx="+memberDTO.getMember_idx();
    }
    @PostMapping("/delete")
    public String deletePOST(int member_idx, HttpServletRequest request){
        memberServiceIf.delete(member_idx);
        request.getSession().invalidate();
        return "redirect:/logout";
    }

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
