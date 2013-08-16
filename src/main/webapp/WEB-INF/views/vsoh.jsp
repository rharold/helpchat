<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page session="false"%>

<html>

<head>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap.js" />"></script>
	<script type="text/javascript"
	src="<c:url value="/resources/js/network.js" />"></script>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet" type="text/css" />
	
	 <script type='text/javascript' src='http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js'></script>
  
  
  
 
<title>Help Chat</title>



<style>
.messagebox {
	width: 50%;
	margin: 10px auto;
	background-color: #fff;
	color: #333;
	border: 1px solid #ddd;
	border-radius: 4px;
	padding-bottom: 20px;
	font-weight: bold;
}

.nav-tabs>li,.nav-pills>li {
	float: none;
	display: inline-block;
	*display: inline; /* ie7 fix */
	zoom: 1; /* hasLayout ie7 trigger */
}

.nav-tabs,.nav-pills {
	text-align: right;
}
</style>

</head>

<body>


	<div class="container">
		<img alt="Land Rover"
			src="http://localhost:8080/images/Land%20RoverLogo36.png"> <img
			alt="SDD" src="http://localhost:8080/images/sddLogo36.bmp"> <img
			alt="Topix" src="http://localhost:8080/images/topixConnected.bmp">

		<ul class="nav nav-tabs tabs-right ">
			<li class="active"><a href="#">Vehicle</a></li>
			<li><a href="#">Settings</a></li>
			<li><a href="#">Sessions</a></li>
		</ul>

		<div class="navbar">
			<div class="navbar-inner">
				<ul class="nav">
					<li class="active"><a href="#">Session</a></li>
					<li><a href="#">Selected Symptoms</a></li>
					<li><a href="#">DTC</a></li>
					<li><a href="#">Recommendations</a></li>
					<li><a href="#">Service Functions</a></li>
				</ul>
			</div>
		</div>

		<div id="east" ></div>

		<hr>

		<div class="btn-toolbar" style="text-align: center;">
			<div class="btn-group">
				<button class="btn">Pre Delivery Inspection</button>
				<button class="btn">Measurement Applications</button>
				<button class="btn">Service Functions</button>
				<button class="btn">Campaigns</button>
				<button class="btn">Diagnosis</button>
				<button class="btn">Close Session</button>
			</div>
		</div>

		<hr>

	</div>
	<!-- container -->

</body>

</html>

<script>
var paper = Raphael('east', 900, 300);
var data = [{"can_name": "CAN_HS", "modules":[{"name":"SUMB", "description":"Module", "type":"o"},
                                              {"name":"VIM", "description":"Module", "type":"o"},
                                              {"name":"PCM", "description":"Module", "type":"n"},
                                              {"name":"ABS", "description":"Module", "type":"d"},
                                              {"name":"BCM", "description":"Module", "type":"o"},
                                              {"name":"IPC", "description":"Module", "type":"o"},
                                              {"name":"PAM", "description":"Module", "type":"n"},
                                              {"name":"PBM", "description":"Module", "type":"d"}]},
            {"can_name": "CAN_MS", "modules":[{"name":"AHCM", "description":"Module", "type":"d"},
                                              {"name":"AHU", "description":"Module", "type":"d"}]},
            {"can_name": "SUB_MOST", "modules":[{"name":"DABM", "description":"Module", "type":"d"},
                                                {"name":"NCM", "description":"Module", "type":"o"},
                                                {"name":"REM", "description":"Module", "type":"n"}]}];

drawNetwork(data);
</script>



