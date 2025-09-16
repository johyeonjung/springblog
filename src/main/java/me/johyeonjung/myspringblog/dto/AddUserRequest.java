package me.johyeonjung.myspringblog.dto;

import lombok.Getter;
import lombok.Setter;

//사용자 정보를 담고 있는 객체 작성
@Getter
@Setter
public class AddUserRequest {
    private String username;
    private String password;
    private String displayName;
}
