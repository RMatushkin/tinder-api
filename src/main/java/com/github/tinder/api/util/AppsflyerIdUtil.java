package com.github.tinder.api.util;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class AppsflyerIdUtil {
    private static final String PREFIX_ALLOWED_SYMBOLS = "123456789";
    private static final String SUFFIX_ALLOWED_SYMBOLS = "0123456789";
    private static final long LEFT_PART_LENGTH = 13;
    private static final long RIGHT_PART_LENGTH = 19;
    private static final String SEPARATOR = "-";

    public static void main(String[] args) {
        System.out.println(generateAppsFlyerId());
    }

    public static String generateAppsFlyerId() {
        List<String> prefixSymbols = shuffleSymbols(PREFIX_ALLOWED_SYMBOLS);
        List<String> suffixSymbols = shuffleSymbols(SUFFIX_ALLOWED_SYMBOLS);
        Random random = new Random();
        int index = random.nextInt(prefixSymbols.size());
        StringBuilder appsflyerId = new StringBuilder(prefixSymbols.get(index));
        for (int i = 0; i < LEFT_PART_LENGTH - 1; i++) {
            index = random.nextInt(suffixSymbols.size());
            appsflyerId.append(suffixSymbols.get(index));
        }
        appsflyerId.append(SEPARATOR);
        prefixSymbols = shuffleSymbols(PREFIX_ALLOWED_SYMBOLS);
        suffixSymbols = shuffleSymbols(SUFFIX_ALLOWED_SYMBOLS);
        index = random.nextInt(prefixSymbols.size());
        appsflyerId.append(prefixSymbols.get(index));
        for (int i = 0; i < RIGHT_PART_LENGTH - 1; i++) {
            index = random.nextInt(suffixSymbols.size());
            appsflyerId.append(suffixSymbols.get(index));
        }
        return appsflyerId.toString();
    }

    private static List<String> shuffleSymbols(String symbols) {
        char[] chars = symbols.toCharArray();
        List<String> _symbols = new ArrayList<>(chars.length);
        for (char ch : chars) {
            _symbols.add(String.valueOf(ch));
        }
        shuffle(_symbols);
        return _symbols;
    }
}
