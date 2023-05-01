package com.sysmap.parrot.application.requests.Authentication.Authenticate;

import lombok.Data;

@Data
public class AuthenticationRequest {
    public String email;
    public String password;
}
