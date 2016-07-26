package com.dank.framework.util;

import com.dank.framework.Framework;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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

    @SideOnly(Side.CLIENT)
    public String translateMessage(String message) {
        if (this == NONE)
            return I18n.format(message);
        return I18n.format(String.format("%s.%s.%s", this.name, Framework.MOD_ID, message));
    }

}