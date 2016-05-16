package ar.com.oxen.nibiru.mobile.aaj.ui.mvp;

import javax.inject.Provider;

import com.aajtech.ui.core.api.ClickHandler;
import com.aajtech.ui.core.api.Container;
import com.aajtech.ui.core.api.VerticalPanel;
import com.aajtech.ui.core.api.Widget;
import com.aajtech.ui.core.impl.builder.ButtonBuilder;
import com.aajtech.ui.core.impl.builder.HorizontalPanelBuilder;
import com.aajtech.ui.core.impl.builder.VerticalPanelBuilder;

import ar.com.oxen.nibiru.mobile.core.api.ui.mvp.View;

public abstract class MenuViewDecorator implements View {
	private final Widget navigationWidget;
	private final Container titleContainer;
	private final Container contentContainer;

	protected MenuViewDecorator(Iterable<MenuItem> items,
			Provider<ButtonBuilder> button,
			Provider<HorizontalPanelBuilder> horizontalPanel,
			Provider<VerticalPanelBuilder> verticalPanel) {
		VerticalPanel menu;
		navigationWidget = verticalPanel.get()
				.add(titleContainer = verticalPanel.get().build())
				.add(horizontalPanel.get()
						.add(menu = verticalPanel.get().build())
						.add(contentContainer = verticalPanel.get().build())
						.build())
				.build();
		
		for (final MenuItem item :items) {
			menu.add(button.get().build(item.getTitle(), new ClickHandler() {
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
