package com.kvp.domain.step3.grade;

import java.util.Arrays;
import java.util.List;

public class GradeDecider {
    private static final List<Grade> GRADES = Arrays.asList(
            new Bronze(), new Silver(), new Gold(), new Platinum(), new Vip()
    );

    public static String decideByPurchasePrice(Long purchasePrice) {
        return GRADES.stream()
                .filter(grade -> grade.isInGrade(purchasePrice))
                .findFirst()
                .map(Grade::getName)
                .orElseThrow(() -> new IllegalArgumentException("구입 금액을 확인해 주세요."));
    }
}
