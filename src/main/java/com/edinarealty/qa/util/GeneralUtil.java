package com.edinarealty.qa.util;

import org.apache.commons.lang3.RandomStringUtils;

public class GeneralUtil {

    public static String generatingRandomAlphabeticString() {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        return generatedString ;

    }

    public static String generatingRandomNumericString() {
        String generatedString = "+"+RandomStringUtils.randomNumeric(5);
        return generatedString ;

    }

}
