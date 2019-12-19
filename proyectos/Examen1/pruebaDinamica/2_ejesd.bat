echo off
echo inicializa el servidor de disparo
echo uso:
echo 2_ejesd HOSTNAME (en caso de omitirlo se usa localhost)
echo on

set cb=%cd%\ClasesDinamicos.jar

if [%1] NEQ [] goto conHost
REM java -Djava.rmi.server.codebase=file:%cb% -jar %cb% ServidorDeDisparo 
java -cp .;%cb% -Djava.rmi.server.codebase=file:%cb% clasesdinamicos.Distribuidor clasesdinamicos.ServidorDeDisparo
goto fin

:conHost
REM java -Djava.rmi.server.codebase=file:///%cb% -jar %cb% ServidorDeDisparo %1
java -cp .;%cb% -Djava.rmi.server.codebase=file:%cb% clasesdinamicos.Distribuidor clasesdinamicos.ServidorDeDisparo %1

:fin
