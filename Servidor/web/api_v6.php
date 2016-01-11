<?php

	function connect() {
		require_once("config.php");
		global $c;
		$c = mysql_connect($server, $user, $pass) or die(mysql_error());
		mysql_select_db($db, $c) or die(mysql_error());
	}

	function makeQuery($q) {
		global $c;
		$result = mysql_query($q) or die ($q);
		$rows = array();
		while($r = mysql_fetch_assoc($result)){
			$rows[] = array_map('utf8_encode', $r);
		}
		return $rows;
	}
	
	function makeQuery2($q) {
		global $c;
		$result = mysql_query($q) or die ($q);
		$rows = array();
		while($r = mysql_fetch_row($result)){
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
		$query = "SELECT * 
FROM `alimento` LEFT JOIN `prioridad` ON alimento.ID_alimento=prioridad.ID_alimento 
WHERE prioridad.ID_entidad = ". $id_entidad." ORDER BY prioridad.prioridad";
		$res = makeQuery($query);
		echo convertToJSON($res);
	}

	function getPrio_Entidad($id_alimento) {
		$query = "SELECT * 
FROM `entidad` LEFT JOIN `prioridad` ON entidad.ID_entidad=prioridad.ID_entidad 
WHERE prioridad.ID_alimento = ".$id_alimento." ORDER BY prioridad.prioridad";
		$res = makeQuery($query);
		echo convertToJSON($res);
	}

	function getPrio_General() {
		$query = "SELECT nombre FROM alimento ORDER BY ID_alimento";
		$alimentos = makeQuery($query);
		$query = "SELECT ID_entidad FROM entidad ORDER BY ID_entidad";
		$entidades = makeQuery($query);
		$prioridad=array();
		for ($x = 0; $x < count($alimentos); $x++) {
			$id_alimento = $x + 1;
			
			$query = "SELECT prioridad FROM prioridad WHERE ID_alimento=".$id_alimento;
			$res = makeQuery2($query);
			$prioridad[$id_alimento]=0;
			foreach ($res as $r){
				$prioridad[$id_alimento]+= $r[0];
			}
			if($prioridad[$id_alimento]== 0){
				$prioridad[$id_alimento] = 30;
			}
			// Prioridad tiene en la posición ID_alimento el valor de prioridad de ese alimento. A mayor valor, mayor prioridad.
			$prioridad[$id_alimento] = count($alimentos)*count($entidades) - $prioridad[$id_alimento];
		//	$resultado [$x] = array("nombre" => $alimentos[$x]["nombre"], "ID_alimento" => $x);
			
		}
		arsort($prioridad);
		print_r ($prioridad);
		for ($x = 0; $x < count($prioridad); $x++) {
			
			$resultado[$x] = array("nombre" => $alimentos[$x]["nombre"], "ID_alimento" => $x );			
		}
		//arsort($resultado);
		//$nombres = array_column($resultado, 'nombre');
		//print_r ($resultado);
		echo convertToJSON($resultado);
	}

if (!isset($_GET['q'])){
	die("Error de q");
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