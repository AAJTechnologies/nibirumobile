package org.nibiru.mobile.java.serializer.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.nibiru.mobile.core.api.serializer.Serializer;

import java.io.IOException;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

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
		} catch (JsonProcessingException e) {
			throw exception(object, e);
		}
	}

	@Override
	public <T> T deserialize(String data, Class<T> returnType) {
		checkNotNull(data);
		checkNotNull(returnType);
		try {
			return mapper.readValue(data, returnType);
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
