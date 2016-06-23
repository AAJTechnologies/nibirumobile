package ar.com.oxen.nibiru.mobile.vaadin.app;

import static java.util.Objects.requireNonNull;

import javax.inject.Inject;
import javax.inject.Provider;

import com.vaadin.server.ServiceException;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinServlet;

public class NibiruServlet extends VaadinServlet implements SessionInitListener {
	private final Provider<UIProvider> uiProvider;

	@Inject
	public NibiruServlet(Provider<UIProvider> uiProvider) {
		this.uiProvider = requireNonNull(uiProvider);
	}

	@Override
	protected void servletInitialized() {
		getService().addSessionInitListener(this);
	}

	@Override
	public void sessionInit(SessionInitEvent event) throws ServiceException {
		event.getSession().addUIProvider(uiProvider.get());
	}
}
