package com.googlecode.gwtTableToExcel.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Creates a {@link TableToExcelClient} with various fliuent options
 * 
 * @author Francois Wauquier 'wokier'
 */
public class TableToExcelClientBuilder {

	private Element tableElement;
	private Widget exportWidget = new Label("Export");
	private String fileName = "export";

	private TableToExcelClientBuilder(Element tableElement) {
		super();
		this.tableElement = tableElement;
	}

	/**
	 * table
	 * 
	 * @param htmlTable
	 * @return
	 */
	public static TableToExcelClientBuilder fromTable(HTMLTable htmlTable) {
		return new TableToExcelClientBuilder(htmlTable.getElement());
	}

	/**
	 * table
	 * 
	 * @param cellTable
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static TableToExcelClientBuilder fromTable(CellTable cellTable) {
		return new TableToExcelClientBuilder(cellTable.getElement());
	}

	/**
	 * table
	 * 
	 * @param tableElement
	 * @return
	 */
	public static TableToExcelClientBuilder fromTable(TableElement tableElement) {
		return new TableToExcelClientBuilder(tableElement);
	}

	/**
	 * table
	 * 
	 * @param flexTable
	 * @return
	 */
	public static TableToExcelClientBuilder fromTable(FlexTable flexTable) {
		return new TableToExcelClientBuilder(flexTable.getElement());
	}

	/**
	 * exportWidget
	 * 
	 * @param labelText
	 * @return
	 */
	public TableToExcelClientBuilder withLabel(String labelText) {
		this.exportWidget = new Label(labelText);
		return this;
	}

	/**
	 * exportWidget
	 * 
	 * @param exportWidget
	 * @return
	 */
	public TableToExcelClientBuilder withClickable(HasClickHandlers exportWidget) {
		this.exportWidget = (Widget) exportWidget;
		return this;
	}

	/**
	 * exportWidget
	 * 
	 * @param exportWidget
	 * @return
	 */
	public TableToExcelClientBuilder withWidget(Widget exportWidget) {
		this.exportWidget = exportWidget;
		return this;
	}

	/**
	 * fileName
	 * 
	 * @param fileName
	 * @return
	 */
	public TableToExcelClientBuilder toFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	protected TableToExcelClient build() {
		return new TableToExcelClient(tableElement, exportWidget, fileName);
	}

	/**
	 * Builds the final result to place in your view<br>
	 * see {@link TableToExcelClient#getExportWidget()}
	 * 
	 * @return
	 */
	public FormPanel buildExportFormWidget() {
		return build().getExportFormWidget();
	}

}
