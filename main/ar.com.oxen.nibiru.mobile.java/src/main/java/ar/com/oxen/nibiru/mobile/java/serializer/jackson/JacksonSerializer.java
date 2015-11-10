package ar.com.oxen.nibiru.mobile.java.serializer.jackson;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import javax.inject.Inject;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import ar.com.oxen.nibiru.mobile.core.api.serializer.Serializer;

public class JacksonSerializer implements Serializer {
	private final ObjectMapper mapper;

	@Inject
	public JacksonSerializer(ObjectMapper mapper) {
		this.mapper = checkNotNull(mapper);
	}

	@Override
	public String serialize(Object object) {
		checkNotNull(object);
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			throw exception(object, e);
		} catch (JsonMappingException e) {
			throw exception(object, e);
		} catch (IOException e) {
			throw exception(object, e);
		}
	}

	@Override
	public <T> T deserialize(String data, Class<T> returnType) {
		checkNotNull(data);
		checkNotNull(returnType);
		try {
			return mapper.readValue(data, returnType);
		} catch (JsonParseException e) {
			throw exception(data, e);
		} catch (JsonMappingException e) {
			throw exception(data, e);
		} catch (IOException e) {
			throw exception(data, e);
		}
	}

	@Override
	public String getEncoding() {
		return "json";
	}

	private RuntimeException exception(Object argument, Exception cause) {
		return new IllegalArgumentException("Invalid object/json: " + argument,
				cause);
	}
}
