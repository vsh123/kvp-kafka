package com.kvp.domain.step3.grade;

public class Bronze implements Grade {
    public static final long HIGHER_LIMIT = 100_000L;

    @Override
    public String getName() {
        return "Bronze";
    }

    @Override
    public boolean isInGrade(Long purchasePrice) {
        return purchasePrice <= HIGHER_LIMIT;
    }
}
