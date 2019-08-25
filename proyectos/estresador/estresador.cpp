// estresador.cpp : Defines the entry point for the console application.
//
/*
#include "stdafx.h"

int _tmain(int argc, _TCHAR* argv[])
{
	printf("Hola Mundo\n");
	int i = 0;
	while( argv[i] != NULL )
	{
		_tprintf(_T("argv[%d]:%s\n"),i,argv[i]);
		i++;
	}
	getchar();
	return 0;
}

*/

//#include "stdafx.h" // puede omitirse
/*
#include <process.h>

int main(int argc, char* argv[])
{
	int i, r;
	char *args[4];
	char *datos[] = {"0","1","2","3","4","5","6","7","8","9"};
	
	args[0] = "java.exe";       // ojo éste es importante que esté repetido
	args[1] = "HolaMundo";      // clase a ejecutar su main()
	args[2] = "0";              // argumento del programa
	args[3] = NULL;             // para marcar el fin de los argumentos
    for( i = 0; i < 10; i++ )
	{
       args[2] = datos[i];
       r = _spawnvp( _P_NOWAITO , "java.exe", args );  // el vp significa array values y path local 
	}
    if( r < 0 )
      printf("Hubo un error al ejecutar, r = %d\n",r);
    else
     printf("stress_c.exe ha terminado bien\n");
    return 0;
}

*/
#include "stdafx.h"
#include <process.h>

int main(int argc, char* argv[])
{
	int i, r;
	char *args[9];
	
	args[0] = "java.exe";       // ojo éste es importante que esté repetido
	args[1] = argv[1];         
	args[2] = argv[2];          
	args[3] = argv[3];
	args[4] = argv[4];
	args[5] = argv[5];
	args[6] = argv[6];
    args[7] = NULL;
	args[8] = NULL;

	// para marcar el fin de los argumentos
    for( i = 0; i < 10; i++ )
	{
       r = _spawnvp( _P_NOWAITO , "java.exe", args );  /* el vp significa array values y path local */
	}
    if( r < 0 )
      printf("Hubo un error al ejecutar, r = %d\n",r);
    else
     printf("stress_c.exe ha terminado bien\n");
    return 0;
}



