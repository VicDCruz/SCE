set cb="ClasesDinamicos.jar"

if [%1] NEQ [] goto conHost
REM java -Djava.rmi.server.codebase=file:///%cb% -jar %cb% Master localhost reporta
java -cp .;%cb% -Djava.rmi.server.codebase=file:%cb% clasesdinamicos.Distribuidor clasesdinamicos.Master localhost reporta
goto fin

:conHost
REM java -Djava.rmi.server.codebase=file:///%cb% -jar %cb% Master %1 reporta
java -cp .;%cb% -Djava.rmi.server.codebase=file:%cb% clasesdinamicos.Distribuidor clasesdinamicos.Master %1 reporta
:fin


