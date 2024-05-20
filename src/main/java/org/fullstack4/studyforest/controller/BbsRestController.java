package org.fullstack4.studyforest.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.fullstack4.studyforest.dto.*;
import org.fullstack4.studyforest.repository.BbsFreeRepository;
import org.fullstack4.studyforest.service.BbsServiceIf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

@Log4j2
@RestController
@RequiredArgsConstructor
public class BbsRestController {
    private final BbsServiceIf bbsServiceIf;

//    @GetMapping("/bbs/list")
//    @ResponseBody
//    public PageResponseDTO<BbsDTO> listAll(PageRequestDTO pageRequestDTO){
//        pageRequestDTO.setCategory("free");
//        PageResponseDTO<BbsDTO> pageResponseDTO = bbsServiceIf.list(pageRequestDTO);
//        return pageResponseDTO;
//    }
    @GetMapping("/bbs/list/{category}")
    @ResponseBody
    public PageResponseDTO<BbsDTO> list(@PathVariable String category, PageRequestDTO pageRequestDTO){
        pageRequestDTO.setCategory(category);
        PageResponseDTO<BbsDTO> pageResponseDTO = bbsServiceIf.list(pageRequestDTO);
        return pageResponseDTO;
    }
    @GetMapping("/bbs/{category}/{idx}")
    public BbsDTO view(@PathVariable String category,@PathVariable int idx){
        BbsDTO bbsDTO = BbsDTO.builder().category(category).bbsIdx(idx).build();
        return bbsServiceIf.view(bbsDTO);
    }
    @PostMapping("/bbs/{category}")
    public String regist(@PathVariable String category, String title, String content, String category2
            ,String hashtag, String displayYN, HttpServletRequest request){
        HttpSession session= request.getSession();
        if(session.getAttribute("memberDTO")==null) {
            return "등록 실패, 권한 없음 로그인부터 하세요. 로그인 주소 = localhost:8080/login";
        }
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("memberDTO");
        String userId = memberDTO.getUserId();
        BbsDTO bbsDTO = BbsDTO.builder().user_id(userId).category(category).title(title).content(content)
                .category2(category2).hashtag(hashtag).display_date_flag(displayYN).build();
        int idx = bbsServiceIf.regist(bbsDTO);
        return "idx번호 : "+idx+" 등록 성공";
    }
    @PutMapping("/bbs/{category}/{idx}")
    public String modify(@PathVariable String category,@PathVariable int idx, String title, String content,
                         String category2, String hashtag, String displayYN,HttpServletRequest request){
        HttpSession session= request.getSession();
        if(session.getAttribute("memberDTO")==null) {
            return "수정 실패, 권한 없음 로그인부터 하세요. 로그인 주소 = localhost:8080/login";
        }
        BbsDTO bbsDTO = BbsDTO.builder().bbsIdx(idx).title(title).content(content).category(category)
                .category2(category2).hashtag(hashtag).display_date_flag(displayYN).build();
        bbsServiceIf.modify(bbsDTO);
        return "category : "+category+ " idx 번호 : "+idx+" 수정 성공";
    }
    @DeleteMapping("/bbs/{category}/{idx}")
    public String delete(@PathVariable String category, @PathVariable int idx, HttpServletRequest request){
        HttpSession session= request.getSession();
        if(session.getAttribute("memberDTO")==null) {
            return "삭제 실패, 권한 없음 로그인부터 하세요. 로그인 주소 = localhost:8080/login";
        }
        BbsDTO bbsDTO = BbsDTO.builder().bbsIdx(idx).build();
        bbsServiceIf.delete(bbsDTO);
        return "idx번호 : "+idx+" 삭제 성공";

    }

}
