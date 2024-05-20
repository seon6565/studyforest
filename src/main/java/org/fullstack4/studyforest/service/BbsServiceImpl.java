package org.fullstack4.studyforest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.domain.BbsFileEntity;
import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.fullstack4.studyforest.domain.BbsGoodEntity;
import org.fullstack4.studyforest.domain.BbsShareEntity;
import org.fullstack4.studyforest.dto.*;
import org.fullstack4.studyforest.repository.BbsFileRepository;
import org.fullstack4.studyforest.repository.BbsFreeRepository;
import org.fullstack4.studyforest.repository.BbsGoodRepository;
import org.fullstack4.studyforest.repository.BbsShareRepository;
import org.fullstack4.studyforest.util.CommonFileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class BbsServiceImpl implements BbsServiceIf {
    private final ModelMapper modelMapper;
    private final BbsFreeRepository bbsFreeRepository;
    private final BbsShareRepository bbsShareRepository;
    private final BbsGoodRepository bbsGoodRepository;
    private final BbsFileRepository bbsFileRepository;
    private final CommonFileUtil commonFileUtil;
    @Override
    public BbsDTO view(BbsDTO bbsDTO) {
        String category=bbsDTO.getCategory();
        int idx = bbsDTO.getBbsIdx();
        if(category.equals("free")) {
            Optional<BbsFreeEntity> result = bbsFreeRepository.findById(idx);
            BbsFreeEntity board = result.orElse(null);
            return modelMapper.map(board, BbsDTO.class);
        }
        else {
            return bbsDTO;
        }
    }
    @Override
    public int regist(BbsDTO bbsDTO) {
        String category=bbsDTO.getCategory();
        if(category.equals("free")) {
            BbsFreeEntity board = modelMapper.map(bbsDTO, BbsFreeEntity.class);
            int idx = bbsFreeRepository.save(board).getBbsIdx();
            return idx;
        }
        return 0;
    }

    @Override
    public void modify(BbsDTO bbsDTO) {
        String category=bbsDTO.getCategory();
        if(category.equals("free")) {
            Optional<BbsFreeEntity> result = bbsFreeRepository.findById(bbsDTO.getBbsIdx());
            BbsFreeEntity board = result.orElse(null);
            if (board != null) {
                board.modify(bbsDTO.getCategory2(), bbsDTO.getTitle(), bbsDTO.getContent(), bbsDTO.getHashtag(), bbsDTO.getDisplay_date_flag()
                        , bbsDTO.getDisplay_date_start(), bbsDTO.getDisplay_date_end());
                bbsFreeRepository.save(board);
            }
        }
    }

    @Override
    public void delete(BbsDTO bbsDTO) {
        String category=bbsDTO.getCategory();
        int idx = bbsDTO.getBbsIdx();
        bbsFreeRepository.deleteById(idx);
    }

    @Override
    public PageResponseDTO<BbsDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getSearch_types();
        String search_word = pageRequestDTO.getSearch_word();
        String category = pageRequestDTO.getCategory();
        String category2 = pageRequestDTO.getCategory2();
        PageRequest pageable = pageRequestDTO.getPageable();
        if(category.equals("free")) {
            Page<BbsFreeEntity> result = bbsFreeRepository.search(pageable, types, search_word, category, category2);
            List<BbsDTO> dtoList = result.getContent().stream()
                    .map(board->modelMapper.map(board,BbsDTO.class))
                    .collect(Collectors.toList());
            return PageResponseDTO.<BbsDTO>withAll().pageRequestDTO(pageRequestDTO)
                    .dtoList(dtoList).total_count((int)result.getTotalElements()).build();

        }
        else if(category.equals("computer")) {

        }
        else if(category.equals("cook")) {

        }
        else {

        }
        return null;
    }
    @Override
    public PageResponseDTO<BbsDTO> listUser(PageRequestDTO pageRequestDTO, String user_id) {
        String[] types = pageRequestDTO.getSearch_types();
        String search_word = pageRequestDTO.getSearch_word();
        String category = pageRequestDTO.getCategory();
        String category2 = pageRequestDTO.getCategory2();
        String orderby = pageRequestDTO.getOrderby();
        LocalDate reg_date_start = pageRequestDTO.getReg_date_start();
        LocalDate reg_date_end = pageRequestDTO.getReg_date_end();
        PageRequest pageable = pageRequestDTO.getPageable();
        if(category.equals("free")) {
            Page<BbsFreeEntity> result = bbsFreeRepository.searchUserList(pageable, types, search_word, category, category2,user_id,orderby,reg_date_start,reg_date_end);
            List<BbsDTO> dtoList = result.getContent().stream()
                    .map(board->modelMapper.map(board,BbsDTO.class))
                    .collect(Collectors.toList());
            return PageResponseDTO.<BbsDTO>withAll().pageRequestDTO(pageRequestDTO)
                    .dtoList(dtoList).total_count((int)result.getTotalElements()).build();

        }
        else if(category.equals("computer")) {

        }
        else if(category.equals("cook")) {

        }
        else {

        }
        return null;
    }

    @Override
    public int registShare(BbsShareDTO bbsShareDTO) {
        BbsShareEntity board = modelMapper.map(bbsShareDTO, BbsShareEntity.class);
        int idx = bbsShareRepository.save(board).getShareIdx();
        return idx;
    }
    @Override
    public void deleteShare(BbsShareDTO bbsShareDTO) {
        bbsShareRepository.deleteById(bbsShareDTO.getShareIdx());
    }

    @Override
    public List<BbsShareDTO> listShare(int bbsIdx) {
        List<BbsShareEntity> bbsShareEntityList = bbsShareRepository.findAllByBbsIdx(bbsIdx);
        List<BbsShareDTO> bbsShareDTOList = bbsShareEntityList.stream().map(board->modelMapper.map(board,BbsShareDTO.class)).collect(Collectors.toList());
        return bbsShareDTOList;
    }

    @Override
    public List<BbsShareDTO> listShareUserId(String userId) {
        List<BbsShareEntity> bbsShareEntityList = bbsShareRepository.findAllByUserId(userId);
        List<BbsShareDTO> bbsShareDTOList = bbsShareEntityList.stream().map(board->modelMapper.map(board,BbsShareDTO.class)).collect(Collectors.toList());
        return bbsShareDTOList;
    }

    @Override
    public List<BbsShareDTO> listShareToUserId(String userId) {
        List<BbsShareEntity> bbsShareEntityList = bbsShareRepository.findAllByToUserId(userId);
        List<BbsShareDTO> bbsShareDTOList = bbsShareEntityList.stream().map(board->modelMapper.map(board,BbsShareDTO.class)).collect(Collectors.toList());
        return bbsShareDTOList;
    }

    @Override
    public int registGood(BbsGoodDTO bbsGoodDTO) {
        BbsGoodEntity board = modelMapper.map(bbsGoodDTO, BbsGoodEntity.class);
        int idx = bbsGoodRepository.save(board).getGoodIdx();
        int goodcnt = bbsGoodRepository.countAllByBbsIdx(bbsGoodDTO.getBbsIdx());
        Optional<BbsFreeEntity> result = bbsFreeRepository.findById(bbsGoodDTO.getBbsIdx());
        BbsFreeEntity board2 = result.orElse(null);
        board2.modifyGood(goodcnt);
        bbsFreeRepository.save(board2);
        return idx;
    }
    @Override
    public void deleteGood(BbsGoodDTO bbsGoodDTO) {
        bbsGoodRepository.deleteById(bbsGoodDTO.getGoodIdx());
        int goodcnt = bbsGoodRepository.countAllByBbsIdx(bbsGoodDTO.getBbsIdx());
        Optional<BbsFreeEntity> result = bbsFreeRepository.findById(bbsGoodDTO.getBbsIdx());
        BbsFreeEntity board2 = result.orElse(null);
        board2.modifyGood(goodcnt);
        bbsFreeRepository.save(board2);
    }

    @Override
    public List<BbsGoodDTO> listGood(String userId) {
        List<BbsGoodEntity> bbsGoodEntities = bbsGoodRepository.findAllByUserId(userId);
        List<BbsGoodDTO> bbsGoodDTOList = bbsGoodEntities.stream().map(board->modelMapper.map(board,BbsGoodDTO.class)).collect(Collectors.toList());
        return bbsGoodDTOList;
    }
    @Override
    public BbsGoodDTO viewGood(BbsGoodDTO bbsGoodDTO) {
        BbsGoodEntity bbsGoodEntity = bbsGoodRepository.findByBbsIdxAndUserId(bbsGoodDTO.getBbsIdx(),bbsGoodDTO.getUserId());
        if(bbsGoodEntity!=null) {
            return modelMapper.map(bbsGoodEntity, BbsGoodDTO.class);
        }
        else {
            return null;
        }
    }

    @Override
    public int registFile(BbsFileDTO bbsFileDTO) {
        BbsFileEntity board = modelMapper.map(bbsFileDTO, BbsFileEntity.class);
        int idx = bbsFileRepository.save(board).getBbsFileIdx();
        return idx;
    }
    @Override
    public void deleteFile(BbsFileDTO bbsFileDTO) {
        bbsFileRepository.deleteById(bbsFileDTO.getBbsFileIdx());
    }

    @Override
    public BbsFileDTO viewFile(int bbsIdx) {
        BbsFileEntity bbsFileEntity = bbsFileRepository.findByBbsIdx(bbsIdx);
        return modelMapper.map(bbsFileEntity, BbsFileDTO.class);
    }

}
