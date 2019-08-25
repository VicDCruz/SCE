rem cd D:\User\Materias\SistemasDistribuidos\SD_2012_03_AgoDic\SimpleRMI\rmi_tut

java -Djava.rmi.server.codebase=file:///proyectos\pruebaSimpleRMI/Simulator/bin/example/hello example.hello.Server

java -Djava.rmi.server.codebase=file:///proyectos/pruebaSimpleRMI/Simulator/bin/example/hello example.hello.Client localhost

