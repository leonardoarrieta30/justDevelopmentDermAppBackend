package com.DermApp.Backend.security.domain.service.communication;

import com.DermApp.Backend.security.resource.AuthenticateResource;
import com.DermApp.Backend.shared.domain.service.communication.BaseResponse;

public class AuthenticateResponse extends BaseResponse<AuthenticateResource> {
    public AuthenticateResponse(String message) {
        super(message);
    }

    public AuthenticateResponse(AuthenticateResource resource) {
        super(resource);
    }
}
