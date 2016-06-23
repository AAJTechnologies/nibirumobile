package ar.com.oxen.nibiru.mobile.gwt.serializer;

import static com.google.common.base.Preconditions.checkNotNull;
import ar.com.oxen.nibiru.mobile.core.api.serializer.Serializer;

import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

public class AutoBeanSerializer implements Serializer {
	private final AutoBeanFactory autoBeanFactory;

	public AutoBeanSerializer(AutoBeanFactory autoBeanFactory) {
		this.autoBeanFactory = checkNotNull(autoBeanFactory);
	}

	@Override
	public String serialize(Object object) {
		checkNotNull(object);
		return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(object))
				.getPayload();
	}

	@Override
	public <T> T deserialize(String data, Class<T> returnType) {
		checkNotNull(data);
		checkNotNull(returnType);
		if (data != null && !data.trim().equalsIgnoreCase("null")) {
			return AutoBeanCodex.decode(autoBeanFactory, returnType, data)
					.as();
		} else {
			return null;
		}
	}

	@Override
	public String getEncoding() {
		return "json";
	}
}
