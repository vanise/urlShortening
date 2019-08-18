<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="/resources/js/shortUrl.js"></script>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<title>Shortening Url</title>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
            	<a class="navbar-brand" href="#">KAKAO-PAY ShorteningURL</a>
			</div>
          	<div id="navbar" class="navbar-collapse collapse">
            	<ul class="nav navbar-nav">
              		<li class="menu active"><a href="javascript:;" class="url">URL TO ShorteningURL</a></li>
              		<li class="menu"><a href="javascript:;" class="short">ShorteningURL TO URL</a></li>
            	</ul>
          	</div><!--/.nav-collapse -->
		</div><!--/.container-fluid -->
	</nav>
	<form id="form">
		<div class="panel panel-primary" style="width:80%;margin-top:20px;">
			<div class="panel-heading convertTitle">URL TO ShorteningURL</div>
			</div>
		</div>
		<!-- <p style="margin-left:10px; font-weight:bold;">url to shortening</p> -->
		<div class="form-horizontal" style="margin-top:-15px;">
			<input type="text" name="orgUrl" class="form-control" placeholder="Enter url to change." style="width:75%;display:inline-block" id="url"/>
		  	<button type="button" class="btn btn-primary" id="chgShotUrl" style="margin-left:5px;margin-top:-2px;min-height:36px;"><i class="glyphicon glyphicon-refresh"></i></button>
		</div>
		<div class="alert alert-success" id="megAlert" style="width:80%;margin-top:5px;display:none;">
		</div>
		<div class="panel panel-primary" style="width:80%;">
			<div class="panel-heading">Convert URL Result</div>
			<div class="panel-body" id="resultUrl" style="word-wrap:break-word;"></div>
		</div>
	</form>

</body>
</html>