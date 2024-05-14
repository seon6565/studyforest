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
import org.fullstack4.studyforest.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Log4j2
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginServiceIf loginServiceIf;
    private final CommonUtil commonUtil;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public void loginGET(HttpServletRequest req, Model model){
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
                            RedirectAttributes redirectAttributes,
                            HttpServletResponse response){
        String save_id = null;
        String auto_login = null;
        log.info("loginDTO = " + loginDTO);
        loginDTO.setPwd(commonUtil.encryptPwd(loginDTO.getPwd()));
        MemberDTO LoginMemberDTO = loginServiceIf.login(loginDTO);
        String uri = acc_url;
//        try {
//            uri = URLEncoder.encode(acc_url, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            throw new RuntimeException(e);
//        }
        if(LoginMemberDTO !=null){
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
            if(req.getServletPath().equals("/login")){
                return "redirect:/";
            }
            return "redirect:"+uri;
        }
        redirectAttributes.addFlashAttribute("errors","사용자 정보가 일치하지 않습니다.");
        redirectAttributes.addFlashAttribute("errorAlert","alert('사용자 정보가 일치하지 않습니다.')");
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse response){
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
        log.info("============================");
        log.info("LoginController logout");
        log.info("============================");

        return "redirect:/";
    }

    @RequestMapping("/autologin")
    public String autologin(HttpServletRequest req, HttpServletResponse response){
        String uri = req.getHeader("referer");
        HttpSession session= req.getSession();
        log.info("============================");
        log.info("LoginController loginCheck");
        log.info("============================");
        if(session.getAttribute("memberDTO")==null) {
            Cookie cookies[] = req.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auto_login")) {
                    session.setAttribute("memberDTO", loginServiceIf.cookieLogin(cookie.getValue()));
                    return "redirect:"+uri;
                }
            }
        }
        return "redirect:/login/login";
    }
}
