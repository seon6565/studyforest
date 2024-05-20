package org.fullstack4.studyforest.service;


import org.fullstack4.studyforest.dto.*;

import java.util.List;

public interface BbsServiceIf {
    int regist(BbsDTO bbsDTO);
    BbsDTO view(BbsDTO bbsDTO);
    void modify(BbsDTO bbsDTO);
    void delete(BbsDTO bbsDTO);
    PageResponseDTO<BbsDTO> list(PageRequestDTO pageRequestDTO);
    PageResponseDTO<BbsDTO> listUser(PageRequestDTO pageRequestDTO,String user_id);

    int registShare(BbsShareDTO bbsShareDTO);
    void deleteShare(BbsShareDTO bbsShareDTO);
    List<BbsShareDTO> listShare(int bbsIdx);
    List<BbsShareDTO> listShareUserId(String userId);
    List<BbsShareDTO> listShareToUserId(String userId);

    int registGood(BbsGoodDTO bbsGoodDTO);
    void deleteGood(BbsGoodDTO bbsGoodDTO);
    List<BbsGoodDTO> listGood(String userId);
    BbsGoodDTO viewGood(BbsGoodDTO bbsGoodDTO);

    int registFile(BbsFileDTO bbsFileDTO);
    void deleteFile(BbsFileDTO bbsFileDTO);
    BbsFileDTO viewFile(int bbsIdx);

}
