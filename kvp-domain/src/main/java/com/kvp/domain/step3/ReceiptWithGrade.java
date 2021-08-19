package com.kvp.domain.step3;

import com.kvp.domain.step3.grade.GradeDecider;
import lombok.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReceiptWithGrade {
    private String name;
    private Long purchasePrice;
    private Long totalPurchasePrice;
    private String grade;

    public static ReceiptWithGrade of(Receipt receipt, Long totalPurchasePrice) {
        String grade = GradeDecider.decideByPurchasePrice(totalPurchasePrice);
        return new ReceiptWithGrade(receipt.getName(), receipt.getPurchasePrice(), totalPurchasePrice, grade);
    }

//    public enum Grade2 {
//        BRONZE(0L, 100_000L),
//        SILVER(100_000L, 200_000L),
//        GOLD(200_000L, 500_000L),
//        PLATINUM(500_000L, 1_000_000L),
//        VIP(1_000_000L, Long.MAX_VALUE);
//
//        private final Long min;
//        private final Long max;
//
//        Grade2(Long min, Long max) {
//            this.min = min;
//            this.max = max;
//        }
//
//        public static Grade2 getGrade(Long price) {
//            for (Grade2 grade : values()) {
//                if (price > grade.min && price <= grade.max) {
//                    return grade;
//                }
//            }
//            return BRONZE;
//        }
//    }
}
