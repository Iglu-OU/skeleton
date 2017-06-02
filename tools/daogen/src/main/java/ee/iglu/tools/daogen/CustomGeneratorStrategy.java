package ee.iglu.tools.daogen;

import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;

public class CustomGeneratorStrategy extends DefaultGeneratorStrategy {

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        String javaClassName = super.getJavaClassName(definition, mode);

        if (mode == Mode.POJO) {
            return javaClassName + "Row";
        }

        return javaClassName;
    }
}
