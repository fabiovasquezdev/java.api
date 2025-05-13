package com.vazcode.presentation.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MemberDTO {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime birthDate;
    private LocalDateTime joinDate;
    private String address;
    private boolean isActive;
}

