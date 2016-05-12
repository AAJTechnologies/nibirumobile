package org.nibiru.mobile.ios.ui.mvp;

import ios.uikit.UITextField;
import ios.uikit.protocol.UITextFieldDelegate;

public class TextFieldDelegate implements UITextFieldDelegate {
	@Override
	public boolean textFieldShouldReturn(UITextField textField) {
		return textField.resignFirstResponder();
	}
}