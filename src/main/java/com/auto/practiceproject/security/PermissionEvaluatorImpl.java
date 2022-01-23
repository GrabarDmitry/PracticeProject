package com.auto.practiceproject.security;

import com.auto.practiceproject.controller.dto.request.ModeratorUserCreateDTO;
import com.auto.practiceproject.model.Announcement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class PermissionEvaluatorImpl implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object role) {
        return getPermission(authentication, targetDomainObject, role);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object role) {
        return false;
    }

    private boolean getPermission(Authentication authentication, Object target, Object role) {

        final String permission = role.toString();

        if (target instanceof Announcement) {
            return hasPermission(authentication, (Announcement) target, permission);
        } else if (target instanceof ModeratorUserCreateDTO) {
            return hasPermission(authentication, (ModeratorUserCreateDTO) target, permission);
        } else if (target == null) {
            return hasPermission(authentication, permission);
        }

        return false;

    }

    private boolean hasPermission(Authentication authentication, Announcement announcement, String role) {
        if (announcement.getId() == null) {
            return false;
        } else {
            return ((UserDetailsImpl) authentication.getPrincipal())
                    .getUser().equals(announcement.getUser());
        }
    }

    private boolean hasPermission(Authentication authentication, ModeratorUserCreateDTO createDTO, String role) {
        if (createDTO == null) {
            return false;
        }
        return hasPermission(authentication, role);
    }

    private boolean hasPermission(Authentication authentication, String role) {

        return ((UserDetailsImpl) authentication.getPrincipal()).
                getUser().getRoles().stream()
                .filter(role1 -> role1.getTitle().equals(role))
                .count() > 0;

    }

}
