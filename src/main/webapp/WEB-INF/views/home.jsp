<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page session="false" %>

<html>

<head>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/cometd.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/cometd/AckExtension.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/cometd/ReloadExtension.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/cometd/TimeStampExtension.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/cometd/TimeSyncExtension.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.cometd.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.cometd-ack.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.cometd-reload.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.cometd-timestamp.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.cometd-timesync.js" />"></script>
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/css"/>    
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
    </style>
    
</head>

<body>
    <div id="me">
        <input type="hidden" id="meName" value="${name}">
        <span style="line-height: 25px; vertical-align: top;" class="label label-success" id="userLabel"><i class="icon-user icon-white"></i>${name}</span>
        <select name="statusSelector" id="statusSelector">
		    <c:forEach items="${statuss}" var="status">
		        <option value="${status}" ${status == selectedStatus ? 'selected' : ''}>${status}</option>
		    </c:forEach>
		</select>
		
		<select name="languageSelector" id="languageSelector">
		    <option value="none" selected>No Translation</option>
		    <c:forEach items="${languagesFromTo}" var="langaugeFromTo">
		        <option value="${langaugeFromTo.valueString}">${langaugeFromTo.displayString}</option>
		    </c:forEach>
		</select>
		
		<button style="padding: 2px 10px; line-height: 24px; vertical-align: top;" id="logoutbtn" type="button" class="btn btn-info">Logout</button>
    </div>
    <div id="available">
	    <div class="accordion" id="accordion2">
		  <div class="accordion-group">
		    <div class="accordion-heading">
		      <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
		        Engineers
		      </a>
		    </div>
		    <div id="collapseOne" class="accordion-body collapse in">
		      <div class="accordion-inner">
		        <div class="tabbable tabs-left">
				    <ul class="nav nav-tabs">
					    <c:forEach varStatus="counter" var="friend" items="${availableFriends}" >
					       <c:choose>  
							   <c:when test="${counter.count == 1}">  
							      <li id="listFriend${counter.count}"  class="active" ><a href="#user${counter.count}" data-toggle="tab"><span class="label label-success"><i class="icon-user icon-white" id="picFriend${counter.count}"></i></span>${friend.name}</a></li>
							   </c:when>  
							   <c:otherwise>  
							      <li id="listFriend${counter.count}"><a href="#user${counter.count}" data-toggle="tab"><span class="label label-success"><i class="icon-user icon-white" id="picFriend${counter.count}"></i></span>${friend.name}</a></li>  
							   </c:otherwise>  
							</c:choose> 					      
						</c:forEach>	
						
						<c:forEach varStatus="counter" var="friend" items="${unavailableFriends}" >
					      <li><a href="#unUser${counter.count}" data-toggle="tab"><span id="unUserLabel${counter.count}" class="label label-warning"><i class="icon-user icon-white"></i></span>${friend.name}</a></li>
						</c:forEach>					
					</ul>
					<div class="tab-content">
					    <c:forEach varStatus="counter" var="friend" items="${availableFriends}">
					      <c:choose>  
						   <c:when test="${counter.count == 1}">  						      
						        <div class="tab-pane active" id="user${counter.count}">							      
						              <textarea disabled rows="10" style="width:100%; font-size: 12px;" id="textAreaFriend${counter.count}"></textarea>
						              <input type="hidden" name="userFriend${counter.count}" value="${friend.user}" id="userFriend${counter.count}">
						              <input type="text" style="width:90%;" id="textFriend${counter.count}"/><button style="line-height: 19px; padding: 0px 5px; vertical-align: top; margin-left: 6px;" id="buttonFriend${counter.count}" type="button" class="btn btn-info">send</button>						        
					            </div>					          
						   </c:when>  
						   <c:otherwise>  
						      <div class="tab-pane" id="user${counter.count}">
					           <textarea disabled rows="10" style="width:100%; font-size: 12px;" id="textAreaFriend${counter.count}"></textarea>
				               <input type="hidden" name="userFriend${counter.count}" value="${friend.user}" id="userFriend${counter.count}">
				               <input type="text" style="width:90%;" id="textFriend${counter.count}"/><button style="line-height: 19px; padding: 0px 10px; vertical-align: top; margin-left: 6px;" id="buttonFriend${counter.count}" type="button" class="btn btn-info">send</button>
					          </div>  
						   </c:otherwise>  
						  </c:choose> 
						</c:forEach>
						<c:forEach varStatus="counter" var="friend" items="${unavailableFriends}">
					      <div class="tab-pane" id="unUser${counter.count}">
				           <textarea disabled rows="10" style="width:100%;"></textarea>	
				           <input class="unUser" type="hidden" name="unUserFriend${counter.count}" value="${friend.user}" id="unUserFriend${counter.count}">			           
				          </div>
						</c:forEach>
					</div>					
				</div>
		      </div>
		    </div>
		  </div>
		</div>  
	</div>	
</body>

</html>

<script>
	$(document).ready(function(){	
		
	  $("#logoutbtn").click(function() {
          window.location = "logout"; 
	  });	
		
	  $("#statusSelector").change(function() {
		  
		  var selection = $(this).val();
		  
		  $.ajax({
			  type: "POST",
			  url: "changeStatus",
			  data: { status: selection }
			  }).done(function( data ) {
				  if(selection == "online") {
				      $("#userLabel").attr('class', 'label label-success');
				  } else {
					  $("#userLabel").attr('class', 'label label-important');
				  }
		  });  
	  });
	  
		  $(":text").keypress(function(event) {
			  if ( event.which == 13 ) {
				  var friendId = $(this).attr("id").substring(4);
				  
				  var textAreaSelector = "#textArea" + friendId;	
			      var userSelector = "#user" + friendId;
			      
			      sendText($(this).val(), $(textAreaSelector), $(userSelector).val(), $(this));
			      event.preventDefault();
			  }
		  });
	  
	  
	  	$(":button").click(function() {
	      var friendId = $(this).attr("id").substring(6);
	      
	    
	      var textAreaSelector = "#textArea" + friendId;
	      var textSelector = "#text" + friendId;	
	      var userSelector = "#user" + friendId;
	      
	      sendText($(textSelector).val(), $(textAreaSelector), $(userSelector).val(), $(textSelector)); 
		});
	  	
	  	$("a").click(function() {
		      var href = $(this).attr('href');
		      var listId = href.substr(5);
		      var listSelector = "#picFriend" + listId;
		      var classs = $(listSelector).attr("class");
		      if(classs.indexOf("icon-white") == -1) {
		    	  $(listSelector).toggleClass("icon-white");	
		      }
		});
	  	
	  	
	  	
	  	$.cometd.configure({	  		    
            url: "http://dskso-rharold:8081/help/cometd",
            logLevel: 'debug'
        });
	  	
	  	$.cometd.websocketEnabled = false;
	  	
	  	$.cometd.handshake();
	  	
	  	$.cometd.addListener('/meta/handshake', function(message)
	  		    {
	  		        if (message.successful)
	  		        {
	  		            //dom.byId('status').innerHTML += '<div>CometD handshake successful</div>';
	  		            $.cometd.subscribe('/chat/${user}', function(message)
	  		            {
	  		                var data = message.data;
	  		                var message = data.message;
	  		                var participent = data.participent;	
	  		                addText(formatHeader(participent), message, getTextArea(participent));
	  		            });
	  		            
	  		          $.cometd.subscribe('/roster', function(message)
	  	  		            {
	  		        			var data = message.data;
	  	  		                var fromUser = data.fromUser;
	  	  		                var status = data.status;
	  	  		                
	  	  		                var idCount = amIBothered(fromUser); 
	  	  		                
	  	  		                if((status == available) && idCount > -1) {
	  	  		                    toggleToAvailable(fromUser, idCount);    	
		  	  		            }
	  	  		            });
	  		        }	  		       
	  		    });
	});
	
	function amIBothered(from) {
		$(".unUser").each(function() {
		    var user = $(this).attr("value");
		    if(from.indexOf(user) > -1) {
		    	var elementName = $(this).attr("name");		    	
		    	return elementName.substr(12);
		    }
		});
		
		return -1;
	}
	
	function toggleToAvailable(from, countId) {
		$("#unUserLabel" + countId).attr("class", "label label-success");
	}
	
	function sendText(text, textArea, user, textInput) {
          var translatedText = "";
		
		  if(translationNeeded()) {
			  translatedText = getTranslatedText(text);
          }		
		
		  var header;
		  var messageToSend;
		  
		  if(translatedText != "") {
			  header = formatHeader($("#meName").val(), text);
			  addText(header, translatedText, textArea);
			  messageToSend = translatedText;
		  } else {
			  header = formatHeader($("#meName").val(), "");
			  addText(header, text, textArea);
			  messageToSend = text;
		  }
		  	  	      
	      
	      
	      $.ajax({
			  type: "POST",
			  url: "message",
			  data: { user: user, message: messageToSend}
	      });
	
	      textInput.val("");
	}
	
	function getTranslatedText(text) {
		var translatedText = "";
		
		$.ajax({
				  type: "GET",
				  url: "getTranslation",
				  data: { message: text, fromTo: $("#languageSelector").val()}
			  }).done(function( data ) {
				  translatedText = data;
		      });
		
		return translatedText;
	}
	
	function translationNeeded() {
		var selection = $("#languageSelector").val();
		
		return "none" != selection;
	}
	
	function formatHeader(name, nonTranslatedText) {
		var d = new Date();
		return name + " (" + d.getHours() + ":" + d.getMinutes() + ")" + " " + nonTranslatedText;
	}
	
	function addText(header, text, textArea, user) {
		
		
		textArea.val(textArea.val() + header);
		textArea.val(textArea.val() + "\n");	      
		textArea.val(textArea.val() + " - " + text);
		textArea.val(textArea.val() + "\n");
		textArea.val(textArea.val() + "-------------------------------------");
		textArea.val(textArea.val() + "\n");
		
		textArea.scrollTop(
				textArea[0].scrollHeight - textArea.height()
		    );
	}
	
	function runReceive() {
		
		$.ajax({
			  type: "GET",
			  url: "receive"
			  }).done(function( data ) {
				  if(data != null && data != "") {
					  var participent = data.participent;
					  
					  addText(formatHeader(participent), data.message, getTextArea(data.participent));
				  }
		  });  
		
		setTimeout(runReceive, 500);
				
	}
	
	function getTextArea(participent) {
		var textArea;
		
		 $(":input[type='hidden']").each(function() {
		     
		     var elementParticipent = $(this).attr("value");
		     
		     if(participent.indexOf(elementParticipent) > -1) {
		    	 var friendId = $(this).attr("id").substring(4);
		    	 var textAreaSelector = "#textArea" + friendId;
		    	 if($(textAreaSelector) != null) {
		    		 textArea = $(textAreaSelector);
		    		 if(userNotInFocus(friendId)) {
		    			 flashUser(friendId);		    				
		    		 }
		    	 }		    	 
		     }
		 }); 
		 
		 return textArea;
	}
	
	function flashUser(friendId) {
		setTimeout(function flashUser()  {
			var listSelector = "#pic" + friendId;
			$(listSelector).toggleClass("icon-white");	
		}, 500);	 
	 
	 setTimeout(function flashUser()  {
			var listSelector = "#pic" + friendId;
			$(listSelector).toggleClass("icon-white");	
		}, 1000);
	 
	 setTimeout(function flashUser()  {
			var listSelector = "#pic" + friendId;
			$(listSelector).toggleClass("icon-white");	
		}, 1500);
	 
	 setTimeout(function flashUser()  {
			var listSelector = "#pic" + friendId;
			$(listSelector).toggleClass("icon-white");	
		}, 2000);
	 
	 setTimeout(function flashUser()  {
			var listSelector = "#pic" + friendId;
			$(listSelector).toggleClass("icon-white");	
		}, 2500);
	 setTimeout(function flashUser()  {
			var listSelector = "#pic" + friendId;
			$(listSelector).toggleClass("icon-white");	
		}, 3000);
	 setTimeout(function flashUser()  {
			var listSelector = "#pic" + friendId;
			$(listSelector).toggleClass("icon-white");	
		}, 3500);
	 setTimeout(function flashUser()  {
			var listSelector = "#pic" + friendId;
			$(listSelector).toggleClass("icon-white");	
		}, 4000);
	 setTimeout(function flashUser()  {
			var listSelector = "#pic" + friendId;
			$(listSelector).toggleClass("icon-white");	
		}, 4500);
	} 
	
	function userNotInFocus(friendId) {
		var listSelector = "#list" + friendId;
		
		var activeOrNot = $(listSelector).attr('class');
		
		return activeOrNot == "" || activeOrNot == null;
	}
	
	
	
</script>



