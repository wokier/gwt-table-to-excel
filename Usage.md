## Add the library ##

#### With Maven ####
Use [gwt-maven-plugin](http://mojo.codehaus.org/gwt-maven-plugin/)

Define the dependency in the dependencies section
```
		<dependency>
			<groupId>com.googlecode.gwt-table-to-excel</groupId>
			<artifactId>gwt-table-to-excel</artifactId>
			<version>0.0.4</version>
		</dependency>
```
#### Without Maven ####
Add the following jars into WEB-INF/lib
  * gwt-table-to-excel-0.0.4.jar
  * commons-io-1.4.jar

## Add the module in your .gwt.xml ##
```
<inherits name="com.googlecode.gwtTableToExcel.gwtTableToExcel" />
```

## Add the servlet in your web.xml ##
```
     <servlet>
		<servlet-name>gwtTableToExcelServlet</servlet-name>
		<servlet-class>com.googlecode.gwtTableToExcel.server.TableToExcelServlet</servlet-class>
	</servlet>

	<servlet-mapping>
		<servlet-name>gwtTableToExcelServlet</servlet-name>
		<url-pattern>/YOUR_MODULE_NAME/excel</url-pattern>
	</servlet-mapping>
```
Advanced users may have a look at [EncodingIssues](http://code.google.com/p/gwt-table-to-excel/wiki/EncodingIssues)

## Define an hidden frame in your original html page (used as download target) ##
```
<iframe src="javascript:''" id="gwt-table-to-excel-target" tabIndex='-1' class="invisible"></iframe>
```
## Place the export widget ##
```
TableToExcelClient tableToExcelClient = new TableToExcelClient(myTable);
myPanel.add(tableToExcelClient.getExportWidget());
```
## Export ##
Just export !
## Custom the style ##
You can define style for the following classes :
```
.gwt-table-to-excel-form {
}
.gwt-table-to-excel-panel {
}
.gwt-table-to-excel-exportWidget {
}
```
For exemple, it could be nice to put an excel icon in the export widget.