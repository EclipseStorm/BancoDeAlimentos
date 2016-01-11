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
		$query = "SELECT * FROM entidad ORDER BY ID_entidad ASC";
		$res = makeQuery($query);
		echo convertToJSON($res);
	}

	function getAlimentos() {
		$query = "SELECT * FROM alimento ORDER BY nombre";
		$res = makeQuery($query);
		echo convertToJSON($res);
	}

	function getPrio_Alimento($id_entidad) {
		$query = "SELECT alimento.nombre 
FROM `alimento` LEFT JOIN `prioridad` ON alimento.ID_alimento=prioridad.ID_alimento 
WHERE prioridad.ID_entidad = ". $id_entidad." ORDER BY prioridad.prioridad";
		$res = makeQuery($query);
		echo convertToJSON($res);
	}

	function getPrio_Entidad($id_alimento) {
		$query = "SELECT entidad.nombre 
FROM `entidad` LEFT JOIN `prioridad` ON entidad.ID_entidad=prioridad.ID_entidad 
WHERE prioridad.ID_alimento = ".$id_alimento." ORDER BY prioridad.prioridad";
		$res = makeQuery($query);
		echo convertToJSON($res);
	}

	function getPrio_General() {
		$query = "";
		$res = makeQuery($query);
		echo convertToJSON($res);
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
			getPrio_Alimento($_GET['id_entidad']);
			break;

		case 'prioridad_entidad':
			getPrio_Entidad($_GET['id_alimento']);
			break;

		case 'prioridad_general':
			getPrio_General();
			break;
		
		default:
			break;
	}

?>