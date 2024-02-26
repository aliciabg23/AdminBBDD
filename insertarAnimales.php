<?php
    if(isset($_REQUEST['tabla'])) { $tabla=$_REQUEST['tabla']; } else {$marca='sin nombre';}
    if(isset($_REQUEST['nombre'])) { $nombre=$_REQUEST['nombre']; } else {$marca='sin nombre';}
    if(isset($_REQUEST['latitud'])) { $latitud=$_REQUEST['latitud']; } else {$latitud='sin latitud';}
    if(isset($_REQUEST['longitud'])) { $longitud=$_REQUEST['longitud']; } else {$longitud='sin longitud';}
    if(isset($_REQUEST['descripcion'])) { $descripcion=$_REQUEST['descripcion']; } else {$descripcion='sin descripcion';}

    // $conexionBD = mysqli_connect("localhost", "root", "", "litterator_justo");
    $conexionBD = mysqli_connect("PMYSQL118.dns-servicio.com:3306", "bbdd_procesos", "L1tter@tor", "7150493_bbdd_procesos") or die ('No puedo conectar con la BBDD '.mysqli_error($conexionBD));

    $sql = "INSERT INTO $tabla
            (nombre, latitud, longitud, descripcion)
            VALUES('{$nombre}', $latitud, $longitud, '{$descripcion}')";
    $consulta = mysqli_query($conexionBD, $sql);
    
    echo "Agregado $nombre";

    mysqli_close($conexionBD);
    
?>