package org.fullstack4.studyforest.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.dto.LoginDTO;
import org.fullstack4.studyforest.dto.MemberDTO;
import org.fullstack4.studyforest.service.LoginServiceIf;
import org.fullstack4.studyforest.service.MemberServiceIf;
import org.fullstack4.studyforest.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;


@Log4j2
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginServiceIf loginServiceIf;
    private final MemberServiceIf memberServiceIf;
    private final CommonUtil commonUtil;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public void loginGET(HttpServletRequest req, Model model){
        HttpSession session = req.getSession();
        Cookie cookies[] = req.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("auto_login")) {
                model.addAttribute("auto_login", cookie.getValue());
            }
            else if(cookie.getName().equals("auto_login_flag")) {
                model.addAttribute("auto_login_flag", cookie.getValue());
            }
            else if(cookie.getName().equals("save_id")) {
                model.addAttribute("save_id", cookie.getValue());
            }
            else if(cookie.getName().equals("save_id_flag")) {
                model.addAttribute("save_id_flag", cookie.getValue());
            }
        }
        String acc_url = req.getHeader("referer");
        String uri = acc_url;
//        try {
//            uri = URLEncoder.encode(acc_url, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
        model.addAttribute("acc_url", uri);
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String loginPOST(LoginDTO loginDTO,
                            @RequestParam(name="acc_url", defaultValue = "/", required = false) String acc_url,
                            HttpServletRequest req,
                            Model model,
                            HttpServletResponse response,
                            RedirectAttributes redirectAttributes){
        String save_id = null;
        String auto_login = null;
        loginDTO.setPwd(commonUtil.encryptPwd(loginDTO.getPwd()));
        MemberDTO LoginMemberDTO = loginServiceIf.login(loginDTO);
        String uri = acc_url;
//        try {
//            uri = URLEncoder.encode(acc_url, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
        if(LoginMemberDTO !=null){
            if(LoginMemberDTO.getLogin_date().isBefore(LocalDateTime.now().minusMonths(6))){
                memberServiceIf.inactiveState(LoginMemberDTO);
                redirectAttributes.addFlashAttribute("info","alert(`6개월 이상 로그인 이력이 없어서 휴면 계정으로 전환되었습니다. \\n 관리자에게 문의해 주세요.`);");
                return "redirect:/login";
            }
            if(LoginMemberDTO.getState().equals("Y")){
                memberServiceIf.loginState(LoginMemberDTO);
                if(LoginMemberDTO.getPassword_changedate().isBefore(LocalDateTime.now().minusMonths(6))){
                    redirectAttributes.addFlashAttribute("info2","alert(`비밀번호를 변경하시지 않으신지 6개월이 지났습니다. \\n 비밀번호 변경을 권장드립니다.`);");
                }
            }
            else if(LoginMemberDTO.getState().equals("N")){
                redirectAttributes.addFlashAttribute("info","alert(`탈퇴한 계정입니다.`);");
                return "redirect:/login";
            }
            else if(LoginMemberDTO.getState().equals("H")){
                redirectAttributes.addFlashAttribute("info","alert(`6개월 이상 로그인 이력이 없어서 휴면 계정으로 전환되었습니다. \\n 관리자에게 문의해 주세요.`);");
                return "redirect:/login";
            }

            else if(LoginMemberDTO.getState().equals("B")){
                redirectAttributes.addFlashAttribute("info","alert(`이용 규칙 위반에 의하여 이용이 제한된 아이디입니다. \\n 관리자에게 문의해 주세요.`);");
                return "redirect:/login";
            }
            HttpSession session = req.getSession();
            if(loginDTO.getSave_id()!=null){
                save_id=loginDTO.getUser_id();
                Cookie cookie = new Cookie("save_id",save_id);
                cookie.setMaxAge(60*60*24*3);
                cookie.setPath("/");
                response.addCookie(cookie);
                Cookie cookie2 = new Cookie("save_id_flag","checked");
                cookie2.setMaxAge(60*60*24*3);
                cookie2.setPath("/");
                response.addCookie(cookie2);
            }
            if(loginDTO.getAuto_login()!=null){
                auto_login=loginDTO.getUser_id();
                Cookie cookie3 = new Cookie("auto_login",auto_login);
                cookie3.setPath("/");
                cookie3.setMaxAge(60*60*24*3);
                response.addCookie(cookie3);
                Cookie cookie4 = new Cookie("auto_login_flag","checked");
                cookie4.setPath("/");
                cookie4.setMaxAge(60*60*24*3);
                response.addCookie(cookie4);
            }
            session.setAttribute("memberDTO",LoginMemberDTO);
            redirectAttributes.addFlashAttribute("info","alert(`로그인 성공`);");
            if(req.getServletPath().equals("/login")){
                //return "redirect:/";
                return "redirect:/member/view";
            }
            return "redirect:/member/view";
            //return "redirect:"+uri;
        }
        model.addAttribute("acc_url", uri);
        model.addAttribute("info","alert(`입력하신 아이디 혹은 비밀번호가 일치하지 않습니다.`);");
        return "/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse response, RedirectAttributes redirectAttributes){
        HttpSession session = req.getSession(false);
        if(session!=null) {
            session.invalidate();
        }
        Cookie cookies[] = req.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("auto_login")) {
                Cookie cookie1 = cookie;
                cookie1.setPath("/");
                cookie1.setMaxAge(0);
                response.addCookie(cookie1);
            }
            else if(cookie.getName().equals("auto_login_flag")) {
                Cookie cookie2 = cookie;
                cookie2.setMaxAge(0);
                cookie2.setPath("/");
                response.addCookie(cookie2);
            }
            else if(cookie.getName().equals("save_id")) {
                Cookie cookie3 = cookie;
                cookie3.setPath("/");
                cookie3.setMaxAge(0);
                response.addCookie(cookie3);
            }
            else if(cookie.getName().equals("save_id_flag")) {
                Cookie cookie4 = cookie;
                cookie4.setPath("/");
                cookie4.setMaxAge(0);
                response.addCookie(cookie4);
            }
        }
        redirectAttributes.addFlashAttribute("info","alert(`로그아웃 완료`);");
        return "redirect:/";
    }

    @RequestMapping("/autologin")
    public String autologin(HttpServletRequest req){
        String uri = req.getHeader("referer");
        HttpSession session= req.getSession();
        if(session.getAttribute("memberDTO")==null) {
            if(req.getCookies() !=null) {
                Cookie cookies[] = req.getCookies();
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("auto_login")) {
                        session.setAttribute("memberDTO", loginServiceIf.cookieLogin(cookie.getValue()));
                        return "redirect:" + uri;
                    }
                }
            }
            return "redirect:/login";
        }
        return "redirect:/member/view";
    }
}
