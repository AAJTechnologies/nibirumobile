package org.nibiru.mobile.ios.ui.mvp;

import apple.uikit.UITextField;
import apple.uikit.protocol.UITextFieldDelegate;

public class TextFieldDelegate implements UITextFieldDelegate {
	@Override
	public boolean textFieldShouldReturn(UITextField textField) {
		return textField.resignFirstResponder();
	}
}