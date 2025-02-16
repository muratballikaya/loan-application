package com.mb.auth.dto.request;

import java.io.Serializable;
import java.util.List;

public class TokenPayload implements Serializable {
    String userName;
    List<String> roles;

    public TokenPayload(String userName, List<String> roles){
        this.userName=userName;
        this.roles =roles;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
