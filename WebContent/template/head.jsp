<title>SimpleAdmin</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/navi.css" media="screen" />
<script type="text/javascript">
$(function(){
	$(".box .h_title").not(this).next("ul").hide("normal");
	$(".box .h_title").not(this).next("#home").show("normal");
	$(".box").children(".h_title").click( function() { $(this).next("ul").slideToggle(); });
});
</script>

		<!-- Jquery directly from google servers--> 
		<script type="text/javascript" src="js/jquery-1.8.2.js" ></script>
		<script type="text/javascript" src="js/datasorter/jquery.tablesorter.pager.js" ></script>
		<script type="text/javascript" src="js/datasorter/jquery.tablesorter.min.js" ></script>
		<script type="text/javascript" src="js/datasorter/jquery.tablesorter.widgets.js" ></script>
		
		<script type="text/javascript" >
			$(function() { 
			  // call the tablesorter plugin 
			  $("table")
			  .tablesorter({ 
			    // initialize zebra striping and filter widgets 
			    widgets: ["zebra", "filter"], 
			    widgetOptions : { 
			      // css class applied to the table row containing the filters & the inputs within that row 
			      filter_cssFilter : 'tablesorter-filter', 
			      // filter widget: If there are child rows in the table (rows with class name from "cssChildRow" option) 
			      // and this option is true and a match is found anywhere in the child row, then it will make that row 
			      // visible; default is false 
			      filter_childRows : false, 
			      // Set this option to true to use the filter to find text from the start of the column 
			      // So typing in "a" will find "albert" but not "frank", both have a's; default is false 
			      filter_startsWith : false 
			    } 
			 
			  })
			 ;
			}); 
			
		</script>
			
		
		<!-- datasorter css -->
		<link rel="stylesheet" type="text/css" href="css/datasorter/style.css"       />
		<link rel="stylesheet" type="text/css" href="css/datasorter/tablesorter.css" />
		
		
		
		<script type="text/javascript" src="js/datepicker/jquery-ui.js"></script>
 		<link type='text/css' href='css/datepicker/jquery-ui.css' rel='stylesheet' media='screen' />
		<script>
		 $(document).ready(
		  
		  /* This is the function that will get executed after the DOM is fully loaded */
		  function () {
		    $( ".datepicker" ).datepicker({
		      changeMonth: true,//this option for allowing user to select month
		      changeYear: true, //this option for allowing user to select from year range
		      dateFormat: 'dd/mm/yy'
		    });
		  }
		
		);
		</script>