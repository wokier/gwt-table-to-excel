package com.googlecode.gwtTableToExcel.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
/**
 * Build a Form Panel in order to allow to download the table content in an excel format.<br>
 * Dont forget to add an iframe in your original html file :<br>
 * < iframe src="javascript:''" id="gwt-table-to-excel-target" class="invisible">< /iframe><br>
 * and to declare the sevlet in your web.xml :<br>
 * @see com.googlecode.gwtTableToExcel.server.TableToExcelServlet
 * @author Francois Wauquier 'wokier'
 */
public class TableToExcelClient {

	private final FormPanel formPanel = new FormPanel("gwt-table-to-excel-target");

	/**
	 * Simple Constructor
	 * @param table
	 */
	public TableToExcelClient(final HTMLTable table) {
		this(table,"Export","export");
	}
	
	/**
	 * Constructor with defined label text
	 * @param table
	 * @param labelText
	 */
	public TableToExcelClient(final HTMLTable table, String labelText) {
		this(table,labelText,"export");
	}
	
	/**
	 * Constructor with defined label text and excel fileName  
	 * @param table
	 * @param labelText
	 * @param fileName
	 */
	public TableToExcelClient(final HTMLTable table, String labelText, String fileName) {
		formPanel.setAction(GWT.getModuleBaseURL()+"excel");
//		formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setMethod(FormPanel.METHOD_POST);
		formPanel.addStyleName("gwt-table-to-excel-form");
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.addStyleName("gwt-table-to-excel-panel");
		formPanel.setWidget(verticalPanel);
		final Hidden contentHidden = new Hidden("html");
		verticalPanel.add(contentHidden);
		final Hidden fileNameHidden = new Hidden("fileName", fileName);
		verticalPanel.add(fileNameHidden);
		Label action = new Label(labelText);
		verticalPanel.add(action);
		action.addStyleName("gwt-table-to-excel-label");
		action.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				//lazy copy
				contentHidden.setValue(table.getElement().getString());
				formPanel.submit();
			}
		});
	}
	
	/**
	 * Give the created form
	 * @return
	 */
	public FormPanel build() {
		return formPanel;
	}
	
}
