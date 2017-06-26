package ee.iglu.lib.namer.test;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AllArgsConstructorSample {
    private final char workWithChars;
    private final int anInt;
    private final Integer firstNumber;
    private final String secondString;
    private final Object anotherProperty;
}
