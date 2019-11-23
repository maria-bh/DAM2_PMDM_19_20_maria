# DAM2_PMDM_19_20
 Este repositorio contiene todos los ejemplos y ejercicios vistos en clase para la asignatura de PMDM del ciclo Desarrollo de Aplicaciones Multiplataforma (DAM2)
 
 ## PrimeraAplicacion2019
 Es la primera aplicación que hemos estado haciendo estos primeros días del curso donde se introducen conceptos básicos como:
 * Activity
 * EditText
 * TextView
 * Button
 * Toast
 * Ocultar y mostrar una view ("visibility: VISIBLE,INVISIBLE,GONE")
 * Guardar el estado al girar la pantalla: onSaveInstanceState()/onRestoreInstanceState()
 * Diferentes formas de programar el click de los botones
 * Lanzar una nueva pantalla (uso de los Intents) pasando datos (el nombre de una persona, por ej.)
 * Internacionalización o textos en diferentes idiomas: español e inglés (strings.xml)
 * Recursos (carpeta /res)
 
 La funcionalidad de esta aplicación consiste en pedir un nombre al usuario y luego tenemos un botón que permite mostrar un mensaje de saludo usando Toast; también tenemos un botón OCULTAR/MOSTRAR que permite ocultar el texto o bien mostrarlo según si está oculto o no (además cambia el texto del botón para indicar qué hace ese botón); si giramos la pantalla del dispositivo el texto se mantiene en el mismo estado que estuviese (oculto o visible); además hemos añadido un tercer botón que saluda abriendo una nueva pantalla y mostrando el nombre del usuario.
 
 Esta aplicación la he descompuesto en 3 versiones diferentes, para que veais claramente las tres formas posibles de programar el evento "click" de los botones:
 * **PrimeraAplicación2019v1**. Esta versión implementa todos los "clicks" de los botones usando OnClickListener mediante una clase anónima; es decir, instanciándolo en cada botón.
 
 * **PrimeraAplicación2019v2**. Esta versión implementa los "clicks" de los botones implementando el interface OnClickListener en la propia clase MainActivity (implements OnClickListener). Al pulsar sobre cualquier botón se llamará al mismo método onClick().
 
 * **PrimeraAplicacion2019v3**. Esta versión implementa los "clicks" de los botones usando la propiedad "onClick" del XML del layout de la Activity. No usa escuchadores (listeners).
 
 
 
 ## UT3: Activities
 Todos los ejercicios de esta unidad están relacionados con el manejo de las activities.
 
 * **Ejer3ActivityDevolverDatosFactorial**. Contiene el proyecto Android Studio que resuelve el ejercicio. Es una propuesta de solución. Se trabaja con dos activities y es la segunda la que realiza la petición del número y el cálculo del factorial, devolviendo su resultado a la primera activity.
 
 ## UT4: Diseño de la interfaz de usuario: Vistas y Layouts
 Todos los ejercicios de esta unidad están relacionados con el diseño de la interfaz gráfica de usuario.
 
 * **SolucionEj2LayoutsVisualSencillos**. Contiene la solución del ejercicio "Ej2-Ejercicios sencillos visual layouts" que os aparece en la plataforma como tarea. El proyecto Android Studio que contiene la solución lo he estructurado en **varios módulos cada uno de ellos responde al uso de un tipo de Layout concreto: LinearLayout, RelativeLayout,...**. Cuando ejecutéis uno de los módulos disponéis de un menú en la ActionBar que permite cambiar de un layout a otro para ver el resultado en ejecución.
 
 * **SolEjerRadioButtonSencillo**. Contiene la solución del ejercicio "Ejercicio simple con RadioButtons" que permite convertir de grados Celsius a Fahrenheit. Incluye 2 soluciones diferentes en el mismo proyecto implementadas en diferentes módulos:
 
     * ***solucionsinbotoncalcular***: esta solución se ha hecho usando OnCheckedChangeListener para detectar cuándo se pulsan los RadioButton. Y automáticamente en ese momento se hace la conversión.
 
     * ***solucionusandobotoncalcular***: esta solución se ha hecho sin usar listener. Simplemente se pregunta por el valor del RadioButton cuando se pulsa el botón convertir; y es en ese momento cuando se realiza la conversión elegida.
 
 * **Ejercicios RecyclerView**. Esta carpeta contiene las soluciones de los ejercicios de la lista de fútbol hecha con RecyclerView. Cada APP es un nuevo proyecto:
 
    * ***Tutoria2App1RecyclerView***: Solución del ejercicio APP1.
    * ***Tutoria2App2RecyclerView***: Solución del ejercicio APP2 (no es buena solución, pues añade lógica al adaptador). En el siguiente proyecto se ofrece una solución mucho mejor usando interface.
    * ***Tutoria2App2RecyclerViewConInterface***: Solución de la APP2 buena: usa interface para aislar la lógica del adaptador. Éste se limita a cargar las vistas en la lista e informar de cuándo se hace click sobre una de ellas.
	* ***Tutoria2App3RecyclerView***: Solución del ejercicio APP3.
	* ***Tutoria2App4RecyclerView***: Solución del ejercicio APP4.
	
## UT5: Fragments
 Todos los ejercicios de esta unidad están relacionados con fragments
 
 * **EjercicioSencilloFragments**. Contiene la solución del ejercicio "EjercicioSencilloFragments" que hecho en clase como ejemplo de manejo de fragments estáticos comunicados con inteface. En uno de los fragments se pide un texto y al pulsar el botón ENVIAR se envía al otro fragment cuya única tarea es mostrarlo en color rojo. Para la comunicación de los fragments se usa un interface. Se han hecho diseños alternativos para tablets de 7" en horizontal y vertical y teléfono u otros dispositivos.
 
 * **EjemploFragmentsListaFutbol**. Contiene la solución del ejercicio hecho en clase para mostrar el manejo de fragments estáticos cuando uno de ellos es un listado con RecyclerView. Es la adaptación del ejercicio "Tutoria2App3RecyclerView" hecho en la UT4 como ejemplo de RecyclerView, pero ahora empleando fragments estáticos. Se han implementado dos diseños diferentes: en vertical el fragment listado aparece en la parte superior y el otro fragment en la inferior; y en horizontal el fragment listado aparece en la parte izquierda y el otro fragment en la parte derecha.
