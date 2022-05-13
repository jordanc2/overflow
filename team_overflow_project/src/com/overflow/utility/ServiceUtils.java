package com.overflow.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class ServiceUtils {

    static final int AUTHENTICATED_ID_LENGTH = 5;

    // do not instantiate
    private ServiceUtils() {}

    public static String generateAuthenticationId() {
        return RandomStringUtils.randomAlphabetic(AUTHENTICATED_ID_LENGTH);
    }
}
