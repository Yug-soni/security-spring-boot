package com.yug.app.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.yug.app.security.ApplicationUserPermission.*;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationUserPermission> permissions;

}
