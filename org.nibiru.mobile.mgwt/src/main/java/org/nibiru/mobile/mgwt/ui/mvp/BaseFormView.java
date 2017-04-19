package org.nibiru.mobile.mgwt.ui.mvp;

import com.google.gwt.user.client.ui.HasWidgets;
import com.googlecode.mgwt.ui.client.widget.form.Form;
import com.googlecode.mgwt.ui.client.widget.panel.scroll.ScrollPanel;

import org.nibiru.mobile.gwt.ui.mvp.BaseGwtView;

public abstract class BaseFormView extends BaseGwtView {
	private final Form formPanel;

	public BaseFormView() {
		ScrollPanel scrollPanel = new ScrollPanel();
		initWidget(scrollPanel);

		formPanel = new Form();
		scrollPanel.add(formPanel);
	}

	protected HasWidgets getFormPanel() {
		return formPanel;
	}
}
