package org.fullstack4.studyforest.service;


import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.dto.BbsDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class BbsServiceTests {
    @Autowired
    private BbsServiceIf bbsService;

    @Test
    public void testRegist(){
//        log.info("====================");
//        log.info("BoardServiceTests >> testRegist Start");
//
//        log.info("boardService.getClass().getName(): {}", bbsService.getClass().getName());
//        IntStream.rangeClosed(0,10).forEach(i-> {
//                    BbsDTO bbsDTO = BbsDTO.builder().user_id("test2").title("영어 테스트"+i).content("국어 테스트"+i)
//                            .category("free").category2("수학").hashtag("태그"+i).display_date_flag("Y").build();
//                    int result = bbsService.regist(bbsDTO);
//                log.info("result : "+result);
//                });
//
//        log.info("====================");
    }

//    @Test
//    public void testModify(){
//        log.info("====================");
//        log.info("BoardServiceTests >> testModify Start");
//
//        log.info("boardService.getClass().getName(): {}", bbsService.getClass().getName());
//
//        BbsDTO bbsDTO = BbsDTO.builder().bbsIdx(26).user_id("test").title("수정 테스트23").content("수정 테스트23").build();
//        bbsService.modify(bbsDTO);
//
//        log.info("testModify bbsDTO : "+bbsDTO);
//        log.info("====================");
//    }
//
//    @Test
//    public void testView(){
//        int idx = 10;
//        log.info("====================");
//        log.info("BoardServiceTests >> testView Start");
//
//        log.info("boardService.getClass().getName(): {}", bbsService.getClass().getName());
//
//        BbsDTO bbsDTO = bbsService.view(idx);
//
//        log.info("testView bbsDTO : "+bbsDTO);
//        log.info("====================");
//    }
}
