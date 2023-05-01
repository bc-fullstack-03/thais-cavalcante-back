package com.sysmap.parrot.application.requests.Authentication;

import com.sysmap.parrot.application.requests.Authentication.Authenticate.AuthenticationRequest;

public interface IAuthenticationService {
    String authenticateUser(AuthenticationRequest request);
}
