package com.googlecode.gwtTableToExcel.demo.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtTableToExcel.client.TableToExcelClientBuilder;

public class UIBinderDemo extends Composite {

	private static UIBinderDemoUiBinder uiBinder = GWT.create(UIBinderDemoUiBinder.class);

	interface UIBinderDemoUiBinder extends UiBinder<Widget, UIBinderDemo> {
	}

	@UiField(provided = true)
	FlexTable exportFlexTable = new FlexTable();

	@UiField
	SimplePanel exportPanel;

	@UiField(provided = true)
	FormPanel exportForm = TableToExcelClientBuilder.fromTable(exportFlexTable).buildExportFormWidget();

	public UIBinderDemo() {
		initWidget(uiBinder.createAndBindUi(this));
		exportPanel.setWidget(TableToExcelClientBuilder.fromTable(exportFlexTable).buildExportFormWidget());
	}

	public FlexTable getExportFlexTable() {
		return exportFlexTable;
	}
}
