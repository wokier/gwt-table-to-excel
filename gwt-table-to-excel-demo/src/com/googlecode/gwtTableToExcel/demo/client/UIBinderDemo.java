package com.googlecode.gwtTableToExcel.demo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtTableToExcel.client.TableToExcelClient;

public class UIBinderDemo extends Composite {

	private static UIBinderDemoUiBinder uiBinder = GWT.create(UIBinderDemoUiBinder.class);

	interface UIBinderDemoUiBinder extends UiBinder<Widget, UIBinderDemo> {
	}

	@UiField
	FlexTable exportFlexTable;

	@UiField
	SimplePanel exportPanel;

	public UIBinderDemo() {
		initWidget(uiBinder.createAndBindUi(this));
		exportPanel.setWidget(new TableToExcelClient(exportFlexTable).build());
	}
	
	public FlexTable getExportFlexTable() {
		return exportFlexTable;
	}
}
