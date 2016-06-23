package ar.com.oxen.nibiru.mobile.wp.app;

import org.timepedia.exporter.client.ExporterUtil;

import com.google.gwt.core.client.EntryPoint;

public class GwtEntryPoint implements EntryPoint {
	@Override
	public void onModuleLoad() {
		ExporterUtil.exportAll();
	}
}
