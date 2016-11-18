package com.ggsoft.poliglot.utils;

import java.util.ResourceBundle;

/**
 * contains all search types for possible foreign words or sentences
 */
public enum WordSearchTypes {
   F,
   D,
   V,
   TLA,
   TLD,
   TCA,
   TCD;

    /** Resources for the default locale */
    private static final ResourceBundle res =
            ResourceBundle.getBundle("com.example.Searchmessages");

    /** @return the locale-dependent message */
    public String toString() {
        return res.getString(name() + ".string");
    }
}

