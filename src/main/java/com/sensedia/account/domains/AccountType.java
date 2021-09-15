package com.sensedia.account.domains;

import com.sensedia.commons.errors.exceptions.BadRequestException;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public enum AccountType {
    CA,
    SA;

    public static AccountType fromValue(String value) {
        if (StringUtils.isBlank(value)) return null;

        for (AccountType accountType : AccountType.values()) {
            if (accountType.name().equalsIgnoreCase(value)) {
                return accountType;
            }
        }

        throw new BadRequestException(
                "Invalid account type [" + value + "]");
    }

    public static AccountType randomAccountType() {
        Random random = new Random();
        return AccountType.values()[
                random.ints(0,
                                AccountType.values().length - 1)
                        .findFirst()
                        .getAsInt()];
    }
}
