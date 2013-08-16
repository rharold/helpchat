function drawNetwork(data) {
    //alert(data[0].can_name);
    //alert(data[0].values[0]);
    
    drawCan(data[0].can_name, 0);
    drawCanData(data[0].modules, 0);
    
    drawCan(data[1].can_name, 1);
    drawCanData(data[1].modules, 1);
    
    drawCan(data[2].can_name, 2);
    drawCanData(data[2].modules, 2);
}

function drawCanData(canData, row) {
    for(i = 0; i < canData.length; i++) {
        drawCanSymbol(canData[i].name, canData[i].description, canData[i].type, row, i);
        //drawJoiningLine(row, i);
    }        
}

function drawJoiningLine(row, column) {
    var y = (row * 70) + 40;
    var x1 = ((column) * 68.5) + 70;
    var x2 = ((column) * 68.5) + 100;
    
    paper.path("M" + x1 + " " + y + "L" + x2 + " " + y + "z").attr("fill", "grey").attr({"stroke": "grey","stroke-width":"4"});    
    
}

var menuSwitch = false;
var menuRect;
var menuTopRect;
var menuCross;
var menuText1;
var menuText2;
var menuText3;
var menuSpyglass;
var menuCogs;

function menu(x, y, moduleName, moduleDescription) {
    if(menuSwitch == true) {
        menuRect.remove();  
        menuTopRect.remove();
        menuCross.remove();        
        menuText1.remove();
        menuText2.remove();
        menuText3.remove();
        menuSpyglass.remove();
        menuCogs.remove();
    }
    menuRect = paper.rect(x + 28, y + 28, 200, 100, 8).attr({"fill-opacity":"0.8", "fill":"skyblue"}).attr("stroke", "none");
    menuTopRect = paper.rect(x + 31, y + 31, 194, 19, 4).attr({"fill-opacity":"0.8", "fill":"deepskyblue"}).attr("stroke", "none");
    menuSwitch = true;
    
    menuCross = paper.path('M24.778,21.419 19.276,15.917 24.777,10.415 21.949,7.585 16.447,13.087 10.945,7.585 8.117,10.415 13.618,15.917 8.116,21.419 10.946,24.248 16.447,18.746 21.948,24.248z').attr("fill", "black");
    menuCross.attr("transform", "T" + (x + 200) + "," + (y + 23 ) + "s0.6");
    menuCross.click(function() {menuSwitch = false;menuCross.remove(); menuRect.remove();menuText1.remove();menuText2.remove();menuText3.remove();menuSpyglass.remove();menuCogs.remove();menuTopRect.remove();});
    
    menuText1 = paper.text(x + 35, y + 41, moduleName + " " + moduleDescription).attr({"font": "15px Fontin-Sans, Arial", 'text-anchor':'start', "font-weight": "bold"});
    
    menuSpyglass= paper.path('M29.772,26.433l-7.126-7.126c0.96-1.583,1.523-3.435,1.524-5.421C24.169,8.093,19.478,3.401,13.688,3.399C7.897,3.401,3.204,8.093,3.204,13.885c0,5.789,4.693,10.481,10.484,10.481c1.987,0,3.839-0.563,5.422-1.523l7.128,7.127L29.772,26.433zM7.203,13.885c0.006-3.582,2.903-6.478,6.484-6.486c3.579,0.008,6.478,2.904,6.484,6.486c-0.007,3.58-2.905,6.476-6.484,6.484C10.106,20.361,7.209,17.465,7.203,13.885z').attr("fill", "dimgrey")
    
    menuSpyglass.attr("transform", "T" + (x + 25) + "," + (y + 65) + "s0.6");
    menuText2 = paper.text(x + 60, y + 80, "View DTCs").attr({font: "15px Fontin-Sans, Arial", 'text-anchor':'start' });
    
    
    menuCogs = paper.path('M17.41,20.395l-0.778-2.723c0.228-0.2,0.442-0.414,0.644-0.643l2.721,0.778c0.287-0.418,0.534-0.862,0.755-1.323l-2.025-1.96c0.097-0.288,0.181-0.581,0.241-0.883l2.729-0.684c0.02-0.252,0.039-0.505,0.039-0.763s-0.02-0.51-0.039-0.762l-2.729-0.684c-0.061-0.302-0.145-0.595-0.241-0.883l2.026-1.96c-0.222-0.46-0.469-0.905-0.756-1.323l-2.721,0.777c-0.201-0.228-0.416-0.442-0.644-0.643l0.778-2.722c-0.418-0.286-0.863-0.534-1.324-0.755l-1.96,2.026c-0.287-0.097-0.581-0.18-0.883-0.241l-0.683-2.73c-0.253-0.019-0.505-0.039-0.763-0.039s-0.51,0.02-0.762,0.039l-0.684,2.73c-0.302,0.061-0.595,0.144-0.883,0.241l-1.96-2.026C7.048,3.463,6.604,3.71,6.186,3.997l0.778,2.722C6.736,6.919,6.521,7.134,6.321,7.361L3.599,6.583C3.312,7.001,3.065,7.446,2.844,7.907l2.026,1.96c-0.096,0.288-0.18,0.581-0.241,0.883l-2.73,0.684c-0.019,0.252-0.039,0.505-0.039,0.762s0.02,0.51,0.039,0.763l2.73,0.684c0.061,0.302,0.145,0.595,0.241,0.883l-2.026,1.96c0.221,0.46,0.468,0.905,0.755,1.323l2.722-0.778c0.2,0.229,0.415,0.442,0.643,0.643l-0.778,2.723c0.418,0.286,0.863,0.533,1.323,0.755l1.96-2.026c0.288,0.097,0.581,0.181,0.883,0.241l0.684,2.729c0.252,0.02,0.505,0.039,0.763,0.039s0.51-0.02,0.763-0.039l0.683-2.729c0.302-0.061,0.596-0.145,0.883-0.241l1.96,2.026C16.547,20.928,16.992,20.681,17.41,20.395zM11.798,15.594c-1.877,0-3.399-1.522-3.399-3.399s1.522-3.398,3.399-3.398s3.398,1.521,3.398,3.398S13.675,15.594,11.798,15.594zM27.29,22.699c0.019-0.547-0.06-1.104-0.23-1.654l1.244-1.773c-0.188-0.35-0.4-0.682-0.641-0.984l-2.122,0.445c-0.428-0.364-0.915-0.648-1.436-0.851l-0.611-2.079c-0.386-0.068-0.777-0.105-1.173-0.106l-0.974,1.936c-0.279,0.054-0.558,0.128-0.832,0.233c-0.257,0.098-0.497,0.22-0.727,0.353L17.782,17.4c-0.297,0.262-0.568,0.545-0.813,0.852l0.907,1.968c-0.259,0.495-0.437,1.028-0.519,1.585l-1.891,1.06c0.019,0.388,0.076,0.776,0.164,1.165l2.104,0.519c0.231,0.524,0.541,0.993,0.916,1.393l-0.352,2.138c0.32,0.23,0.66,0.428,1.013,0.6l1.715-1.32c0.536,0.141,1.097,0.195,1.662,0.15l1.452,1.607c0.2-0.057,0.399-0.118,0.596-0.193c0.175-0.066,0.34-0.144,0.505-0.223l0.037-2.165c0.455-0.339,0.843-0.747,1.152-1.206l2.161-0.134c0.152-0.359,0.279-0.732,0.368-1.115L27.29,22.699zM23.127,24.706c-1.201,0.458-2.545-0.144-3.004-1.345s0.143-2.546,1.344-3.005c1.201-0.458,2.547,0.144,3.006,1.345C24.931,22.902,24.328,24.247,23.127,24.706z').attr("fill", "dimgrey");
    
    menuCogs.attr("transform", "T" + (x + 25) + "," + (y + 95) + "s0.6");
    menuText3 = paper.text(x + 60, y + 110, "Rerun Data Collect").attr({font: "15px Fontin-Sans, Arial, Bold", 'text-anchor':'start' })
}

function drawCanSymbol(moduleName, moduleDescription, moduleType, row, column) {
    
    var imageX = (column * 70) + 100;   
    var imageY = (row * 70) + 22;
    
    var textX = (column * 70) + 116;   
    var textY = (row * 70) + 59;
   

    if(moduleType == 'o') {
        var e1 = paper.path(
            'M16,1.466C7.973,1.466,1.466,7.973,1.466,16c0,8.027,6.507,14.534,14.534,14.534c8.027,0,14.534-6.507,14.534-14.534C30.534,7.973,24.027,1.466,16,1.466z M17.328,24.371h-2.707v-2.596h2.707V24.371zM17.328,19.003v0.858h-2.707v-1.057c0-3.19,3.63-3.696,3.63-5.963c0-1.034-0.924-1.826-2.134-1.826c-1.254,0-2.354,0.924-2.354,0.924l-1.541-1.915c0,0,1.519-1.584,4.137-1.584c2.487,0,4.796,1.54,4.796,4.136C21.156,16.208,17.328,16.627,17.328,19.003z').attr({fill: "#ff8737", stroke: "none"});
        e1.attr("transform", "T" + imageX + "," + imageY + "s1");
        e1.click(function() {menu(imageX, imageY, moduleName, moduleDescription)});
    }
    if(moduleType == 'd') {
        var e2 = paper.path(
            'M16,1.466C7.973,1.466,1.466,7.973,1.466,16c0,8.027,6.507,14.534,14.534,14.534c8.027,0,14.534-6.507,14.534-14.534C30.534,7.973,24.027,1.466,16,1.466z M14.757,8h2.42v2.574h-2.42V8z M18.762,23.622H16.1c-1.034,0-1.475-0.44-1.475-1.496v-6.865c0-0.33-0.176-0.484-0.484-0.484h-0.88V12.4h2.662c1.035,0,1.474,0.462,1.474,1.496v6.887c0,0.309,0.176,0.484,0.484,0.484h0.88V23.622z').attr({fill: "#ff8737", stroke: "none"});
        e2.attr("transform", "T" + imageX + "," + imageY + "s1");
        e2.click(function() {menu(imageX, imageY, moduleName, moduleDescription)});
    }
    if(moduleType == 'n') {
        var e3 =
            paper.path('M29.548,3.043c-1.081-0.859-2.651-0.679-3.513,0.401L16,16.066l-3.508-4.414c-0.859-1.081-2.431-1.26-3.513-0.401c-1.081,0.859-1.261,2.432-0.401,3.513l5.465,6.875c0.474,0.598,1.195,0.944,1.957,0.944c0.762,0,1.482-0.349,1.957-0.944L29.949,6.556C30.809,5.475,30.629,3.902,29.548,3.043zM24.5,24.5h-17v-17h12.756l2.385-3H6C5.171,4.5,4.5,5.171,4.5,6v20c0,0.828,0.671,1.5,1.5,1.5h20c0.828,0,1.5-0.672,1.5-1.5V12.851l-3,3.773V24.5z').attr({fill: "#b4f002", stroke: "none"});
        e3.attr("transform", "T" + imageX + "," + imageY + "s1");
        e3.click(function() {menu(imageX, imageY, moduleName, moduleDescription)});
    }

    paper.text(textX, textY, moduleName).attr({font: "10px Fontin-Sans, Arial"});
    
    paper.path("M" + (imageX - 35) + " " + (imageY + 17) + "L" + (imageX - 2) + " " + (imageY + 17) + "z").attr("fill", "grey").attr({"stroke": "grey","stroke-width":"4"});
}

function drawCan(canName, row) {
     var imageY = (row * 70) + 20;
     var textY =  (row * 70) + 65;

     paper.text(36, textY, canName).attr({font: "13px Fontin-Sans, Arial"});

    var e1 = paper.path('M6.812,17.202l7.396-3.665v-2.164h-0.834c-0.414,0-0.808-0.084-1.167-0.237v1.159l-7.396,3.667v2.912h2V17.202zM26.561,18.875v-2.913l-7.396-3.666v-1.158c-0.358,0.152-0.753,0.236-1.166,0.236h-0.832l-0.001,2.164l7.396,3.666v1.672H26.561zM16.688,18.875v-7.501h-2v7.501H16.688zM27.875,19.875H23.25c-1.104,0-2,0.896-2,2V26.5c0,1.104,0.896,2,2,2h4.625c1.104,0,2-0.896,2-2v-4.625C29.875,20.771,28.979,19.875,27.875,19.875zM8.125,19.875H3.5c-1.104,0-2,0.896-2,2V26.5c0,1.104,0.896,2,2,2h4.625c1.104,0,2-0.896,2-2v-4.625C10.125,20.771,9.229,19.875,8.125,19.875zM13.375,10.375H18c1.104,0,2-0.896,2-2V3.75c0-1.104-0.896-2-2-2h-4.625c-1.104,0-2,0.896-2,2v4.625C11.375,9.479,12.271,10.375,13.375,10.375zM18,19.875h-4.625c-1.104,0-2,0.896-2,2V26.5c0,1.104,0.896,2,2,2H18c1.104,0,2-0.896,2-2v-4.625C20,20.771,19.104,19.875,18,19.875z ').attr({fill: "#b4f002", stroke: "none"});
    
    //e1.attr("transform", "T32,10");
    
    //e1.attr("transform", "s0.7");
    e1.attr("transform", "T20," + imageY + "s1.5");


   }