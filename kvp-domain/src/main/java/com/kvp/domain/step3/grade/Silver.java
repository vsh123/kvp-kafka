package com.kvp.domain.step3.grade;

public class Silver implements Grade {
    public static final long LOWER_LIMIT = 100_000L;
    public static final long HIGHER_LIMIT = 200_000L;

    @Override
    public String getName() {
        return "Silver";
    }

    @Override
    public boolean isInGrade(Long purchasePrice) {
        return purchasePrice > LOWER_LIMIT && purchasePrice <= HIGHER_LIMIT;
    }
}
