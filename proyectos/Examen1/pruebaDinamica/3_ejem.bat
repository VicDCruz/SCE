echo off
echo ejecuta el master para que (re)inicialice al servidor de disparo
echo uso:
echo 3_ejem Segs_a_Disparo HOSTNAME (en caso de omitirlo se usa localhost, si se omiten asimismo los segundos usa 15)
echo on


set cb=%cd%\ClasesDinamicos.jar

if [%1] NEQ [] goto conDeltaT
REM java -Djava.rmi.server.codebase=file:%cb% -jar %cb% Master localhost reset 15
java -cp .;%cb% -Djava.rmi.server.codebase=file:%cb% clasesdinamicos.Distribuidor clasesdinamicos.Master localhost reset 15
goto fin

:conDeltaT
if [%2] NEQ [] goto conHost
REM java -Djava.rmi.server.codebase=file:%cb% -jar %cb% Master localhost reset %1
java -cp .;%cb% -Djava.rmi.server.codebase=file:%cb% clasesdinamicos.Distribuidor clasesdinamicos.Master localhost reset %1
goto fin

:conHost
REM java -Djava.rmi.server.codebase=file:%cb% -jar %cb% Master %2 reset %1
java -cp .;%cb% -Djava.rmi.server.codebase=file:%cb% clasesdinamicos.Distribuidor clasesdinamicos.Master %2 reset %1
:fin


