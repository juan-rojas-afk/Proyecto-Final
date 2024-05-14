informe sobre el funcionamiento del software

El software del parqueadero está diseñado para gestionar el estacionamiento de vehículos, calcular los costos de estacionamiento y generar informes diarios y mensuales de ingresos. Está compuesto por varias clases que interactúan entre sí para lograr estas funcionalidades:

Parqueadero: Representa el parqueadero físico con una matriz de puestos de estacionamiento. Permite estacionar vehículos, calcular costos de estacionamiento y generar informes de ingresos.

Vehiculo: Es una clase base abstracta que representa un vehículo genérico con atributos como placa, modelo y propietario. Tiene dos clases derivadas: Carro y Moto, que heredan de ella.

ParqueaderoException: Es una excepción personalizada que se utiliza para manejar errores específicos relacionados con el parqueadero, como errores de formato de placa, límites fuera de rango y problemas con el nombre del propietario.

ErrorLogger: Esta clase se encarga de registrar los errores que puedan ocurrir durante la ejecución del programa, lo que facilita la depuración y el mantenimiento del software.

Aplicación: Es la clase principal que contiene el método main. Se encarga de interactuar con el usuario a través de la consola, gestionar las entradas del usuario y controlar el flujo general del programa.

En resumen, el software del parqueadero ofrece una solución completa para la gestión de estacionamientos, con capacidad para estacionar diferentes tipos de vehículos, calcular costos de estacionamiento y generar informes de ingresos para una administración eficiente del negocio.# Proyecto-Final
