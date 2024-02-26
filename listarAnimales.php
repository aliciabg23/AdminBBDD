<?php
    if(isset($_REQUEST['tabla'])) {
        $tabla = $_REQUEST['tabla'];
    } else {
        $tabla = 'sin_nombre'; // Corrected variable name from $marca to $tabla
    }

    $conexionBD = mysqli_connect("localhost", "root", "", "animales");

    if (!$conexionBD) {
        die("Error al conectar con la base de datos: " . mysqli_connect_error());
    }

    $sql = "SELECT * FROM $tabla";
    $consulta = mysqli_query($conexionBD, $sql) or die(mysqli_error($conexionBD));

    while ($row = mysqli_fetch_assoc($consulta)) {
        echo $row["id"] . "/" . $row["nombre"] . "/" . $row["latitud"] . "/" . $row["longitud"] . "/" . $row["descripcion"] . "\n";
    }

    mysqli_close($conexionBD);
?>