package org.fullstack4.studyforest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {
    private int member_idx;
    @NotEmpty
    @Pattern(regexp = "^[A-Za-z0-9]{4,15}$")
    private String userId;
    @NotEmpty
    @Pattern(regexp = "^(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,20}$")
    private String password;
    @NotEmpty
    @Pattern(regexp = "^[가-힣A-Za-z]{1,10}$")
    private String name;

    @Builder.Default
    private String roles = "user";
    @NotEmpty
    @Pattern(regexp = "\\w+@\\w+\\.\\w+(\\.\\w+)?")
    private String email;
    @NotEmpty
    @Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}")
    private String phone;
    @NotNull
    @PastOrPresent
    private LocalDate birthday;
    @NotEmpty
    private String addr1;
    @NotEmpty
    private String addr2;
    @NotEmpty
    private String addr_number;


    private String temp_password;
    private String temp_password_validdate;
    private int try_check;
    @Builder.Default
    private String state = "Y";

    private LocalDateTime reg_date;
    private LocalDateTime login_date;
    private LocalDateTime ban_date;
    private LocalDateTime leave_date;
    private LocalDateTime password_changedate;
}
