package org.fullstack4.studyforest.controller;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.fullstack4.studyforest.repository.BbsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
public class BbsController {
    @Autowired
    private BbsRepository bbsRepository;

    @GetMapping("/bbs/list/")
    @ResponseBody
    public Map<String, Object> listAll(){
        PageRequest pageable = PageRequest.of(0,30, Sort.by("bbsIdx").descending());
        String[] types = {"t","c","w"};
        String search_keyword = "java";
        Page<BbsFreeEntity> result = bbsRepository.search(pageable,types,search_keyword,"");
        Map<String, Object> bbsDTO = new HashMap<>();
        bbsDTO.put("bbsDTO",result);
        return bbsDTO;
    }
    @GetMapping("/bbs/list/{category}")
    @ResponseBody
    public Map<String, Object> list(@PathVariable String category){
        PageRequest pageable = PageRequest.of(0,30, Sort.by("bbsIdx").descending());
        String[] types = {"t","c","w"};
        String search_keyword = "java";
        Page<BbsFreeEntity> result = bbsRepository.search(pageable,types,search_keyword,category);
        Map<String, Object> bbsDTO = new HashMap<>();
        bbsDTO.put("bbsDTO",result);
        return bbsDTO;
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
