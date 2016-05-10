package ar.com.oxen.nibiru.mobile.aaj.ui.mvp;

import static com.aajtech.ui.core.impl.builder.ButtonBuilder.button;
import static com.aajtech.ui.core.impl.builder.HorizontalPanelBuilder.horizontalPanel;
import static com.aajtech.ui.core.impl.builder.VerticalPanelBuilder.verticalPanel;

import com.aajtech.ui.core.api.ClickHandler;
import com.aajtech.ui.core.api.Container;
import com.aajtech.ui.core.api.VerticalPanel;
import com.aajtech.ui.core.api.Widget;

import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.View;

public abstract class MenuViewDecorator implements View {
	private final Widget navigationWidget;
	private final Container titleContainer;
	private final Container contentContainer;

	protected MenuViewDecorator(Iterable<MenuItem> items) {
		VerticalPanel menu;
		navigationWidget = verticalPanel()
				.add(titleContainer = verticalPanel().build())
				.add(horizontalPanel()
						.add(menu = verticalPanel().build())
						.add(contentContainer = verticalPanel().build())
						.build())
				.build();
		
		for (final MenuItem item :items) {
			menu.add(button(item.getTitle(), new ClickHandler() {
				@Override
				public void onClick() {
					item.getPlace().go(true, false);
				}
			}));
		}
	}

	@Override
	public Object asNative() {
		return navigationWidget.asNative();
	}

	protected void setTitle(Widget title) {
		titleContainer.clear();
		titleContainer.add(title);
	}

	protected void setContent(Widget content) {
		contentContainer.clear();
		contentContainer.add(content);
	}
}
