echo off
echo ejecuta el estresador para sembrar los clientes
echo uso:
echo 4_estresa NumCltes NumSolicitudesPorClte Tester HOSTNAME (en caso de omitirlo se usa localhost, si se omiten los clientes usa 15 y el localhost)
echo on

set cb=%cd%\ClasesDinamicos.jar
set cb1=%cd%\tstRMI.jar

if [%1] NEQ [] goto conclientes
REM estresador 20 -Djava.rmi.server.codebase=file:%cb% -jar %cb% Cliente localhost 200
estresador 20  -cp .;%cb%;%cb1% -Djava.rmi.server.codebase=file:%cb% clasesdinamicos.Distribuidor clasesdinamicos.Client clasesdinamicos.HolaMundo localhost 200
goto fin

:conclientes
if [%2] NEQ [] goto consolicitudes
REM estresador %1 -Djava.rmi.server.codebase=file:%cb% -jar %cb% Cliente localhost 200
estresador %1  -cp .;%cb%;%cb1% -Djava.rmi.server.codebase=file:%cb% clasesdinamicos.Distribuidor clasesdinamicos.Client clasesdinamicos.HolaMundo localhost 200
goto fin

:consolicitudes
if [%3] NEQ [] goto conTester
REM estresador %1 -Djava.rmi.server.codebase=file:%cb% -jar %cb% Cliente localhost 200
estresador %1  -cp .;%cb%;%cb1% -Djava.rmi.server.codebase=file:%cb% clasesdinamicos.Distribuidor clasesdinamicos.Client clasesdinamicos.HolaMundo localhost %2
goto fin

:conTester
if [%4] NEQ [] goto conHost
REM estresador %1 -Djava.rmi.server.codebase=file:%cb% -jar %cb% Cliente localhost %2
estresador %1  -cp .;%cb%;%cb1% -Djava.rmi.server.codebase=file:%cb% clasesdinamicos.Distribuidor clasesdinamicos.Client %3 localhost %2
goto fin

:conHost
REM estresador %1 -Djava.rmi.server.codebase=file:///%cb% -jar %cb% Cliente %3 %2
estresador %1  -cp .;%cb%;%cb1% -Djava.rmi.server.codebase=file:%cb% clasesdinamicos.Distribuidor clasesdinamicos.Client %3 %4 %2


:fin