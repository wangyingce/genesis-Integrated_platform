YAHOO.namespace("Claim.calendar");
function init_calendar(calContainer,imgBtn,dateText,dateType){
	
    YAHOO.util.Event.addListener(imgBtn,"click",popCalendar,imgBtn,true);

	function popCalendar(){
        var textCtl = document.getElementById(dateText);
        fPopCalendar(textCtl, textCtl);
        return false;
	}

}