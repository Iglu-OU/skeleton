package ee.iglu.lib.namer.test;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class PublicFinalBuilderSample {
    private final char workWithChars;
    private final int anInt;
    private final Integer firstNumber;
    private final String secondString;
    private final Object anotherProperty;
}
