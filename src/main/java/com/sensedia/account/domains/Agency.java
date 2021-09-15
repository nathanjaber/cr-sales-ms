package com.sensedia.account.domains;

import org.apache.commons.lang3.RandomUtils;

public enum Agency {
    AGENCY1("1056"),
    AGENCY2("1057"),
    AGENCY3("1058");

    private final String number;

    Agency(String number) {
        this.number = number;
    }

    public static String randomAgency() {
        return Agency.values()[RandomUtils.nextInt(0, Agency.values().length - 1)].number;
    }
}
