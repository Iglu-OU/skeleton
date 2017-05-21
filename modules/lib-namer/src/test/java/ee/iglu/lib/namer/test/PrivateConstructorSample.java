package ee.iglu.lib.namer.test;

import lombok.Getter;

@Getter
public class PrivateConstructorSample {
    private final String finalField;

    private PrivateConstructorSample(String finalField) {
        this.finalField = finalField;
    }
}
