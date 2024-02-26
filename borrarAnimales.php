<?php
    if(isset($_REQUEST['tabla'])) { $tabla=$_REQUEST['tabla']; } else {$marca='sin nombre';}

    if (isset ($_REQUEST['id'])) {$id = $_REQUEST['id']; } else {$id ='no id';}
 	
    $conexionBD = mysqli_connect("PMYSQL118.dns-servicio.com:3306", "bbdd_procesos", "L1tter@tor", "7150493_bbdd_procesos") or die ('No puedo conectar con la BBDD '.mysqli_error($conexionBD));
	
	$sql = "DELETE FROM $tabla WHERE id='$id'";
	$consulta = mysqli_query($conexionBD, $sql);
	
	echo "Mascota eliminada"
?>