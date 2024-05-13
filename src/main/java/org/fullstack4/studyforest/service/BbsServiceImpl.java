package org.fullstack4.studyforest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.fullstack4.studyforest.dto.BbsDTO;
import org.fullstack4.studyforest.repository.BbsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class BbsServiceImpl implements BbsServiceIf {
    private final ModelMapper modelMapper;
    private final BbsRepository bbsRepository;
    @Override
    public int regist(BbsDTO bbsDTO) {
        BbsFreeEntity board = modelMapper.map(bbsDTO, BbsFreeEntity.class);
        int idx = bbsRepository.save(board).getBbsIdx();
        return idx;
    }

    @Override
    public BbsDTO view(int idx) {
        Optional<BbsFreeEntity> result = bbsRepository.findById(idx);
        BbsFreeEntity board = result.orElse(null);
        BbsDTO bbsDTO = modelMapper.map(board,BbsDTO.class);
        return bbsDTO;
    }

    @Override
    public void modify(BbsDTO bbsDTO) {
        Optional<BbsFreeEntity> result = bbsRepository.findById(bbsDTO.getBbsIdx());
        BbsFreeEntity board = result.orElse(null);
        if(board!=null) {
            //board.modify(bbsDTO.getUser_id(), bbsDTO.getTitle(), bbsDTO.getContent(), bbsDTO.getDisplay_date());
        }
    }

    @Override
    public void delete(BbsDTO bbsDTO) {

    }
}
