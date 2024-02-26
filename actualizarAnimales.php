<?php
    if(isset($_REQUEST['tabla'])) { $tabla=$_REQUEST['tabla']; } else {$marca='sin nombre';}

    if (isset($_REQUEST['id'])) {$id = $_REQUEST['id']; } else {$id = 'sin id';}
    if (isset($_REQUEST['nombre'])) {$nombre = $_REQUEST['nombre']; } else {$nombre = 'sin nombre';}
    if (isset($_REQUEST['latitud'])) {$latitud = $_REQUEST['latitud']; } else {$latitud = 0.0;}
    if (isset($_REQUEST['longitud'])) {$longitud = $_REQUEST['longitud'];} else {$longitud = 0.0;}
    if (isset($_REQUEST['descripcion'])) { $descripcion = $_REQUEST['descripcion'];} else {$descripcion = 'sin descripcion';}

    $conexionBD = mysqli_connect("PMYSQL118.dns-servicio.com:3306", "bbdd_procesos", "L1tter@tor", "7150493_bbdd_procesos") or die ('No puedo conectar con la BBDD '.mysqli_error($conexionBD));

    $sql = "UPDATE $tabla 
            SET nombre='{$nombre}', latitud={$latitud}, longitud={$longitud}, descripcion='{$descripcion}'
            WHERE id={$id}";


    if (mysqli_query($conexionBD, $sql)) {
        echo "Actualizado correctamente";
    } else {
        echo -1;
    }

?>