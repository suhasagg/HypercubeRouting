HypercubeRouting
================

Simple algorithm for routing on a hypercube topology.


*********************************************************************
---- PROGRAMA PARA RUTEO DE NODOS EN RED CON TOPOLOGIA HYPERCUBO ----
*********************************************************************

El programa se encarga de mostrar la secuencia de nodos por los que pasa un paquete originado en un nodo fuente para llegar a un nodo destino en una topologia de red de hypercubo.

Para llamar de forma correcta al programa debe suministrarle tres parametros:
    -> La dimension del hypercubo (un entero mayor que 1).
    -> El nodo fuente del mensaje (un entero entre 0 y (2^dim)-1).
    -> El nodo destino del mensaje (un entero entre 0 y (2^dim)-1) (distinto del nodo fuente).
