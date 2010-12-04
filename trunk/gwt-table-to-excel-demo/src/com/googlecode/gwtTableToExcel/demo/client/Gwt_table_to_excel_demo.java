package com.googlecode.gwtTableToExcel.demo.client;

import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.FlexTable;
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

	public void onModuleLoad() {
		Scheduler.get().scheduleDeferred(new Command() {
			public void execute() {
				doModuleLoad();
			}
		});
	}

	private void doModuleLoad() {
		RootPanel.get("content").getElement().setInnerHTML("");

		FlexTable flexTable = new FlexTable();
		List<String> sentences = Arrays.asList("Here is a FlexTable",
				"You can Export it in Excel!",
				"You can get some formatting contained in the html",
				"But not those from the css");
		for (int i = 0; i < sentences.size(); i++) {
			String sentence = sentences.get(i);
			String[] words = sentence.split(" ");
			for (int j = 0; j < words.length; j++) {
				String word = words[j];
				flexTable.setText(i, j, word);
			}
		}
		RootPanel.get("content").add(flexTable);
		
		TableToExcelClient tableToExcelClient = new TableToExcelClient(flexTable);
		RootPanel.get("content").add(tableToExcelClient.build());
	}
}
