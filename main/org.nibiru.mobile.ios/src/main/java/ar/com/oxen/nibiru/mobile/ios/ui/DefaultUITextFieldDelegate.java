package ar.com.oxen.nibiru.mobile.ios.ui;

import org.apache.commons.logging.LogFactory;
import org.robovm.apple.uikit.UITextField;
import org.robovm.apple.uikit.UITextFieldDelegateAdapter;

public class DefaultUITextFieldDelegate extends UITextFieldDelegateAdapter {
	@Override
	public boolean shouldReturn(UITextField textField) {
		LogFactory.getLog("MATANGA").warn("shouldReturn "+textField.getText());
		textField.resignFirstResponder();
		return true;
	}
}
