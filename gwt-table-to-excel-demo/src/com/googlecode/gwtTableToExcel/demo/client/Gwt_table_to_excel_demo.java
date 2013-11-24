package com.googlecode.gwtTableToExcel.demo.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.PieChart;
import com.google.gwt.visualization.client.visualizations.PieChart.Options;
import com.google.gwt.visualization.client.visualizations.Table;
import com.googlecode.gwtTableToExcel.client.TableToExcelClientBuilder;

/**
 * @see EntryPoint
 */
public class Gwt_table_to_excel_demo implements EntryPoint {

	RootPanel content;

	public void onModuleLoad() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			public void execute() {
				doModuleLoad();
			}
		});

	}

	private void doModuleLoad() {
		content = RootPanel.get("content");

		content.getElement().setInnerHTML("");

		buildFlexTableExample();

		buildFlexTableWithButtonExample();

		buildCellTableExample();

		buildUIBinderExample();

		buildGwtVisualizationExample();
	}

	private void buildGwtVisualizationExample() {
		VisualizationUtils.loadVisualizationApi(new Runnable() {
			public void run() {
				content.add(new PieChart(createTable(), createPieChartOptions()));
				Table visualizationTable = new Table(createTable(), createTableOptions());
				content.add(visualizationTable);
				com.google.gwt.dom.client.TableElement tableElement = extractVisualizationTableElement(visualizationTable);
				content.add(TableToExcelClientBuilder.fromTable(tableElement).toFileName("gwt-visualization").buildExportFormWidget());
			}

		}, PieChart.PACKAGE, Table.PACKAGE);
	}

	private com.google.gwt.dom.client.TableElement extractVisualizationTableElement(Table visualizationTable) {
		if (!visualizationTable.isAttached()) {
			Window.alert("Add your Visualization Table first");
		}
		com.google.gwt.dom.client.TableElement tableElement = TableElement.as(visualizationTable.getElement().getElementsByTagName("table").getItem(0));
		return tableElement;
	}

	private void buildFlexTableExample() {
		FlexTable flexTable = new FlexTable();
		flexTable.addStyleName("flexTable");
		List<String> sentences = Arrays.asList("Here is a FlexTable", "You can Export it in Excel!", "You can get some formatting contained in the html", "But not those from the css");
		fillaTableWithSentences(flexTable, sentences);
		DOM.setStyleAttribute((Element) flexTable.getWidget(2, 8).getElement().getParentNode(), "background", "#016300");// green
		((Element) flexTable.getWidget(3, 5).getElement().getParentNode()).setClassName("red");

		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(flexTable);

		content.add(decoratorPanel);
		content.add(TableToExcelClientBuilder.fromTable(flexTable).buildExportFormWidget());
		content.add(new HTML("<br />"));
	}

	private void fillaTableWithSentences(FlexTable flexTable, List<String> sentences) {
		for (int i = 0; i < sentences.size(); i++) {
			String sentence = sentences.get(i);
			String[] words = sentence.split(" ");
			for (int j = 0; j < words.length; j++) {
				String word = words[j];
				flexTable.setWidget(i, j, new Label(word));
			}
		}
	}

	private void buildFlexTableWithButtonExample() {
		FlexTable flexTable = new FlexTable();
		flexTable.addStyleName("flexTable");
		List<String> sentences = Arrays.asList("Here is another FlexTable", "You can also use a button as export widget", "And include it in the table itself if you want", "like this",
				"Et pour les français, il faut des caractères accentués");
		fillaTableWithSentences(flexTable, sentences);

		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(flexTable);

		flexTable.setWidget(3, 9, TableToExcelClientBuilder.fromTable(flexTable).withWidget(new Button("Export Button")).buildExportFormWidget());

		content.add(decoratorPanel);
		content.add(new HTML("<br />"));
	}

	private void buildCellTableExample() {
		CellTable<String> cellTable = new CellTable<String>();
		cellTable.addStyleName("cellTable");
		cellTable.addColumn(new TextColumn<String>() {
			@Override
			public String getValue(String object) {
				return object;
			}
		}, new TextHeader("Cool"));
		List<String> sentences = Arrays.asList("It also works with the Cell Table", "Really, that works !", "Without any server-side rewrite");
		cellTable.setRowData(0, sentences);

		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(cellTable);

		content.add(decoratorPanel);
		content.add(TableToExcelClientBuilder.fromTable(cellTable).buildExportFormWidget());
		content.add(new HTML("<br />"));
	}

	private void buildUIBinderExample() {
		UIBinderDemo uiBinder = new UIBinderDemo();
		List<String> sentences = Arrays.asList("This table was built with UIBinder", "It uses a SimplePanel to place the export widget", "You can also provide it");
		fillaTableWithSentences(uiBinder.getExportFlexTable(), sentences);
		content.add(uiBinder);

	}

	private AbstractDataTable createTable() {
		DataTable data = DataTable.create();
		data.addColumn(ColumnType.STRING, "Task");
		data.addColumn(ColumnType.NUMBER, "Hours per Day");
		data.addRows(2);
		data.setValue(0, 0, "Work");
		data.setValue(0, 1, 16);
		data.setValue(1, 0, "Sleep");
		data.setValue(1, 1, 8);
		return data;
	}

	private Options createPieChartOptions() {
		Options options = Options.create();
		options.setWidth(400);
		options.setHeight(240);
		options.set3D(true);
		options.setTitle("My Daily Activities");
		return options;
	}

	private com.google.gwt.visualization.client.visualizations.Table.Options createTableOptions() {
		com.google.gwt.visualization.client.visualizations.Table.Options options = com.google.gwt.visualization.client.visualizations.Table.Options.create();
		// options.setShowRowNumber(true);
		return options;
	}
}
