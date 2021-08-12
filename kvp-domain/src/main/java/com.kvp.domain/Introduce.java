package com.kvp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Introduce {
    private String name;
    private Long age;
    private ComputerLanguage computerLanguage;
    private int annual;

    public boolean isSenior() {
        return annual > 3;
    }

    public boolean isJunior() {
        return !isSenior();
    }

    public boolean isJavaEngineer() {
        return Objects.equals(computerLanguage, ComputerLanguage.JAVA);
    }
}
