#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <sys/wait.h>
#define BUFF 512
int pipecommand()
{
	int pfd[2];
	pid_t pid;
	char *a[2];
	a[1] = '\0';
	for(int i = 1; i < argc; i++)
	{
		pipe(pfd);
		pid = fork();
		int status;
		if(pid == 0)
		{
			close(pfd[0]);
			dup2(pfd[1], 1);
			close(pfd[1]);
			a[0] = argv[i];
			if (execvp(a[0], a) == -1)
			_exit(EXIT_FAILURE);
		}
		else
		{
			close(pfd[1]);
			dup2(pfd[0], 0);
			close(pfd[0]);
			wait(&status);
		}
	}
	return 0;
}

int main()
{
	char line[BUFF];
	while(1)
	{
		printf("VIsh$> ");
		if(!fgets(line, BUFF, stdin))
			return 0;
		if(strcmp(line, "exit")==0)
			break;
        int input = 0, first = 1;
		char* cmd = line;
		char* next = strchr(cmd, '|'); 
		while (next != NULL)
		{
			
			*next = '\0';
			//input = exe(cmd, input, first, 0);
 
			cmd = next + 1;
			next = strchr(cmd, '|'); 
			first = 0;
		}
		
		//input = exe(cmd, input, first, 1);
		
		//n = 0;
	}
	return 0;
	
}
