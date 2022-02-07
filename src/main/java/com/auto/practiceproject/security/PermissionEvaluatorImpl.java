package com.auto.practiceproject.security;

import com.auto.practiceproject.model.Announcement;
import com.auto.practiceproject.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class PermissionEvaluatorImpl implements PermissionEvaluator {

    private final AnnouncementService announcementService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object role) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object role) {
        return getPermission(authentication, (Long) targetId, targetType, role);
    }

    private boolean getPermission(Authentication authentication, Long targetId, String targetType, Object role) {

        final String permission = role.toString();

        if (targetType.equals("Announcement")) {
            return hasPermissionForAnnouncement(authentication, targetId, targetType, permission);
        } else if (targetId == null && targetType == null) {
            return hasPermissionOnlyByRole(authentication, (String) role);
        }

        return false;

    }

    private boolean hasPermissionForAnnouncement(Authentication authentication, Long targetId, String targetType, String role) {
        if (targetId == null) {
            return false;
        } else {
            Announcement announcement = announcementService.findAnnouncement(targetId)
                    .orElse(null);

            if (announcement != null) {
                return ((UserDetailsImpl) authentication.getPrincipal())
                        .getUser().equals(announcement.getUser());
            } else return false;
        }
    }

    private boolean hasPermissionOnlyByRole(Authentication authentication, String role) {
        return ((UserDetailsImpl) authentication.getPrincipal()).
                getUser().getRoles().stream()
                .filter(role1 -> role1.getTitle().equals(role))
                .count() > 0;
    }

}
