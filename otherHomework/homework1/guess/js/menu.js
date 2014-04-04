//these are all of the button handles 

var gui = require('nw.gui');

var socket = require('./js/socket.js');

//load host and port into text boxes on start up
$('#host').attr('value',socket.config.serverHost);
$('#port').attr('value',socket.config.serverPort);



$('#giveUp').click(function(e) {
	$('#definitionArea').hide();
	$('#resultArea').show();
	$('#resultArea').text("Don't be a quitter next time.");
	$('#submitGuess').attr("disabled",true);
	$('#getHint').attr("disabled",true);
	$('#giveUp').attr("disabled",true);
    socket.exit();

  });

$('#newGame').click(function(e) {
		$('#guess').val('');
		socket.exit();
    socket.newGame();
  });

//button handles
$('#getHint').click(function(event){
	socket.getHint();
});

$('#submitGuess').click(function(event){
	var guess = $('#guess').val();
	socket.guess(guess);
});

//file system to save to server and port
var fs = require('fs');


$('#submitHost').click(function(event){
	var host = $('#host').val();
	var port = $('#port').val();

	if(host ==='' || port === ''){
		alert('Host and Port cannot be left blank');
		return;
	}

	if(socket.gameId){
		$('#giveUp').trigger('click');		
	}

	socket.config.serverHost = host;
	socket.config.serverPort = port;
	hostInfoFile(socket.config);

	$('#submitHost').attr('class','btn btn-warning');
	$('#submitHost').fadeOut(700,function(){
                $('#submitHost').attr('class','btn btn-success');
                $('#submitHost').attr('style','');
		});
});


function hostInfoFile(config){
	fs.open('./hostInfo.dat', 'w', function(err,fd){
		if(err){
			alert('unable to save host/port information to file but it will work until you close the program');
		}
		else{
			var buffer = new Buffer(config.serverHost + " "+config.serverPort);
			console.log(buffer.toString());
			fs.writeSync(fd,buffer,0,buffer.length,null);
			fs.closeSync(fd);
		}
	});

}