package org.fullstack4.studyforest.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.dto.*;
import org.fullstack4.studyforest.service.BbsServiceIf;
import org.fullstack4.studyforest.service.MemberServiceIf;
import org.fullstack4.studyforest.util.CommonFileUtil;
import org.fullstack4.studyforest.util.CommonUtil;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Log4j2
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberServiceIf memberServiceIf;
    private final BbsServiceIf bbsServiceIf;
    private final CommonUtil commonUtil;
    private final CommonFileUtil commonFileUtil;
    @GetMapping("/view")
    public void view(){
    }
    @GetMapping("/regist")
    public void registGET(){
    }
    @PostMapping("/regist")
    public String registPOST(@Valid MemberDTO memberDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        memberDTO.setPassword(commonUtil.encryptPwd(memberDTO.getPassword()));
        if(bindingResult.hasErrors()){
            model.addAttribute("errors",bindingResult.getAllErrors());
            model.addAttribute("memberDTO",memberDTO);
            return "/member/regist";
        }
        int result = memberServiceIf.regist(memberDTO);
        if(result > 0 ){
            redirectAttributes.addFlashAttribute("info","alert(`가입이 완료되었습니다.`);");
            return "redirect:/login";
        }
        else{
            return "/member/regist";
        }
    }
    @GetMapping("/duplecheck")
    public void duplecheckGET(HttpServletRequest request, HttpServletResponse response){
        String user_id = request.getParameter("user_id");
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
    public void modifyGET(){
    }
    @PostMapping("/modify")
    public String modifyPOST(@Valid MemberDTO memberDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes,HttpServletRequest request){
        if(bindingResult.hasErrors()){
            log.info("bindingResult Errors : " +memberDTO);
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("info","alert(`회원 정보 수정 실패 올바른 값을 입력해 주세요.`);");
            return "redirect:/member/modify";
        }
        MemberDTO modifyDTO = memberServiceIf.modify(memberDTO);
        request.getSession().setAttribute("memberDTO",modifyDTO);
        redirectAttributes.addFlashAttribute("info","alert(`회원 정보 수정 성공`);");
        return "redirect:/member/view";
    }
    @GetMapping("/modifypwd")
    public void modifyPwdGET(){
    }
    @PostMapping("/modifypwd")
    public String modifyPwdPOST(MemberDTO memberDTO, RedirectAttributes redirectAttributes, HttpServletRequest request){
        if(!memberDTO.getPassword().matches("^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$")){
            redirectAttributes.addFlashAttribute("info","alert(`비밀번호를 조건에 맞게 입력해주세요.`);");
            return "redirect:/member/modifypwd";
        }
        memberDTO.setPassword(commonUtil.encryptPwd(memberDTO.getPassword()));
        memberDTO.setTemp_password(commonUtil.encryptPwd(memberDTO.getTemp_password()));

        MemberDTO modifyDTO = memberServiceIf.modifyPassword(memberDTO);
        request.getSession().setAttribute("memberDTO",modifyDTO);
        redirectAttributes.addFlashAttribute("info","alert(`비밀번호 변경 성공`);");
        return "redirect:/member/view";
    }

    @GetMapping("/pwdcheck")
    public void pwdcheckGET(HttpServletRequest request, HttpServletResponse response){
        MemberDTO memberDTO = (MemberDTO) request.getSession().getAttribute("memberDTO");
        String pwd =memberDTO.getPassword();
        String tempPwd = commonUtil.encryptPwd(request.getParameter("temp_password"));
        if(pwd.equals(tempPwd)){
            try {
                response.getWriter().print("Y");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                response.getWriter().print("N");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @GetMapping("/leave")
    public String deletePOST(HttpServletRequest request){
        MemberDTO memberDTO = (MemberDTO) request.getSession().getAttribute("memberDTO");
        memberServiceIf.leave(memberDTO.getMember_idx());
        return "redirect:/logout";
    }

    @GetMapping("/mypage")
    public void mypage(){
    }

    @GetMapping("/mybbsview")
    public void view(BbsDTO bbsDTO, PageRequestDTO pageRequestDTO, Model model, HttpServletRequest request){
        BbsDTO resultbbsDTO = bbsServiceIf.view(bbsDTO);
        List<BbsShareDTO> bbsShareDTOList = bbsServiceIf.listShare(bbsDTO.getBbsIdx());
        MemberDTO memberDTO = (MemberDTO) request.getSession().getAttribute("memberDTO");
        BbsFileDTO fileDTO = bbsServiceIf.viewFile(bbsDTO.getBbsIdx());
        fileDTO.setDirectory(fileDTO.getDirectory().replace("D:\\java4\\studyforest\\studyforest\\src\\main\\resources\\static\\img\\upload","/img/upload/"));
        if(memberDTO != null) {
            BbsGoodDTO bbsGoodDTO = BbsGoodDTO.builder().bbsIdx(bbsDTO.getBbsIdx()).userId(memberDTO.getUserId()).build();
            BbsGoodDTO resultDTO = bbsServiceIf.viewGood(bbsGoodDTO);
            model.addAttribute("bbsGoodDTO",resultDTO);
        }
        model.addAttribute("linkParam","?page="+pageRequestDTO.getPage()+pageRequestDTO.getLinkParams());
        model.addAttribute("bbsShareDTO",bbsShareDTOList);
        model.addAttribute("bbsDTO",resultbbsDTO);
        model.addAttribute("fileDTO",fileDTO);
    }

    @GetMapping("/mybbsregist")
    public void regist(BbsDTO bbsDTO, PageRequestDTO pageRequestDTO, Model model){
    }
    @PostMapping("/mybbsregist")
    public String registPost(BbsDTO bbsDTO, PageRequestDTO pageRequestDTO, Model model, MultipartHttpServletRequest files){
        int resultidx = bbsServiceIf.regist(bbsDTO);
        String saveDirectory = "D:\\java4\\studyforest\\studyforest\\src\\main\\resources\\static\\img\\upload";
        List<String> filenames = null;
        filenames = commonFileUtil.fileuploads(files,saveDirectory);;
            if(filenames!=null) {
                for (String filename : filenames) {
                    BbsFileDTO bbsFileDTO = BbsFileDTO.builder().bbsIdx(resultidx).category(bbsDTO.getCategory()).directory(saveDirectory)
                            .fileName(filename).userId(bbsDTO.getUser_id()).build();
                    bbsServiceIf.registFile(bbsFileDTO);
                }
            }
        return "redirect:/member/mystudy";
    }
    @GetMapping("/mybbsmodify")
    public void modifyGet(BbsDTO bbsDTO, PageRequestDTO pageRequestDTO, Model model){
        BbsDTO viewDTO = bbsServiceIf.view(bbsDTO);
        BbsFileDTO fileDTO = bbsServiceIf.viewFile(bbsDTO.getBbsIdx());
        model.addAttribute("bbsDTO",viewDTO);
        model.addAttribute("fileDTO",fileDTO);
    }
    @PostMapping("/mybbsmodify")
    public String modifyPost(BbsDTO bbsDTO, Model model, MultipartHttpServletRequest files){
        bbsServiceIf.modify(bbsDTO);
        BbsFileDTO bbsFileDTO = bbsServiceIf.viewFile(bbsDTO.getBbsIdx());
        String saveDirectory = "D:\\java4\\studyforest\\studyforest\\src\\main\\resources\\static\\img\\upload";
        List<String> filenames = null;
        filenames = commonFileUtil.fileuploads(files,saveDirectory);;
        if(filenames!=null) {
            commonFileUtil.fileDelite(bbsFileDTO.getDirectory(),bbsFileDTO.getFileName());

            for (String filename : filenames) {
                bbsServiceIf.deleteFile(bbsFileDTO);
                BbsFileDTO bbsFileDTO2 = BbsFileDTO.builder().bbsIdx(bbsDTO.getBbsIdx()).category(bbsDTO.getCategory()).directory(saveDirectory)
                        .fileName(filename).userId(bbsDTO.getUser_id()).build();
                bbsServiceIf.registFile(bbsFileDTO2);
            }
        }
        return "redirect:/member/mybbsview?bbsIdx="+bbsDTO.getBbsIdx()+"&category="+bbsDTO.getCategory();
    }

    @GetMapping("/mybbsdelete")
    public String delete(BbsDTO bbsDTO, Model model){
        bbsServiceIf.delete(bbsDTO);
        BbsFileDTO bbsFileDTO = bbsServiceIf.viewFile(bbsDTO.getBbsIdx());
        commonFileUtil.fileDelite(bbsFileDTO.getDirectory(),bbsFileDTO.getFileName());
        bbsServiceIf.deleteFile(bbsFileDTO);
        return "redirect:/member/mystudy";
    }

    @GetMapping("/mystudy")
    public void mystudy(PageRequestDTO pageRequestDTO, Model model,HttpServletRequest request){
        MemberDTO memberDTO = (MemberDTO) request.getSession().getAttribute("memberDTO");
        String user_id = memberDTO.getUserId();
        pageRequestDTO.setCategory("free");
        PageResponseDTO<BbsDTO> pageResponseDTO = bbsServiceIf.listUser(pageRequestDTO,user_id);
        model.addAttribute("pageResponseDTO" , pageResponseDTO);

    }

    @GetMapping("/myshare")
    public void listshare(Model model,HttpServletRequest request, BbsShareDTO bbsShareDTO){
        MemberDTO memberDTO = (MemberDTO) request.getSession().getAttribute("memberDTO");
        String userId = memberDTO.getUserId();
        if(bbsShareDTO.getToUserId()!=null) {
            List<BbsShareDTO> bbsShareDTOList = bbsServiceIf.listShareToUserId(bbsShareDTO.getToUserId());
            model.addAttribute("bbsShareDTO",bbsShareDTOList);
        }
        else {
            List<BbsShareDTO> bbsShareDTOList = bbsServiceIf.listShareUserId(userId);
            model.addAttribute("bbsShareDTO", bbsShareDTOList);
        }
    }


    @PostMapping("/shareregist")
    public String regist(BbsShareDTO bbsShareDTO){
        bbsServiceIf.registShare(bbsShareDTO);
        return "redirect:/member/mybbsview?bbsIdx="+bbsShareDTO.getBbsIdx()+"&category="+bbsShareDTO.getCategory();
    }

    @GetMapping("/sharedelete")
    public String delete(BbsShareDTO bbsShareDTO){
        bbsServiceIf.deleteShare(bbsShareDTO);
        return "redirect:/member/myshare";
    }

    @GetMapping("/goodregist")
    public String registgood(BbsGoodDTO bbsGoodDTO){
        bbsServiceIf.registGood(bbsGoodDTO);
        return "redirect:/member/mybbsview?bbsIdx="+bbsGoodDTO.getBbsIdx()+"&category="+bbsGoodDTO.getCategory();
    }

    @GetMapping("/gooddelete")
    public String deletegood(BbsGoodDTO bbsGoodDTO){
        bbsServiceIf.deleteGood(bbsGoodDTO);
        return "redirect:/member/mygood";
    }

    @GetMapping("/mygood")
    public void listgood(Model model,HttpServletRequest request, BbsGoodDTO bbsGoodDTO){
        MemberDTO memberDTO = (MemberDTO) request.getSession().getAttribute("memberDTO");
        String userId = memberDTO.getUserId();
        List<BbsGoodDTO> bbsGoodDTOList = bbsServiceIf.listGood(userId);
        model.addAttribute("bbsGoodDTO", bbsGoodDTOList);
    }
}
