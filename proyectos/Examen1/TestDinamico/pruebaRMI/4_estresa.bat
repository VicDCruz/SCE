echo off
echo ejecuta el estresador para sembrar los clientes
echo uso:
echo 4_estresa NumCltes NumSolicitudesPorClte HOSTNAME Clase (en caso de omitirlo se usa localhost, si se omiten los clientes usa 15 y el localhost)
echo on

set cb=%cd%\..\dist\TestDinamico.jar

if [%1] NEQ [] goto conclientes
estresador 20 -Djava.rmi.server.codebase=file:%cb% -jar %cb% Cliente localhost 200 testdinamico.HelloWorld
goto fin

:conclientes

if [%2] NEQ [] goto consolicitudes
estresador %1 -Djava.rmi.server.codebase=file:%cb% -jar %cb% Cliente localhost 200 testdinamico.HelloWorld
goto fin

:consolicitudes
if [%3] NEQ [] goto conHost
estresador %1 -Djava.rmi.server.codebase=file:%cb% -jar %cb% Cliente localhost %2 testdinamico.HelloWorld
goto fin

:conHost
if [%4] NEQ [] goto conClase
estresador %1 -Djava.rmi.server.codebase=file:///%cb% -jar %cb% Cliente %3 %2 testdinamico.HelloWorld
goto fin

:conClase
estresador %1 -Djava.rmi.server.codebase=file:///%cb% -jar %cb% Cliente %3 %2 %4


:fin