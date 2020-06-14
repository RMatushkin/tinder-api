package com.github.tinder.api.util;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Collections.shuffle;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class InstallIdUtil {
    private static final String ALLOWED_SYMBOLS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 11;

    public static void main(String[] args) {
        System.out.println(generateInstallId());
    }

    public static String generateInstallId() {
        List<String> symbols = shuffleSymbols();
        StringBuilder installId = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(symbols.size());
            installId.append(symbols.get(index));
        }
        return installId.toString();
    }

    private static List<String> shuffleSymbols() {
        char[] chars = ALLOWED_SYMBOLS.toCharArray();
        List<String> symbols = new ArrayList<>(chars.length);
        for (char ch : chars) {
            symbols.add(String.valueOf(ch));
        }
        shuffle(symbols);
        return symbols;
    }
}
