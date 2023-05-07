package com.sysmap.parrot.application.requests.Authentication;

import com.sysmap.parrot.application.requests.Authentication.Authenticate.AuthenticateRequest;
import com.sysmap.parrot.application.requests.Authentication.Authenticate.AuthenticateResponse;

public interface IAuthenticationService {
//    String authenticateUser(AuthenticationRequest request);
    AuthenticateResponse authenticate(AuthenticateRequest request) throws Exception;
}
