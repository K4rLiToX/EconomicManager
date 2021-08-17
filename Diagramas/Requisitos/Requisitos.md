# Requisitos funcionales
## Requisito 1: Cuenta

** Requsito 1.1: Registro de Usuario

Descripción: el sistema pide al usuario los datos de inicio de sesión. Se deberá de comprobar que la información es válida.

** Requisito 1.2: Login

Descripción: el sistema pide el nombre de usuario y la contraseña.

** Requisito 1.3: Logout

Descripción: el sistema da la opcion de cerrar la sesión.

** Requisito 1.4: Eliminar Cuenta

Descripción: el sistema ofrece la posibilidad de elimiar la cuenta con todos sus datos relacionados.

## Requisito 2: Introducción de Gastos Mensuales

**Requisito 2.1: Gastos por Empleados**

Descripción: el sistema, dentro de la introducción de gastos mensuales, pide el número de empleados, su puesto y su salario y cuanto
le cuesta a la empresa tenerlo en su plantilla (seguro).

**Requisito 2.2: Gastos por Impuestos de Sociedades/Autónomo**

Descripción: el sistema pide la cantidad a pagar por ser empresa/autónomo.

**Requisito 2.3: Gastos por Impuestos (Luz, agua, internet,..)**

Descripción: el sistema pide la cantidad a pagar por los distintos impuestos obligatorios.

**Requisito 2.4: Pagos Pendientes**

Descripción: el sistema, a la vez que pide el tipo de gasto y la cantidad, da la opción de introducir 
en qué fecha se tiene que realizar el pago.

## Requisito 3: Introducción de Ingresos Mensuales

Descripción: el sistema le pide al usuario el tipo de ingreso (de donde viene) y la cantidad.

## Requisito 4: Cáculo del Beneficio

Descripción: con los datos de ingresos y gastos, el sistema calcula los beneficios.

## Requisito 5: Visualización de Datos

** Requisito 5.1: Gráficas

Descripción: se permite al usuario visualizar los datos de X meses en formato gráfica.

** Requisito 5.2: Tablas

Descripción: se permite al usuario visualizar los datos de X meses en formato tabular.

## Requisito 6: Exportación a PDF

Descripción: los datos se podrán exportar a formato PDF.

## Requisito 7: Modificación de Datos

Descripción: el usuario, después de introducir los datos iniciales, si lo desea y en cualquier momento, puede cambiar los datos ingresados y el sistema se ajustará a los nuevos cambios.

## Requisito 8: Calendario de Eventos

Descripción: el sistema ofrece un calendario para apuntar eventos importantes.

## Requisito 9: Divisa

** Requisito 9.1: Elección de Moneda

Descripción: el usuario podrá elegir la divisa a utilizar.

** Requisito 9.2: Conversor

Descripción: el sistema dispondrá de un conversor de divisa.

## Requisito 10: Hoja de Clientes

Descripción: el sistema permite al usuario mantener una hoja de clientes con la que se pueda guardar el historial de interacciones económicas.

## Requisito 11: Emisión de Facturas

Descripción: el sistema permite al usuario emitir facturas a los clientes.

# Requisitos no funcionales
## Requisito 1: Lenguaje de Programación

Descripción: Java

## Requisito 2: Privacidad

Descripción: el sistema asegura una seguridad mínima y no permite al usuario ver sus datos y no los de otros.

## Requisito 3: Tiempo de Respuesta (Estimación)

Descripción: como máximo 10 segundos para la respuesta.

## Requisito 4: Capacidad de Almacenamiento

Descripción: el límite de la base de datos a utilizar

## Requisito 5: Verificación de Credenciales

Descripción: si el nombre de usuario o contraseña son incorrectos 3 veces, el sistema se bloquea durante un tiempo.

## Requsito 6: Estimación de Fallos

Descripción: el sistema realiza cálculos, por lo tanto, debido al redondeo de cifras, el resultado final podría variar del real.

## Requisito 7: Inactividad

Descripción: tras 10 minutos de inactividad el sistema cierra la sesión automáticamente.

