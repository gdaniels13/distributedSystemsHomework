function splitAtComma(toSplit){
	return toSplit.split(',');
}


//Parses the message response and turns the string into an object
function parser(toParse){
	var toReturn = {};
	toReturn.type = toParse.substring(0,toParse.indexOf(':'));
	var data = toParse.substring(toParse.indexOf(':')+1,toParse.length);
	
	data = splitAtComma(data);

	switch(toReturn.type){
		case 'def':
			toReturn.gameId = data[0];
			toReturn.hint = data[1];
			toReturn.definition = data[2];
			break;
		case 'answer':
			toReturn.gameId = data[0];
			if(data[1]=='F'){
				toReturn.result = false;
				toReturn.correct = data[2];
			}
			else{
				toReturn.result = true;
				toReturn.score = data[2];
			}
			break;
		case 'hint':
			toReturn.gameId = data[0];
			toReturn.hint = data[1];
			break;
		case 'error':
			toReturn.error = data[1];
	}
	return toReturn;
}


function test(){
	console.log(parser('def:5163,_______,earth measurement on a large scale'));	
	console.log(parser('answer:24,F,180'));	
	console.log(parser('error:Invalid GameId'));	
	console.log(parser('hint:__buli__'));	
}

module.exports = parser;