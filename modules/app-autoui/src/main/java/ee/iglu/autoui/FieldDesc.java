package ee.iglu.autoui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class FieldDesc {
    private final String name;
    private final String type;
    private final String view;
    private final boolean required;
}
