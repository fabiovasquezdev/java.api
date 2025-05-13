package com.vazcode.application.usecases.member;

import com.vazcode.domain.entities.Member;
import com.vazcode.domain.repositories.MemberRepository;
import com.vazcode.presentation.dtos.MemberDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateMemberUseCase {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberDTO execute(Long id, MemberDTO memberDTO) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado com id: " + id));

        if (!member.getEmail().equals(memberDTO.getEmail()) && 
            memberRepository.existsByEmail(memberDTO.getEmail())) {
            throw new IllegalArgumentException("Email já registrado");
        }

        BeanUtils.copyProperties(memberDTO, member, "id");
        member = memberRepository.save(member);
        
        MemberDTO dto = new MemberDTO();
        BeanUtils.copyProperties(member, dto);
        return dto;
    }
}