<!--
Validation 체크 (표준)
-->
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
<script type="text/javascript" src="prototype.js"></script>
<script type="text/javascript" src="ValidationExample.js"></script>

<script>
	function doInsert()
	{
		if(document.forms[0].isValidForm() == "true") {
         alert("Y");
		} else {
			alert("N");
		}
	}
</script>
</head>

<body>
<form name="test_frm">
<div>
number 형 체크<br>
&ltinput type="text" name="TEST_1" id="TEST_1" dataType="number" dispName="NUMBER"&gt <br>
<input type="text" name="TEST_1" id="TEST_1" dataType="number" dispName="NUMBER">
</div>
<hr>
<div>
integer 형 체크(+,-부호가능)<br>
&ltinput type="text" name="TEST_2" id="TEST_2" dataType="integer" dispName="integer"&gt <br>
<input type="text" name="TEST_2" id="TEST_2" dataType="integer" dispName="integer">
</div>
<hr>
<div>
float 형 체크<br>
&ltinput type="text" name="TEST_3" id="TEST_3" dataType="float" dispName="실수형"&gt <br>
<input type="text" name="TEST_3" id="TEST_3" dataType="float" dispName="실수형">
</div>
<hr>
<div>
email<br>
&ltinput type="text" name="TEST_4" id="TEST_4" dataType="email" dispName="E-Mail"&gt <br>
<input type="text" name="TEST_4" id="TEST_4" dataType="email" dispName="E-Mail">
</div>
<hr>
<div>
숫자형 범위(number,integer,float)<br>
&ltinput type="text" name="TEST_5" id="TEST_5" dataType="number" minValue="5" dispName="숫자"&gt <br>
<input type="text" name="TEST_5" id="TEST_5" dataType="number" minValue="5" dispName="숫자">
</div>
<hr>
<div>
숫자형 범위(number,integer,float)(<br>
&ltinput type="text" name="TEST_6" id="TEST_6" dataType="number" minValue="5" maxValue="10" dispName="숫자"&gt <br>
<input type="text" name="TEST_6" id="TEST_6" dataType="number" minValue="5" maxValue="10" dispName="숫자">
</div>
<hr>
 
<div>
문자형 범위(textarea, text, password, file)<br>
&ltinput type="text" name="TEST_7" id="TEST_7" dataType="text" minLen="5" maxLen="10" dispName="문자"&gt <br>
&lttextarea rows="5" cols="80" name="TEST_8" id="TEST_8" minLen="5" maxLen="10" notNull dispName="textArea"&gt&lt/textarea&gt<br>

<input type="text" name="TEST_7" id="TEST_7" dataType="text" minLen="5" maxLen="10" dispName="문자">
<textarea rows="5" cols="80" name="TEST_8" id="TEST_8" minLen="5" maxLen="10" notNull dispName="textArea"></textarea>
</div>
<hr>
<input type="button" onclick="doInsert()" value="전송"> 
</form>

</body>

</html>


