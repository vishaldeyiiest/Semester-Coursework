#include <unistd.h> 
#include <stdlib.h> 
#include <stdio.h>  
#include <fcntl.h>
#include <sys/wait.h>  
int main(int argc, char * argv[])
{ 

    printf( "usage: %i filename", argc );

    pid_t pid;
    int status;

     if ((pid = fork()) < 0)
     {     /* fork a child process           */
          printf("*** ERROR: forking child process failed\n");
          exit(1);
     }
     else if (pid == 0) 
     {          /* for the child process:         */
          execvp(argv[1], argv);
          {     /* execute the command  */
               printf("*** ERROR: exec failed\n");
               exit(1);
          }
     }
     else {                                  /* for the parent:      */
          while (wait(&status) != pid);
     }
	return 0; 

} 
