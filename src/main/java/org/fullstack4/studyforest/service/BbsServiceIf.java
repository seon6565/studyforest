package org.fullstack4.studyforest.service;


import org.fullstack4.studyforest.dto.BbsDTO;
import org.fullstack4.studyforest.dto.PageRequestDTO;
import org.fullstack4.studyforest.dto.PageResponseDTO;

public interface BbsServiceIf {
    int regist(BbsDTO bbsDTO);
    BbsDTO view(BbsDTO bbsDTO);
    void modify(BbsDTO bbsDTO);
    void delete(BbsDTO bbsDTO);
    PageResponseDTO<BbsDTO> list(PageRequestDTO pageRequestDTO);
    PageResponseDTO<BbsDTO> listUser(PageRequestDTO pageRequestDTO,String user_id);

}
