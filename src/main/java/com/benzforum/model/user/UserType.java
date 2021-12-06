package com.benzforum.model.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;

public enum UserType {
    SIMPLE("SIMPLE"),
    MODERATOR("MODERATOR"),
    ADMIN("ADMIN");

    private String name;

    UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public static UserType fromUserType(String name) {
        for (UserType userType : UserType.values()) {
            if (userType.getName().equals(name)) {
                return userType;
            }
        }
        return null;
    }

}
