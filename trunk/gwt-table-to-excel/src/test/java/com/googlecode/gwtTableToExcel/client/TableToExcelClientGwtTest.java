package com.googlecode.gwtTableToExcel.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;

public class TableToExcelClientGwtTest extends GWTTestCase {

	@Override
	public String getModuleName() {
		return "com.googlecode.gwtTableToExcel.gwtTableToExcel";
	}

	public void testConstructor() throws Exception {
		TableToExcelClient tableToExcelClient = new TableToExcelClient(new FlexTable());
		FormPanel exportFormWidget = tableToExcelClient.getExportFormWidget();
		{
			assertNotNull(exportFormWidget);
		}
	}

	public void testBuilder() throws Exception {
		FormPanel exportFormWidget = TableToExcelClientBuilder.fromTable(new FlexTable()).buildExportFormWidget();
		{
			assertNotNull(exportFormWidget);
		}
	}

}
