package org.example.aop;

import org.example.service.UserAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginAspect {
    @Autowired
    private UserAuditService userAuditService;

}
