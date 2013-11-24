package com.googlecode.gwtTableToExcel.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Build a Form Panel in order to allow to download the table content in an excel format.<br>
 * Dont forget to add an iframe in your original html file :<br>
 * < iframe src="javascript:''" id="gwt-table-to-excel-target" class="invisible">< /iframe><br>
 * and to declare the sevlet in your web.xml :<br>
 * 
 * @see com.googlecode.gwtTableToExcel.server.TableToExcelServlet
 * @author Francois Wauquier 'wokier'
 */
public class TableToExcelClient {

	private final FormPanel formPanel = new FormPanel("gwt-table-to-excel-target");

	/**
	 * Simple Constructor
	 * 
	 * @param table
	 * @deprecated use {@link TableToExcelClientBuilder}
	 */
	@Deprecated
	public TableToExcelClient(final HTMLTable table) {
		this(table, new Label("Export"), "export");
	}

	/**
	 * Simple Constructor
	 * 
	 * @param table
	 * @deprecated use {@link TableToExcelClientBuilder}
	 */
	@Deprecated
	@SuppressWarnings("rawtypes")
	public TableToExcelClient(final CellTable table) {
		this(table.getElement(), new Label("Export"), "export");
	}

	/**
	 * Simple Constructor
	 * 
	 * @param tableDomElement
	 * @deprecated use {@link TableToExcelClientBuilder}
	 */
	@Deprecated
	public TableToExcelClient(TableElement tableDomElement) {
		this(tableDomElement, new Label("Export"), "export");
	}

	/**
	 * Constructor with defined label text
	 * 
	 * @param table
	 * @param labelText
	 * @deprecated use {@link TableToExcelClientBuilder}
	 */
	@Deprecated
	public TableToExcelClient(final HTMLTable table, String labelText) {
		this(table.getElement(), new Label(labelText), "export");
	}

	/**
	 * Constructor with defined label text
	 * 
	 * @param table
	 * @param labelText
	 * @deprecated use {@link TableToExcelClientBuilder}
	 */
	@Deprecated
	@SuppressWarnings("rawtypes")
	public TableToExcelClient(final CellTable table, String labelText) {
		this(table.getElement(), new Label(labelText), "export");
	}

	/**
	 * Constructor with defined label text
	 * 
	 * @param tableElement
	 * @param labelText
	 * @deprecated use TableToExcelClientBuilder
	 */
	@Deprecated
	public TableToExcelClient(final TableElement tableElement, String labelText) {
		this(tableElement, new Label(labelText), "export");
	}

	/**
	 * Constructor with defined label text and fileName
	 * 
	 * @param table
	 * @param labelText
	 * @param fileName
	 * @deprecated use {@link TableToExcelClient#TableToExcelClient(HTMLTable, Widget, String)} with Label as widget or {@link TableToExcelClientBuilder}
	 */
	@Deprecated
	protected TableToExcelClient(final HTMLTable table, String labelText, String fileName) {
		this(table.getElement(), new Label(labelText), fileName);
	}

	/**
	 * Constructor with defined label text and fileName
	 * 
	 * @param table
	 * @param labelText
	 * @param fileName
	 * @deprecated use {@link TableToExcelClient#TableToExcelClient(CellTable, Widget, String)} with Label as widget or {@link TableToExcelClientBuilder}
	 */
	@Deprecated
	@SuppressWarnings("rawtypes")
	protected TableToExcelClient(final CellTable table, String labelText, String fileName) {
		this(table.getElement(), new Label(labelText), fileName);
	}

	/**
	 * Constructor with other widget (ex button)
	 * 
	 * @param table
	 * @param exportWidget
	 * @deprecated use {@link TableToExcelClientBuilder}
	 */
	@Deprecated
	public TableToExcelClient(final HTMLTable table, HasClickHandlers exportWidget) {
		this(table.getElement(), (Widget) exportWidget, "export");
	}

	/**
	 * Constructor with other widget (ex button)
	 * 
	 * @param table
	 * @param exportWidget
	 * @deprecated use {@link TableToExcelClientBuilder}
	 */
	@Deprecated
	@SuppressWarnings("rawtypes")
	public TableToExcelClient(final CellTable table, HasClickHandlers exportWidget) {
		this(table.getElement(), (Widget) exportWidget, "export");
	}

	/**
	 * Constructor with other widget (ex button)
	 * 
	 * @param tableElement
	 * @param exportWidget
	 * @deprecated use {@link TableToExcelClientBuilder}
	 */
	@Deprecated
	public TableToExcelClient(final TableElement tableElement, HasClickHandlers exportWidget) {
		this(tableElement, (Widget) exportWidget, "export");
	}

	/**
	 * Constructor with full options
	 * 
	 * @param table
	 * @param labelText
	 * @param fileName
	 * @deprecated use {@link TableToExcelClientBuilder}
	 */
	@Deprecated
	public TableToExcelClient(HTMLTable table, Widget exportWidget, String fileName) {
		this(table.getElement(), exportWidget, fileName);
	}

	/**
	 * Constructor with full options
	 * 
	 * @param table
	 * @param labelText
	 * @param fileName
	 * @deprecated use {@link TableToExcelClientBuilder}
	 */
	@Deprecated
	@SuppressWarnings("rawtypes")
	public TableToExcelClient(CellTable table, Widget exportWidget, String fileName) {
		this(table.getElement(), exportWidget, fileName);
	}

	protected TableToExcelClient(final com.google.gwt.dom.client.Element tableElement, Widget exportWidget, String fileName) {
		formPanel.setAction(GWT.getModuleBaseURL() + "excel");
		// formPanel.setEncoding(FormPanel.ENCODING_MULTIPART);
		formPanel.setMethod(FormPanel.METHOD_POST);
		formPanel.addStyleName("gwt-table-to-excel-form");
		FlowPanel flowPanel = new FlowPanel();
		flowPanel.addStyleName("gwt-table-to-excel-panel");
		formPanel.setWidget(flowPanel);
		final Hidden contentHidden = new Hidden("html");
		flowPanel.add(contentHidden);
		final Hidden fileNameHidden = new Hidden("fileName", fileName);
		flowPanel.add(fileNameHidden);
		flowPanel.add(exportWidget);
		exportWidget.addStyleName("gwt-table-to-excel-exportWidget");
		((HasClickHandlers) exportWidget).addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// lazy copy
				contentHidden.setValue(tableElement.getString());
				formPanel.submit();
			}
		});
	}

	/**
	 * Give the created form
	 * 
	 * @return
	 * @deprecated use {@link #getExportWidget()}
	 */
	@Deprecated
	protected FormPanel build() {
		return formPanel;
	}

	/**
	 * Give the created form widget
	 * 
	 * @return
	 * @deprecated use {@link #getExportFormWidget()}
	 */
	@Deprecated
	public FormPanel getExportWidget() {
		return formPanel;
	}

	/**
	 * Give the created form widget
	 * 
	 * @return
	 */
	public FormPanel getExportFormWidget() {
		return formPanel;
	}

}
