package com.vazcode.application.usecases.member;

import com.vazcode.domain.entities.Member;
import com.vazcode.domain.repositories.MemberRepository;
import com.vazcode.presentation.dtos.MemberDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetMemberUseCase {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MemberDTO> findAll() {
        return memberRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MemberDTO findById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado com id: " + id));
        return convertToDTO(member);
    }

    private MemberDTO convertToDTO(Member member) {
        MemberDTO dto = new MemberDTO();
        BeanUtils.copyProperties(member, dto);
        return dto;
    }
}