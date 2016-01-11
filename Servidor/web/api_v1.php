<?php

	function connect() {
		require_once("config.php");
		global $c;
		$c = mysql_connect($server, $user, $pass) or die(mysql_error());
		mysql_select_db($db, $c) or die(mysql_error());
	}

	function makeQuery($q) {
		global $c;
		$result = mysql_query($q);
		$rows = array();
		while($r = mysql_fetch_assoc($result)){
			$rows[] = array_map('utf8_encode', $r);
		}
		return $rows;
	}

	function convertToJSON($d) {
		return json_encode($d);
	}

	function getEntidades() {
		$query = "SELECT * FROM entidad ORDER BY ID_entidad ASC;";
		$res = makeQuery($query);
		echo convertToJSON($res);
	}

	function getAlimentos() {
		$query = "";
		makeQuery($query);
	}

	function getPrio_Alimento() {
		$query = "";
		makeQuery($query);
	}

	function getPrio_Entidad() {
		$query = "";
		makeQuery($query);
	}

	function getPrio_General() {
		$query = "";
		makeQuery($query);
	}


	connect();

	switch ($_GET['q']) {
		case 'entidades':
			getEntidades();
			break;

		case 'alimentos':
			getAlimentos();
			break;

		case 'prioridad_alimento':
			getPrio_Alimento();
			break;

		case 'prioridad_entidad':
			getPrio_Entidad();
			break;

		case 'prioridad_general':
			getPrio_General();
			break;
		
		default:
			break;
	}

?>