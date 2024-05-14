package org.fullstack4.studyforest.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Service
public class CommonLoginCheck {

    public String adminCheck(HttpServletRequest request, RedirectAttributes redirectAttributes){
        HttpSession session = request.getSession();
        if(session.getAttribute("adminDTO")==null){
            redirectAttributes.addFlashAttribute("errorAlert","<script> alert('해당페이지는 관리자 로그인이 필요합니다.') </script>");
            return "redirect:/admin/login";
        }
        else{
            return "/admin/anotice/list";
        }
    }
}
