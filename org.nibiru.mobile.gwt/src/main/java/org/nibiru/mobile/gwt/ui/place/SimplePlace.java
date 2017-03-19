package org.nibiru.mobile.gwt.ui.place;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import javax.annotation.Nullable;

import org.nibiru.mobile.core.api.ui.place.Place;

import com.google.common.collect.Maps;
import com.google.gwt.http.client.URL;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceTokenizer;

public class SimplePlace extends com.google.gwt.place.shared.Place implements
		Place {
	private final String id;
	private final PlaceController placeController;
	private final Map<String, Object> parameters;
	private final int order;

	//TODO: No esta bueno que el ID sea nullable, pero en
	// org.nibiru.mobile.gwt.app.GwtPlacesBootstrap.onBootstrap()
	// le estoy pasando null. Lo mismo para el place controller.
	public SimplePlace(@Nullable String id, int order, @Nullable PlaceController placeController) {
		this.id = id;
		this.placeController = placeController;
		this.parameters = Maps.newHashMap();
		this.order = order;
	}

	@Override
	public String getId() {
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getParameter(String key) {
		checkNotNull(key);
		return (T) parameters.get(key);
	}

	@Override
	public <T> T getParameter(Enum<?> key) {
		checkNotNull(key);
		return getParameter(key.toString());
	}

	@Override
	public Place addParameter(String key, Object value) {
		checkNotNull(key);
		checkNotNull(value);
		parameters.put(key, value);
		return this;
	}

	@Override
	public Place addParameter(Enum<?> key, Object value) {
		checkNotNull(key);
		checkNotNull(value);
		return addParameter(key.toString(), value);
	}

	@Override
	public void go(boolean push, boolean animated) {
		placeController.goTo(this);
	}

	@Override
	public void go() {
		go(false, true);
	}

	public static class Tokenizer implements PlaceTokenizer<SimplePlace> {
		// TODO: No entiendo bien como es el tema de la tokenizacion. Por
		// ejemplo, no le estoy pasando los parametros, pero sin embargo
		// funciona igual...

		@Override
		public String getToken(SimplePlace place) {
			checkNotNull(place);
			StringBuilder sb = new StringBuilder();

			sb.append(place.getId());
			sb.append("/");
			sb.append(place.order);

			boolean first = true;
			for (Map.Entry<String, Object> entry : place.parameters.entrySet()) {
				// TODO por ahora, solo String en la URL
				if (entry.getValue() instanceof String) {
					if (first) {
						sb.append("/");
					} else {
						sb.append("&");
					}
					sb.append(entry.getKey());
					sb.append("=");
					sb.append(URL.encode(entry.getValue().toString()));
				}
			}

			return sb.toString();
		}

		@Override
		public SimplePlace getPlace(String token) {
			checkNotNull(token);
			String[] tokens = token.split("/");
			SimplePlace place = new SimplePlace(tokens[0],
					Integer.valueOf(tokens[1]), null);

			if (tokens.length > 2) {
				for (String argToken : tokens[2].split("&")) {
					String[] arg = argToken.split("=");
					place.addParameter(arg[0], URL.decode(arg[1]));
				}
			}

			return place;
		}
	}

	public boolean forwardFrom(SimplePlace other) {
		checkNotNull(other);
		return order > other.order;
	}
}
