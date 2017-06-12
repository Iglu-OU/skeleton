package ee.iglu.skeleton.greeter.impl;

import com.google.common.collect.ImmutableMap;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.StringLoader;
import ee.iglu.skeleton.greeter.Greeter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;

@Component
@RequiredArgsConstructor
class PebbleGreeter implements Greeter {

    // implementation-scope dependency, not visible to dependants
    private final PebbleEngine engine = createEngine();

    private static PebbleEngine createEngine() {
        return new PebbleEngine.Builder()
                .loader(new StringLoader())
                .build();
    }

    @Override
    public String hello(String name) {
        StringWriter stringWriter = new StringWriter();

        try {
            engine.getTemplate("Hello, {{name}} from Pebble!")
                    .evaluate(stringWriter, ImmutableMap.of("name", name));
        } catch (PebbleException | IOException e) {
            throw new RuntimeException(e);
        }

        return stringWriter.toString();
    }

}
