var ui = require('./ui.js');
var dgram = require('dgram');
var parser = require('./responseParser.js');
var fs = require('fs');



var config = {
	serverHost: "erewhon.mdsc.com",
	serverPort:12001,
	user:'A00798340,Greg,Daniels',
};


//Read in host information from the file
	file =  fs.readFileSync('./hostInfo.dat');
	console.log(file.toString()); 
	var data = file.toString().split(' ');
	config.serverHost = data[0];
	config.serverPort = data[1];


var udpClient = dgram.createSocket('udp4');
udpClient.bind(0); //listen on any random port


//sends a message
function sendMessage(toSend, config)
{
	console.log("Message: " + toSend);
	console.log('host: '+config.serverHost);
	console.log('port: '+config.serverPort);
	var message = new Buffer(toSend);
	udpClient.send(message,0,message.length,config.serverPort, config.serverHost, function(err,bytes){
		if(err)
			console.log(err);
		else{
			console.log('sent ' + bytes + 'bytes');
		}
		});
}


var myClient = {};

myClient.config = config;
myClient.gameId = null;

myClient.newGame = function(){
	sendMessage("newgame:"+config.user, myClient.config);
};

myClient.guess = function(myGuess){
	sendMessage("guess:" + myClient.gameId + ',' + myGuess, myClient.config);
};

myClient.getHint = function(){
		sendMessage('gethint:' + myClient.gameId, myClient.config);
};

myClient.exit = function(){
		myClient.gameId = null;
		sendMessage('exit:'+myClient.gameId,  myClient.config);	
};


//this function handles all incoming messages and modifies 
//the elements in the DOM appropriately
udpClient.on('message',function(msg,rinfo){
	var response = parser(msg.toString());
	console.log('response: ' +response);

	switch(response.type){
		case 'def':
			myClient.gameId=response.gameId;
			ui.newGame(response);
			break;
		case 'hint':
			ui.hint(response);
			break;
		case 'answer':
			if(response.result === true){
				ui.wonGame(response);
			}
			else{
				ui.didntWin(response);
			}
			break;
		case 'error':
			ui.showError(response);
			break;
	}
});







module.exports = myClient;

