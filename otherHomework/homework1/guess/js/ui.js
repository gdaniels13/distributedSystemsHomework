//used for modifying the UI

var ui = {};

ui.newGame = function(data){
	$('#definition').text("Definition: " + data.definition);
	$('#hint').text("Hint: " + data.hint);
	$('#definitionArea').show();
	$('#resultArea').hide();
	$('#submitGuess').attr("disabled",false);
	$('#getHint').attr("disabled",false);
	$('#giveUp').attr("disabled",false);

};

ui.hint = function(data){
	$('#hint').text("Hint: " + data.hint);
};

ui.wonGame = function(data){
	$('#definitionArea').hide();
	var area = $('#resultArea');
	area.show();
	area.text("Congratulations you scored " + data.score + " points.");
	$('#submitGuess').attr("disabled",true);
	$('#getHint').attr("disabled",true);
	$('#giveUp').attr("disabled",true);
};

ui.didntWin = function(data){
	$('#resultArea').show();
	$('#resultArea').text('You got ' + data.correct + ' letters correct.');
	$('#submitGuess').attr('class','btn btn-warning');
	$('#submitGuess').fadeOut(700,function(){
                $('#submitGuess').attr('class','btn btn-success');
                $('#submitGuess').attr('style','');
            });
};

ui.showError = function(data){
	$('#resultArea').show();
	$('#resultArea').text("error: " + data);
};

module.exports = ui;