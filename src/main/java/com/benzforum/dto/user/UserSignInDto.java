package com.benzforum.dto.user;

public class UserSignInDto {

    private String nickname;

    private String password;

    private boolean isRemember;

    public void setIsRemember(boolean remember) {
        isRemember = remember;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsRemember() {
        return isRemember;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
}
