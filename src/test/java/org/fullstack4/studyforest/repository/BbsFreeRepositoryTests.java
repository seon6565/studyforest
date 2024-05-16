package org.fullstack4.studyforest.repository;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BbsFreeRepositoryTests {
    @Autowired
    private BbsFreeRepository bbsFreeRepository;

//    @Test
//    public void  testRegist(){
//        log.info("testRegist : ");
//        IntStream.rangeClosed(0,10).forEach(i->{
//            BbsFreeEntity bbs = BbsFreeEntity.builder()
//                    .category("test")
//                    .title("테스트 제목 "+i)
//                    .content("테스트 본문 "+i)
//                    //display_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date(2024-1900,4,(i%10==0?1:i%10))).toString())
//                    .build();
//            BbsFreeEntity bbsResult = bbsRepository.save(bbs);
//            log.info("testRegist Result : "+ bbsResult);
//        });
//    }
//
//    @Test
//    public void testView(){
//        int idx= 10;
//
//        Optional<BbsFreeEntity> result = bbsFreeRepository.findById(idx);
//        BbsFreeEntity bbs = result.orElse(null);
////        if(result.isPresent()){
////            result.get();
////        }
////        else{
////            throw new IllegalArgumentException();
////        }
////        result.orElseThrow(IllegalArgumentException::new);
////        result.orElseThrow(()->new IllegalArgumentException("no find data"));
////        result.orElseGet(BoardEntity::new);
////        result.ifPresent(b->{log.info("result:{}",b);});
//        log.info("testView Result : "+ result);
//    }
//
//    @Test
//    public void testModify(){
//        int idx = 12;
//        Optional<BbsFreeEntity> result = bbsFreeRepository.findById(idx);
//        BbsFreeEntity bbs = result.orElse(null);
////        if(bbs!=null) {
////            bbs = BoardEntity.builder().idx(idx).user_id("test").title("제목수정1").content("내용수정1")
////                    .display_date(new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString()).build();
////            boardRepository.save(bbs);
////        }
//        //bbs.modify("test","제목수정2","내용수정2",new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString());
////        bbsFreeRepository.save(bbs);
//        log.info("testModify Result : "+ bbs);
//    }
//
//    @Test
//    public void testDelete(){
//        int idx = 11;
//        bbsFreeRepository.deleteById(idx);
//    }
//    @Test
//    public void testSearch(){
//        PageRequest pageable = PageRequest.of(0,10, Sort.by("bbsIdx").descending());
//        String[] types = {"t","c","w"};
//        String search_keyword = "테스트";
//        String category = "자바";
//        bbsFreeRepository.search(pageable,types,search_keyword,category);
//
//    }
}
