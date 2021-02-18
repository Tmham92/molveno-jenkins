package com.capgemini.molveno.model;

import org.springframework.context.i18n.LocaleContextHolder;

public enum RoomType {
    SINGLE("Single", "單"),
    DOUBLE_SINGLES("Double with single beds", "雙人床單人床"),
    DOUBLE("Double", "雙"),
    FAMILY("Family", "家庭"),
    PENTHOUSE("Penthouse", "頂層公寓");

    private final String displayValueEN;
    private final String displayValueZH;

    RoomType(String displayValueEN, String displayValueZH) {
        this.displayValueEN = displayValueEN;
        this.displayValueZH = displayValueZH;
    }

    public String getDisplayValue() {
        if (LocaleContextHolder.getLocale().getLanguage().equalsIgnoreCase("en"))
            return displayValueEN;
        else return displayValueZH;
    }
}
