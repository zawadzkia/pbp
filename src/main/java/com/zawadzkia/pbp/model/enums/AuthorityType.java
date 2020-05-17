package com.zawadzkia.pbp.model.enums;

public enum AuthorityType {
    ROLE_ADMIN("ROLE_ADMIN"),

    ROLE_USER("ROLE_USER");

    private String value;

    AuthorityType(String wartosc) {
        this.value = wartosc;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static AuthorityType fromValue(String text) {
        for (AuthorityType b : AuthorityType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }
}
