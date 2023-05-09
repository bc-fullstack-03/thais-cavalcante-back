package com.sysmap.parrot.api.controllers;

import com.sysmap.parrot.application.requests.Authentication.Authenticate.AuthenticateRequest;
import com.sysmap.parrot.application.requests.Authentication.Authenticate.AuthenticateResponse;
import com.sysmap.parrot.application.requests.Authentication.IAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/authentication")
@Tag(name = "Authentication")
public class AuthenticationController {

    @Autowired
    private IAuthenticationService _authenticationService;

    @PostMapping()
    @Operation(summary = "Authenticate user")
    public ResponseEntity<AuthenticateResponse> authenticate (@RequestBody AuthenticateRequest request) {
        try {
            return ResponseEntity.ok().body(_authenticationService.authenticate(request));
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
