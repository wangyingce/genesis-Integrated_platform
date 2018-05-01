/**
 * common js
 */
/** Gobal bCancel; */
var bCancel = false;
var DATE_DELIMITER = "-";
var DB_INT_LENGTH = 64; //Database Length.defalut64
var MAX_INTEGER = Math.pow(2, DB_INT_LENGTH - 1) - 1;
var MIN_INTEGER = -Math.pow(2, DB_INT_LENGTH - 1);
var MAX_SMALLINT = Math.pow(2, DB_INT_LENGTH / 4 - 1) - 1;
var MIN_SMALLINT = -Math.pow(2, DB_INT_LENGTH / 4 - 1);
var VERBOSE = false;
var FIELD_SEPARATOR = "_FIELD_SEPARATOR_";
var GROUP_SEPARATOR = "_GROUP_SEPARATOR_";
function setDateDelimiter(delimiter) {
    DATE_DELIMITER = delimiter;
}
function setDBIntLength(len) {
    DB_INT_LENGTH = len;
    MAX_INTEGER = Math.pow(2, DB_INT_LENGTH - 1) - 1;
    MIN_INTEGER = -Math.pow(2, DB_INT_LENGTH - 1);
    MAX_SMALLINT = Math.pow(2, DB_INT_LENGTH / 4 - 1) - 1;
    MIN_SMALLINT = -Math.pow(2, DB_INT_LENGTH / 4 - 1);
}
function setVerbose(verbose) {
    VERBOSE = verbose;
}
function isVerbose() {
    return VERBOSE;
}
function log(value) {
    if (isVerbose()) {
        window.status = value;
    }
}
function isNetscape() {
    if (navigator.appName == "Netscape") {
        return true;
    } else {
        return false;
    }
}
function getElementOrder(field) {
    var i = 0;
    var order = 0;
    var elements = document.getElementsByName(field.name);
    var elementsCount = elements.length;
    for (i = 0; i < elementsCount; i++) {
        order++;
        if (elements[i] == field) {
            break;
        }
    }
    return order;
}

function isFirstElement(field) {
    var elements = document.getElementsByName(field.name);
    if (elements[0] == field) {
        return true;
    } else {
        return false;
    }
}
function getElementCount(fieldName) {
    var count = 0;
    count = document.getElementsByName(fieldName).length;
    return count;
}
function getByteLength(value) {
    var str;
    var count = 0;
    var valueCount = value.length;
    for (var i = 0; i < valueCount; i++) {
        str = escape(value.charAt(i));
        if (str.length == 6) {
            count = count + 2;
        } else {
            count = count + 1;
        }
    }
    return count;
}
function getTableElements(tableId) {
    var i = 0;
    var elements = new Array();
    var tempElements = null;
    var tbody;
    var index = 0;
    var tbodies = document.getElementById(tableId).tBodies;
    var tbodiesCount = tbodies.length;
    var tempElementsCount = 0;
    for (i = 0; i < tbodiesCount; i++) {
        tbody = tbodies.item(i);
        tempElements = tbody.getElementsByTagName("INPUT");
        tempElementsCount = tempElements.length;
        for (var j = 0; j < tempElementsCount; j++) {
            elements[index++] = tempElements[j];
        }
        tempElements = tbody.getElementsByTagName("SELECT");
        tempElementsCount = tempElements.length;
        for (var j = 0; j < tempElementsCount; j++) {
            elements[index++] = tempElements[j];
        }
        tempElements = tbody.getElementsByTagName("TEXTAREA");
        tempElementsCount = tempElements.length;
        for (var j = 0; j < tempElementsCount; j++) {
            elements[index++] = tempElements[j];
        }
    }
    return elements;
}
function leftTrim(value) {
    var re = /^\s*/;
    if (value == null) {
        return null;
    }
    return value.replace(re, "");
}
function rightTrim(value) {
    var re = /\s*$/;
    if (value == null) {
        return null;
    }
    return value.replace(re, "");
}
function trim(value) {
    return leftTrim(rightTrim(value));
}
function regExpTest(resource, re) {
    var result = false;
    if (resource == null || resource == "") {
        return false;
    }
    if (resource == re.exec(resource)) {
        result = true;
    }
    return result;
}
function replace(str, strFind, strReplaceWith) {
    var strReturn;
    var re = new RegExp(strFind, "g");
    if (str == null) {
        return null;
    }
    strReturn = str.replace(re, strReplaceWith);
    return strReturn;
}
function isEmptyField(field) {
    if (field.value == null || trim(field.value) == "") {
        return true;
    }
    return false;
}
function formatNumberToString(strValue) {
    strValue = trim(strValue);
    strValue = replace(strValue, ",", "");
    return strValue;
}
function formatNumberToDouble(strValue) {
    var dblValue = 0;
    strValue = formatNumberToString(strValue);
    dblValue = parseFloat(strValue);
    if (isNaN(dblValue)) {
        dblValue = 0;
    }
    return dblValue;
}
function fixNumber(cellStr) {
    if (parseFloat(cellStr) == 0) {
        return "0";
    }
    if (cellStr.indexOf(".") == -1) {
        return cellStr;
    }
    var x = parseFloat(cellStr);
    x += (parseFloat(cellStr) < 0) ? -5e-9 : 5e-9;

	// Chop the string to 4 decimal places.
    cellStr = "" + x;
    cellStr = cellStr.substring(0, 5 + cellStr.indexOf("."));

	// Remove trailing zeros beyond the decimal point.
    cellStrArr = cellStr.split("");
    for (k = cellStrArr.length - 1; k > 0 && cellStrArr[k] == "0"; --k) {
        cellStrArr[k] = "X";
    }
    cellStr = "";
    for (k = 0; k < cellStrArr.length && cellStrArr[k] != "X"; ++k) {
        cellStr += cellStrArr[k];
    }
    return cellStr;
}
function setFocus(field) {
    try {
        field.focus();
    }
    catch (E) {
    }
}
function showMessage(message) {
    alert(message);
}
function boundCheckBox(controlField, checkBoxField) {
    var count = 0;
    try {
        count = checkBoxField.length;
    }
    catch (E) {
    }
    if (isNaN(count)) {
	    if(checkBoxField.className!="readonlyCheckBox"){
        checkBoxField.checked = controlField.checked;
        }
    } else {
        for (var i = 0; i < count; i++) {
        	if(checkBoxField[i].className!="readonlyCheckBox"){
            checkBoxField[i].checked = controlField.checked;
        }
    }
}
}
function getElementIndexInForm(form, field) {
    var intElementIndex = -1;
    var elementsCount = form.elements.length;
    for (var i = 0; i < elementsCount; i++) {
        if (form.elements[i] == field) {
            intElementIndex = i;
            break;
        }
    }
    return intElementIndex;
}
function setSelect(field) {
    if (field.type != "hidden") {
        try {
            field.select();
        }
        catch (E) {
        }
    }
}
function isIE6() {
    if (navigator.appVersion.indexOf("MSIE 6") > -1) {
        return true;
    } else {
        return false;
    }
}

function isIE7() {
    if (navigator.appVersion.indexOf("MSIE 7") > -1) {
        return true;
    } else {
        return false;
    }
}

function bindField(source, target) {
    target.value = source.value;
}
function newString(str, times) {
    var value = "";
    for (var i = 0; i < times; i++) {
        value += str;
    }
    return value;
}
function printExcept(obj) {
    obj.style.display = "none";
    window.print();
    obj.style.display = "";
}
function postAction(form, action, target) {
    var oldTarget = form.target;
    if (target != null) {
        form.target = target;
    }
    form.action = action;
    form.submit();
    if (target != null) {
        form.target = oldTarget;
    }
    return true;
}
function postActionWithConfirm(form, action, message, target) {
    if (confirm(message)) {
        var oldTarget = form.target;
        if (target != null) {
            form.target = target;
        }
        form.action = action;
        form.submit();
        if (target != null) {
            form.target = oldTarget;
        }
        return true;
    } else {
        return false;
    }
}
function setFormDisabled(form) {
    if (form == null) {
        form = fm;
    }
    var element;
    for (var i = 0; i < form.elements.length; i++) {
        element = form.elements[i];
        if (element.type == "hidden") {
            continue;
        }
        if (element.name.indexOf("buttonControl") == 0) {
            continue;
        }
        if (element.name == "buttonSubmit") {
            element.style.display = "none";
            continue;
        }
        element.disabled = true;
    }
}
function getFirstElementValue(fieldName) {
    var field;
    if (getElementCount(fieldName) > 1) {
        field = document.getElementsByName(fieldName);
    } else {
        field = document.getElementsByName(fieldName)[0];
    }
    return field.value;
}
function openWindow(urlString, windowName, optionString) {
    var newWindow = window.open(urlString, windowName, optionString);
    try {
        newWindow.focus();
    }
    catch (E) {
    }
    return newWindow;
}
function exportResultDataToExcel(table) {
    var oXL;
    try {
        oXL = GetObject("", "Excel.Application");
    }
    catch (E) {
        try {
            oXL = new ActiveXObject("Excel.Application");
        }
        catch (E2) {
            showMessage("Please confirm:\n1.Microsoft Excel has been installed.\n2.Internet Options=>Security=>Setting \"Enable unsafe ActiveX\"");
            return;
        }
    }
    var oWB = oXL.Workbooks.Add();
    var oSheet = oWB.ActiveSheet;
    var Lenr = 1;
    var maxColumn = 0;
    for (var i = 0; i < Lenr; i++) {
        var Lenc = table.tHead.rows(i).cells.length;
        for (j = 0; j < Lenc; j++) {
            if (maxColumn < j) {
                maxColumn = j;
            }
            oSheet.Columns(j + 1).EntireColumn.NumberFormatLocal = "@";
            oSheet.Cells(i + 1, j + 1).value = table.tHead.rows(i).cells(j).innerText;
        }
    }
    Lenr = table.tBodies(0).rows.length;
    for (var i = 0; i < Lenr; i++) {
        var Lenc = table.tBodies(0).rows(i).cells.length;
        var j = 0;
        var value = table.tBodies(0).rows(i).cells(j).innerText;
        var pos = value.indexOf(" ");
        if (pos > -1) {
            value = trim(value.substring(pos));
        }
        oSheet.Cells(i + 2, j + 1).value = value;
        for (j = 1; j < Lenc; j++) {
            oSheet.Cells(i + 2, j + 1).value = table.tBodies(0).rows(i).cells(j).innerText;
        }
    }
    for (var i = 0; i < maxColumn; i++) {
        oSheet.Columns(i + 1).EntireColumn.AutoFit;
    }
    oXL.Visible = true;
}
function setSameElementValue(field) {
    if (getElementCount(field.name) > 1) {
        var fields = document.getElementsByName(field.name);
        for (var i = 0; i < fields.length; i++) {
            fields[i].value = field.value;
        }
    }
}
function readCookie(name) {
    var cookieValue = "";
    var search = name + "=";
    if (document.cookie.length > 0) {
        offset = document.cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = document.cookie.indexOf(";", offset);
            if (end == -1) {
                end = document.cookie.length;
            }
            cookieValue = unescape(document.cookie.substring(offset, end));
        }
    }
    return cookieValue;
}
function writeCookie(name, value, hours) {
    var expire = "";
    if (hours != null) {
        expire = new Date((new Date()).getTime() + hours * 3600000);
        expire = ";  expires=" + expire.toGMTString();
    }
    document.cookie = name + "=" + escape(value) + expire;
}

/**
 * unformat number
 * example:123,222.23 ==> 123322.23
 * @since 2006-09-01
 */
function unformatNumber(value){
  var retValue = replace(value+"", ",", "");
  var valueArray = retValue.split(".");
  if(valueArray.length>1 && valueArray[1]=="00"){
    retValue = valueArray[0];
  }
  return retValue;
}

/**
 * format number
 * example:123222.23 ==> 123,322.23
 * if has precision,the return value will have at least precision number after dot.
 * @since 2006-09-01
 */
function formatNumber(value,precision){
  value  =  value+"";
  var pos = value.indexOf('.');
  if(pos>-1){
    var firstValue=value.substring(0,pos);
    var lastValue=value.substring(pos+1);
    var  re=/(-?\d+)(\d{3})/
    while(re.test(firstValue)){
      firstValue=firstValue.replace(re,"$1,$2")
    }
    /*
    re=/(\d{3})(\d+)/
    while(re.test(lastValue)){
      lastValue=lastValue.replace(re,"$1,$2")
    }
    */
    value = firstValue + "." + lastValue;
  }else{
    var re=/(-?\d+)(\d{3})/
    while(re.test(value)){
      value=value.replace(re,"$1,$2")
    } 
  } 
  if(precision!=undefined && !isNaN(precision)){
  	var pos = value.indexOf('.'); 
  	if(pos==-1){
  		value+="."; 
  		pos = value.indexOf('.'); 
  	}
  	
  	var len = value.length-pos-1;
  	
		for(var i=len;i<precision;i++){
			value+="0";
		}  	
  }
  return  value;
}

/**
 * get multi-line field object.
 * @param statement: fm.ClassCode[]
 * @param elementOrder: 3
 * @return: The third ClassCode field object of this page form.
 * @since 2006-09-01
 */
function getElementObjectByOrder(statement, elementOrder) {
  var object = null;
  if(statement.indexOf("[]")==-1){
    try{
      object = eval(statement);
    }catch (E) { 
      object = statement;
    }
  }else{
    var startPos = statement.indexOf("[");
    var endPos = statement.indexOf("]");
    if(startPos==endPos-1){
      statement = statement.substring(0,startPos+1) + elementOrder + statement.substring(endPos);
    }
    try{
      object = eval(statement);
    }catch (E) { 
      object = statement;
    }
  }
  return object;
}
function upperCaseFirstChar(str){
  if(str == null || str.length < 1 ){
   return str;  
  }
  var convertStr = str.substring(0,1).toUpperCase()+str.substring(1);        
  return convertStr ;    
}

function lowerCaseFirstChar(str){
  if(str == null || str.length < 1 ){
   return str;  
  }
  var convertStr = str.substring(0,1).toLowerCase()+str.substring(1);        
  return convertStr ;    
}

function getElementByTagName(object,elementName,tagName){ 
  var tempField = object.getElementsByTagName(tagName); 
  for (var i = 0; i <tempField.length; i++) {
    if(tempField[i].name == elementName){      
      return tempField[i]; 
    }
  } 
  return null;
}  
function copyOptions(targetSelect,sourceSelect){
  targetSelect.options.length=0;
  for(var i=0; i<sourceSelect.length; i++){
    addOption(targetSelect, sourceSelect.options[i].text, sourceSelect.options[i].value);
  }
}
function addOption(targetSelect,text,value){
  targetSelect.options[targetSelect.length]=new Option(text,value);
}

function setChildElementsDisabled(object,disabledValue){
  var fields = object.getElementsByTagName("INPUT"); 
  var i= 0;
  for (i = 0; i <fields.length; i++) {
    fields[i].disabled = disabledValue;
  }
  fields = object.getElementsByTagName("SELECT"); 
  for (i = 0; i <fields.length; i++) {
    fields[i].disabled = disabledValue;
  } 
  fields = object.getElementsByTagName("TEXTAREA"); 
  for (i = 0; i <fields.length; i++) {
    fields[i].disabled = disabledValue;
  }  
}
//show/hide advanced condition.
function showAdvanced(controlNode,controlButton){ 
	if(controlNode.style.display=="none"){
		setChildElementsDisabled(controlNode,false);
		controlNode.style.display = "";
	}else{
		controlNode.style.display = "none";
		setChildElementsDisabled(controlNode,true);
	}	
}

function round(number,precision)
{
  if(isNaN(number))
    number = 0;
  var prec = Math.pow(10,precision);
  var result = Math.round( number * prec) ;
  result = result/prec;
  return result;
}


function point(number,precision)
{
  if(isNaN(number))
    number = 0;
  var result = number.toString();
  if(result.indexOf(".")==-1)
    result = result + ".";

  result = result + newString("0",precision);
  result = result.substring(0,precision + result.indexOf(".") + 1);
  return result;
}

/**
 * ���ܣ�����������ֻ����ͬʱ��CSS�����Ա��ֻ��
 * return true/false
 */
function readonlyAllInput(){
  var testStr = "" ;
  var tempElements = null;

  for(i=0; i<document.all.length; i++){
    //alert(document.all(i).tagName);
	  /*�����ж�Ӱ��϶�ҳ�棬�軹ԭ�޸�  last modify by zhangdong 2014.1.23
	  if(document.all(i).name!= null && document.all(i).name.indexOf("prpLReceivers")==0){
		  continue;
	  }*/
    if(document.all(i).tagName=="INPUT"){
        tempElements = document.all(i);
        //���������Ϊֻ��
        if(tempElements.type=="text"){
	    	tempElements.style.fontSize="9pt";
	    	tempElements.style.borderTop = "none";
	    	tempElements.style.borderBottom = "none";
	    	tempElements.style.borderRight= "none";
	    	tempElements.style.borderLeft = "none" ;
	    	//tempElements.style.width="80%";
	    	tempElements.style.color = "#000000";
	    	tempElements.style.backgroundColor = "#F4F9FF";
        	tempElements.readOnly=true;
        }
        //���������Ϊֻ��
        if(tempElements.type=="radio"){
        	tempElements.disabled=true;
        }
        
        //�����¼�
        tempElements.ondblclick = "return true";
        tempElements.onkeyup = "return true";
    }
    //��ѡ�����Ϊֻ��
    if(document.all(i).tagName=="SELECT"){
    	tempElements = document.all(i);
    	tempElements.disabled = true;
    }
    //��ѡ�����Ϊֻ��
    if(document.all(i).tagName=="TEXTAREA"){
    	tempElements = document.all(i);
    	tempElements.readOnly=true;
    	tempElements.style.backgroundColor = "RGB(247,247,247)";
    }
    

  }
}
/**
 * ���ܣ���ָ����������ֻ����ͬʱ��CSS�����Ա��ֻ��
 * return true/false
 */
function readonlyUserInput(userInput){
  var testStr = "" ;
  var tempElements = null;

  for(i=0; i<userInput.all.length; i++){
    if(userInput.all(i).tagName=="INPUT"){
        tempElements = userInput.all(i);
        //���������Ϊֻ��
        if(tempElements.type=="text"){
	    	tempElements.style.fontSize="9pt";
	    	tempElements.style.borderTop = "none";
	    	tempElements.style.borderBottom = "none";
	    	tempElements.style.borderRight= "none";
	    	tempElements.style.borderLeft = "none" ;
	    	//tempElements.style.width="80%";
	    	tempElements.style.color = "#000000";
	    	tempElements.style.backgroundColor = "#F4F9FF";
        	tempElements.readOnly=true;
        }
        //���������Ϊֻ��
        if(tempElements.type=="radio"){
        	tempElements.disabled=true;
        }
        
        //�����¼�
        tempElements.ondblclick = "return true";
        tempElements.onkeyup = "return true";
    }
    //��ѡ�����Ϊֻ��
    if(userInput.all(i).tagName=="SELECT"){
    	tempElements = userInput.all(i);
    	tempElements.disabled = true;
    }
    //��ѡ�����Ϊֻ��
    if(userInput.all(i).tagName=="TEXTAREA"){
    	tempElements = userInput.all(i);
    	tempElements.readOnly=true;
    	tempElements.style.backgroundColor = "RGB(247,247,247)";
    }
    

  }
}
function checkIsInteger(field){
    var str = field.value;
    if(str == ""|| str ==null){
        return false;
    }
    if(/^(\-?)(\d+)$/.test(str)){
      if(str<0){
         field.value = "";
        return false;
      }
        return true;
    }else{
        field.value = "";
        return false;
    }
  }
 
 function findMessageList(registNo,nodeType){
 	var inputObject;
	var outputObject;
	if(nodeType != null && (nodeType == "Regist" || nodeType == "CaseDispatch")){
		inputObject = "special";
	}
	var messageInfo = registNo + "," + nodeType;
	dwrInvokeData("findMessageList",messageInfo,"callbackSetButtonFlash",inputObject,outputObject,"",true);
 }
 
 function callbackSetButtonFlash(inputObject,outputObject,returnObject){
 	var pmBox = document.getElementById("pmBox");
 	if(returnObject == true && pmBox != null){
 		pmBox.style.backgroundcolor = "transparent";
 		if(inputObject == "special"){
 			pmBox.style.backgroundImage="url(pages/image/btbg_changeColor2.gif)";
 		}else{
 			pmBox.style.backgroundImage="url(../pages/image/btbg_changeColor2.gif)";
 		}
 	}
 }
 
 //���ϸ�����
 function incompetent(field){
 	var value = field.value;
 	if(value.indexOf("*")>-1 || value.indexOf("%")>-1 ){
 		return true;
 	}
 	return false;
 }
 
 //����ҳ��input���Ƿ��
 /**function checkEmpty(){
 	var objArray=document.getElementsByTagName("input");
	for(var i=0;i<objArray.length;i++){
		if(objArray[i].type=="text"){
			if(objArray[i].value!="" && objArray[i].value!=null){
				if(objArray[i].name.indexOf("begindate") == "-1" && objArray[i].name.indexOf("enddate") == "-1"
				&& objArray[i].name.indexOf("damageStartDate") == "-1" && objArray[i].name.indexOf("damageEndDate") == "-1" ){
					return false;
				}
			}
		}
	}
	alert("�ܱ�Ǹ����������һ����ѯ������");
	return true;
 }*/
 
 //checkbox������ͬʱѡ��2��������
 function checkLength(stateList){
 /*
 	var stateList = document.getElementsByName("state");
 	if(stateList.length < 1){
 		stateList = document.getElementsByName("states");
 	}
 	var j = 0 ;
 	for(var index=0; index<stateList.length; index++) {
 		if(stateList[index].checked){
 			j++;
 		}
 		if(j > 1){
 			alert("�ܱ�Ǹ������״̬��ʱֻ����ѡ��һ����");
 			return false;
 		}
 	}
 	*/
 }
 // ʱ��������֤
 function checkInputDate(indate,outdate){
 	//if(indate == "" || outdate == "" ){
 	//	alert("����ʱ�������ʱ�䲻��Ϊ�գ�");
 	//	return false;
 	//}
 	if(indate!=""&&outdate!=""){
		if(!(checkDate(indate) && checkDate(outdate))){
			alert("ʱ���ʽ�������");
			return false;
		}else{
			return true;
		}
		//var dateNum = daysBetween(indate,outdate);
		//if(dateNum>182){
		//	alert("����ʱ�������ʱ��Ĳ�ܳ�������!");
		//	return false;
		//}else{
		//	return true;
		//}
 	}else{
 		return true;
 	}

}
//��������ʱ��Ĳ�
function daysBetween(DateOne,DateTwo)   
{    
    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));   
    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);   
    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));   
    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));   
    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);   
    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));   
    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);    
    return Math.abs(cha);   
} 
//ʱ���ʽYYYY-MM-DD��֤
function checkDate(theDate) 
{ 
var reg = /^\d{4}-((0{0,1}[1-9]{1})|(1[0-2]{1}))-((0{0,1}[1-9]{1})|([1-2]{1}[0-9]{1})|(3[0-1]{1}))$/;   
  var result=true; 
  if(!reg.test(theDate)) 
    result = false; 
  else{ 
    var arr_hd=theDate.split("-"); 
    var dateTmp; 
if(arr_hd[1].length != 2) 
  return false; 
    if(arr_hd[2].length != 2) 
  return false; 
    dateTmp= new Date(arr_hd[0],parseFloat(arr_hd[1])-1,parseFloat(arr_hd[2])); 
    if(dateTmp.getFullYear()!=parseFloat(arr_hd[0]) 
       || dateTmp.getMonth()!=parseFloat(arr_hd[1]) -1 
        || dateTmp.getDate()!=parseFloat(arr_hd[2])){ 
        result = false; 
    } 
  } 
  return result; 
} 

 