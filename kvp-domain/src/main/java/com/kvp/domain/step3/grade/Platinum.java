package com.kvp.domain.step3.grade;

public class Platinum implements Grade {
    public static final long LOWER_LIMIT = 500_000L;
    public static final long HIGHER_LIMIT = 1_000_000L;

    @Override
    public String getName() {
        return "Platinum";
    }

    @Override
    public boolean isInGrade(Long purchasePrice) {
        return purchasePrice > LOWER_LIMIT && purchasePrice <= HIGHER_LIMIT;
    }
}
