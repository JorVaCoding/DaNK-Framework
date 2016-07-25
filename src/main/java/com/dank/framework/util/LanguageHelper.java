package com.dank.framework.util;

import com.dank.thermalcontraction.ModInfo;
import net.minecraft.client.resources.I18n;

public enum LanguageHelper {
    MESSAGE("message"),
    LABEL("label"),
    BLOCK("tile"),
    ITEMGROUP("itemGroup"),
    ITEM("item"),
    DESCRIPTION("description"),
    JEI("jei"),
    RESOURCE("resource"),
    TYPE("type"),
    NONE(""),;

    private String name;

    LanguageHelper(String name) {
        this.name = name;
    }

    public String translateMessage(String message) {
        if (this == NONE)
            return I18n.format(message);
        return I18n.format(String.format("%s.%s.%s", this.name, ModInfo.MOD_ID, message));
    }

}