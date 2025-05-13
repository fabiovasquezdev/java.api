package com.vazcode.presentation.controllers;

import com.vazcode.application.usecases.member.*;
import com.vazcode.presentation.dtos.MemberDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Tag(name = "Members", description = "Member management endpoints")
public class MemberController {
    private final CreateMemberUseCase createMemberUseCase;
    private final UpdateMemberUseCase updateMemberUseCase;
    private final DeleteMemberUseCase deleteMemberUseCase;
    private final GetMemberUseCase getMemberUseCase;

    @GetMapping
    @Operation(summary = "Get all members")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        return ResponseEntity.ok(getMemberUseCase.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get member by ID")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(getMemberUseCase.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create new member")
    public ResponseEntity<MemberDTO> createMember(@Valid @RequestBody MemberDTO memberDTO) {
        return new ResponseEntity<>(createMemberUseCase.execute(memberDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing member")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Long id, @Valid @RequestBody MemberDTO memberDTO) {
        return ResponseEntity.ok(updateMemberUseCase.execute(id, memberDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete member")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        deleteMemberUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}