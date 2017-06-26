package ee.iglu.skeleton.rpc.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.Instant;

@Component
class CustomJacksonModule extends SimpleModule {

	@PostConstruct
	void init() {
		addSerializer(Instant.class, new InstantSerializer());
	}

	private static class InstantSerializer extends JsonSerializer<Instant>{
		@Override
		public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws
				IOException,
				JsonProcessingException {

			gen.writeNumber(value.toEpochMilli());
		}
	}

}
