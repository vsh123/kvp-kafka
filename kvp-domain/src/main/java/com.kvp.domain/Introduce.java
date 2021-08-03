package com.kvp.domain;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Introduce {
    private String name;
    private Long age;

    public void addAge() {
        age += 10;
    }

    public void masking() {
        if(Objects.isNull(name) || name.length() <= 1) {
            return;
        }
        int length = name.length();
        StringBuilder builder = new StringBuilder();
        builder.append(name.charAt(0));
        for (int i = 0; i < name.length() - 2; i++) {
            builder.append("*");
        }
        builder.append(name.charAt(length - 1));
        this.name = builder.toString();
    }
}
