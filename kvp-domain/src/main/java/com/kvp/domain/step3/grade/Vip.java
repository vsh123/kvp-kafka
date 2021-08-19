package com.kvp.domain.step3.grade;

public class Vip implements Grade {
    public static final long LOWER_LIMIT = 1_000_000L;

    @Override
    public String getName() {
        return "Vip";
    }

    @Override
    public boolean isInGrade(Long purchasePrice) {
        return purchasePrice > LOWER_LIMIT;
    }
}
