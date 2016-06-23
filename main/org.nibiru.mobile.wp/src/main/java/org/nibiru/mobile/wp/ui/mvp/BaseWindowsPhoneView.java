package org.nibiru.mobile.wp.ui.mvp;

import org.nibiru.mobile.core.api.ui.mvp.View;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Base class for Windows Phone views.
 */
public abstract class BaseWindowsPhoneView implements View {
	protected Label label(String id) {
		return Label.wrap(element(id));
	}

	protected Button button(String id) {
		return Button.wrap(element(id));
	}

	protected Image image(String id) {
		return Image.wrap(element(id));
	}

	protected TextBox textBox(String id) {
		return TextBox.wrap(element(id));
	}

	protected Element element(String id) {
		return Document.get().getElementById(id);
	}

	@Override
	public Object asNative() {
		// Not used by WindowsPhone implementation
		return null;
	}
}
