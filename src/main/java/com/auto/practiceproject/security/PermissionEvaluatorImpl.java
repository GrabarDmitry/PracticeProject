package com.auto.practiceproject.security;

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
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return getPermission(authentication, targetDomainObject, permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

    private boolean getPermission(Authentication authentication, Object target, Object perm) {

        final String permission = perm.toString();

        if (target instanceof Announcement) {
            return hasPermission(authentication, (Announcement) target, permission);
        }

        return false;

    }

    private boolean hasPermission(Authentication authentication, Announcement announcement, String perm) {
        if (announcement.getId() == null) {
            return false;
        } else {
            return ((UserDetailsImpl) authentication.getPrincipal())
                    .getUser().equals(announcement.getUser());
        }
    }

}
