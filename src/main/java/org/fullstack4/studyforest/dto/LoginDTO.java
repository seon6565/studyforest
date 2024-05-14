package org.fullstack4.studyforest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {
    private String user_id;
    private String pwd;
    private String save_id;
    private String auto_login;
}
