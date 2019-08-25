set cb=%cd%\example\hello\

rem java -Djava.rmi.server.codebase=file:///C:/Users/vicda/Documents/SCE/proyectos/pruebaSimpleRMI/example/hello example.hello.Client

estresador %1 -Djava.rmi.server.codebase=file:%cb% example.hello.Client %2 %3
