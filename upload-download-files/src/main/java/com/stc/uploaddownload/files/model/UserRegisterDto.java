package com.stc.uploaddownload.files.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegisterDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String groupName;
}
