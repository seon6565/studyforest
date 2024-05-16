package org.fullstack4.studyforest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.studyforest.domain.BbsFreeEntity;
import org.fullstack4.studyforest.dto.BbsDTO;
import org.fullstack4.studyforest.dto.PageRequestDTO;
import org.fullstack4.studyforest.dto.PageResponseDTO;
import org.fullstack4.studyforest.repository.BbsFreeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            Page<BbsFreeEntity> result = bbsFreeRepository.search(pageable, types, search_word, category, category2);
            List<BbsDTO> dtoList = result.getContent().stream()
                    .map(board->modelMapper.map(board,BbsDTO.class))
                    .collect(Collectors.toList());
            return PageResponseDTO.<BbsDTO>withAll().pageRequestDTO(pageRequestDTO)
                    .dtoList(dtoList).total_count((int)result.getTotalElements()).build();

        }
        if(category.equals("cook")) {
            Page<BbsFreeEntity> result = bbsFreeRepository.search(pageable, types, search_word, category, category2);
            List<BbsDTO> dtoList = result.getContent().stream()
                    .map(board->modelMapper.map(board,BbsDTO.class))
                    .collect(Collectors.toList());
            return PageResponseDTO.<BbsDTO>withAll().pageRequestDTO(pageRequestDTO)
                    .dtoList(dtoList).total_count((int)result.getTotalElements()).build();

        }
        else if(category.equals("all")) {
            Page<BbsFreeEntity> result = bbsFreeRepository.search(pageable, types, search_word, category, category2);
            List<BbsDTO> dtoList = result.getContent().stream()
                    .map(board->modelMapper.map(board,BbsDTO.class))
                    .collect(Collectors.toList());
            return PageResponseDTO.<BbsDTO>withAll().pageRequestDTO(pageRequestDTO)
                    .dtoList(dtoList).total_count((int)result.getTotalElements()).build();

        }
        return null;
    }
    @Override
    public PageResponseDTO<BbsDTO> listUser(PageRequestDTO pageRequestDTO, String user_id) {
        String[] types = pageRequestDTO.getSearch_types();
        String search_word = pageRequestDTO.getSearch_word();
        String category = pageRequestDTO.getCategory();
        String category2 = pageRequestDTO.getCategory2();
        PageRequest pageable = pageRequestDTO.getPageable();
        if(category.equals("free")) {
            Page<BbsFreeEntity> result = bbsFreeRepository.searchUserList(pageable, types, search_word, category, category2,user_id);
            List<BbsDTO> dtoList = result.getContent().stream()
                    .map(board->modelMapper.map(board,BbsDTO.class))
                    .collect(Collectors.toList());
            return PageResponseDTO.<BbsDTO>withAll().pageRequestDTO(pageRequestDTO)
                    .dtoList(dtoList).total_count((int)result.getTotalElements()).build();

        }
        else if(category.equals("computer")) {
            Page<BbsFreeEntity> result = bbsFreeRepository.searchUserList(pageable, types, search_word, category, category2,user_id);
            List<BbsDTO> dtoList = result.getContent().stream()
                    .map(board->modelMapper.map(board,BbsDTO.class))
                    .collect(Collectors.toList());
            return PageResponseDTO.<BbsDTO>withAll().pageRequestDTO(pageRequestDTO)
                    .dtoList(dtoList).total_count((int)result.getTotalElements()).build();

        }
        if(category.equals("cook")) {
            Page<BbsFreeEntity> result = bbsFreeRepository.searchUserList(pageable, types, search_word, category, category2,user_id);
            List<BbsDTO> dtoList = result.getContent().stream()
                    .map(board->modelMapper.map(board,BbsDTO.class))
                    .collect(Collectors.toList());
            return PageResponseDTO.<BbsDTO>withAll().pageRequestDTO(pageRequestDTO)
                    .dtoList(dtoList).total_count((int)result.getTotalElements()).build();

        }
        else if(category.equals("all")) {
            Page<BbsFreeEntity> result = bbsFreeRepository.searchUserList(pageable, types, search_word, category, category2,user_id);
            List<BbsDTO> dtoList = result.getContent().stream()
                    .map(board->modelMapper.map(board,BbsDTO.class))
                    .collect(Collectors.toList());
            return PageResponseDTO.<BbsDTO>withAll().pageRequestDTO(pageRequestDTO)
                    .dtoList(dtoList).total_count((int)result.getTotalElements()).build();

        }
        return null;
    }
}
