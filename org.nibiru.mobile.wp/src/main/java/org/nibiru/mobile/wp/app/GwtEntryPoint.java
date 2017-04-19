package org.nibiru.mobile.wp.app;

import com.google.gwt.core.client.EntryPoint;

import org.timepedia.exporter.client.ExporterUtil;

public class GwtEntryPoint implements EntryPoint {
	@Override
	public void onModuleLoad() {
		ExporterUtil.exportAll();
	}
}
