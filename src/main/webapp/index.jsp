<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Zadanie 1</title>
	
		<style>
		    .input-container {
		        padding: 0;
		        margin-bottom: 5px;
		    }
		</style>
	</head>

	<body>
	    <form action="main" method="get">
	    	<div class="input-container">
		        <label>Wnioskowana kwota kredytu:</label>
		        <input type="number" step="0.01" id="kredyt" name="kredyt" />
	        </div>
	        
	        <div class="input-container">
		        <label>Ilość rat:</label>
		        <input type="number" id="raty" name="raty" />
		    </div>
	        
	        <div class="input-container">
		        <label>Oprocentowanie:</label>
		        <input type="number" step="0.01" id="oprocentowanie" name="oprocentowanie" />
	        </div>
	        
	        <div class="input-container">
		        <label>Opłata stała:</label>
		        <input type="number" step="0.01" id="oplata" name="oplata" />
	        </div>
	        
	        <div class="input-container">
		        <label>Rodzaj rat:</label>
		        <select id="rodzaj" name="rodzaj">
		        	<option value="stale">Stałe</option>
		        	<option value="malejace">Malejące</option>
		        </select>
	        </div>

	        <div class="input-container">
	        	<input type="submit" value="Wyślij" />
	        </div>
	    </form>
	</body>
</html>