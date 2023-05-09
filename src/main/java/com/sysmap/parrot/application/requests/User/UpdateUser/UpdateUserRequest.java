package com.sysmap.parrot.application.requests.User.UpdateUser;

import lombok.Data;

@Data
public class UpdateUserRequest {
    public String name;
    public String email;
    public String password;
}
