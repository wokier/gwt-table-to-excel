GWT module to export a table, without writing any server-side code.

Following the [dry](http://en.wikipedia.org/wiki/Don%27t_repeat_yourself) principle, you don't want to write a table building twice, one for the GWT UI, and another for the excel export? This framework is for you!

### How it works ? ###
The main point is that excel and other modern spreadsheet knows how to render an html table, so just open the dynamically built html table with the correct mime-type, and that's all. The gwt-table-to-excel servlet acts as a mirror.

Here is an exemple with a GET request:
[try to open a table](http://gwt-table-to-excel-demo.appspot.com/gtte/excel?fileName=get-gtte-export&html=<table><tr><td>It</td><td>is</td><td>simple</td></tr></table>)

A get request will only work for short table due to url length constraints.
The framework actually use a POST request.

Watch the [Demo](http://gwt-table-to-excel-demo.appspot.com/)

### Scope ###
Any browser, any server, any MS Excel, Open Office or Libre Office


### Limitations ###
  * The ccs style is not taken into account, so if you want to preserve it, you would have to convert it into dom injection with [setStyleAttribute](http://google-web-toolkit.googlecode.com/svn/javadoc/2.1/com/google/gwt/user/client/DOM.html#setStyleAttribute(com.google.gwt.user.client.Element,%20java.lang.String,%20java.lang.String))
  * As the whole table is passed into the request, you'll won't have very good performances for very large tables
  * The paging is not taken into account, only the current page is exported

Continue with [Usage](http://code.google.com/p/gwt-table-to-excel/wiki/Usage)

&lt;wiki:gadget url="http://www.ohloh.net/p/487289/widgets/project\_partner\_badge.xml" height="53" border="0"/&gt;