package com.googlecode.gwtTableToExcel.demo.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtTableToExcel.client.TableToExcelClient;
import com.googlecode.gwtTableToExcel.demo.client.rpc.GreetingService;
import com.googlecode.gwtTableToExcel.demo.client.rpc.GreetingServiceAsync;

/**
 * @see EntryPoint
 */
public class Gwt_table_to_excel_demo implements EntryPoint {

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	RootPanel content = RootPanel.get("content");
	
	public void onModuleLoad() {
		Scheduler.get().scheduleDeferred(new Command() {
			public void execute() {
				doModuleLoad();
			}
		});
	}

	private void doModuleLoad() {
		content.getElement().setInnerHTML("");
		
		buildFlexTableExample();
		
		buildFlexTableWithButtonExample();
		
		buildCellTableExample();
		
		buildUIBinderExample();
	}

	private void buildFlexTableExample() {
		FlexTable flexTable = new FlexTable();
		flexTable.addStyleName("flexTable");
		List<String> sentences = Arrays.asList("Here is a FlexTable",
				"You can Export it in Excel!",
				"You can get some formatting contained in the html",
				"But not those from the css");
		fillaTableWithSentences(flexTable, sentences);
		DOM.setStyleAttribute((Element)flexTable.getWidget(2, 8).getElement().getParentNode(), "background", "#016300");//green
		((Element)flexTable.getWidget(3, 5).getElement().getParentNode()).addClassName("red");
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(flexTable);
		
		TableToExcelClient tableToExcelClient = new TableToExcelClient(flexTable);
		content.add(decoratorPanel);
		content.add(tableToExcelClient.build());
		content.add(new HTML("<br />"));
	}

	private void fillaTableWithSentences(FlexTable flexTable,
			List<String> sentences) {
		for (int i = 0; i < sentences.size(); i++) {
			String sentence = sentences.get(i);
			String[] words = sentence.split(" ");
			for (int j = 0; j < words.length; j++) {
				String word = words[j];
				flexTable.setWidget(i, j,new Label( word));
			}
		}
	}

	private void buildFlexTableWithButtonExample() {
		FlexTable flexTable = new FlexTable();
		flexTable.addStyleName("flexTable");
		List<String> sentences = Arrays.asList("Here is another FlexTable","You can also use a button as export widget",
				"And include it in the table itself if you want", "like this", "Et pour les français, il faut des caractères accentués");
		fillaTableWithSentences(flexTable, sentences);

		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(flexTable);
		
		TableToExcelClient tableToExcelClient = new TableToExcelClient(flexTable, new Button("Export Button"));
		flexTable.setWidget(3, 9, tableToExcelClient.build());
		
		content.add(decoratorPanel);
		content.add(new HTML("<br />"));
	}
	
	
	private void buildCellTableExample() {
		CellTable<String> cellTable = new CellTable<String>();
		cellTable.addStyleName("cellTable");
		cellTable.addColumn(new TextColumn<String>(){
			@Override
			public String getValue(String object) {
				return object;
			}
		},new TextHeader("Cool"));
		List<String> sentences = Arrays.asList("It also works with the Cell Table","Really, that works !", "Without any server-side rewrite" 
				);
		cellTable.setRowData(0,sentences);
		
		DecoratorPanel decoratorPanel = new DecoratorPanel();
		decoratorPanel.add(cellTable);
		
		TableToExcelClient tableToExcelClient = new TableToExcelClient(cellTable);
		content.add(decoratorPanel);
		content.add(tableToExcelClient.build());
	}
	
	private void buildUIBinderExample() {
		UIBinderDemo uiBinder = new UIBinderDemo();
		List<String> sentences = Arrays.asList("This table was built with UIBinder","It uses a SimplePanel to place the export widget");
		fillaTableWithSentences(uiBinder.getExportFlexTable(), sentences);
		content.add(uiBinder);
		
	}

	
	
}
