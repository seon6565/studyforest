package org.fullstack4.studyforest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.fullstack4.studyforest.dto.BbsDTO;
import org.fullstack4.studyforest.dto.MemberDTO;
import org.fullstack4.studyforest.dto.PageRequestDTO;
import org.fullstack4.studyforest.dto.PageResponseDTO;
import org.fullstack4.studyforest.repository.BbsFreeRepository;
import org.fullstack4.studyforest.service.BbsServiceIf;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
public class BbsRestController {
    private final BbsServiceIf bbsServiceIf;

    @GetMapping("/bbs/list")
    @ResponseBody
    public PageResponseDTO<BbsDTO> listAll(PageRequestDTO pageRequestDTO){
        pageRequestDTO.setCategory("free");
        PageResponseDTO<BbsDTO> pageResponseDTO = bbsServiceIf.list(pageRequestDTO);
        log.info("pageResponseDTO test : " + pageResponseDTO);
        return pageResponseDTO;
    }
    @GetMapping("/bbs/list/{category}")
    @ResponseBody
    public PageResponseDTO<BbsDTO> list(@PathVariable String category, PageRequestDTO pageRequestDTO){
        pageRequestDTO.setCategory(category);
        PageResponseDTO<BbsDTO> pageResponseDTO = bbsServiceIf.list(pageRequestDTO);
        log.info("pageResponseDTO test : " + pageResponseDTO);
        return pageResponseDTO;
    }
    @GetMapping("/bbs/{category}/{idx}")
    public void view(Model model,@PathVariable String category,@PathVariable int idx){
        log.info("category = " +category);
        log.info("idx = " +idx);
        model.addAttribute("msg","Hello");
    }
    @PostMapping("/bbs/{category}")
    public void regist(Model model, @PathVariable String category){
        log.info("category = " +category);
        model.addAttribute("msg","Hello");
    }
    @PutMapping("/bbs/{category}/{idx}")
    public void modify(Model model,@PathVariable String category,@PathVariable int idx){
        log.info("category = " +category);
        log.info("idx = " +idx);
        model.addAttribute("msg","Hello");
    }
    @DeleteMapping("/bbs/{category}/{idx}")
    public void delete(Model model,@PathVariable String category,@PathVariable int idx){
        log.info("category = " +category);
        log.info("idx = " +idx);
        model.addAttribute("msg","Hello");
    }
}
