#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <sys/wait.h>
#include <sys/types.h>
#define BUFF 1024
char *args[BUFF];
int n = 0;
char* skipwhite(char *a)			//removes extra whitespaces
{
	while(isspace(*a))
		a++;
	return a;
}

void parse(char *cmd)				//tokenizes the command for multi-argument
{
	cmd = skipwhite(cmd);
	char *n = strchr(cmd, ' ');
	int i = 0;
	while(n != NULL)
	{
		n[0] = '\0';
		args[i] = cmd;
		cmd = skipwhite(n + 1);
		n = strchr(cmd, ' ');
		i++;
	}
	
	if(cmd[0] != '\0')
	{
		args[i] = cmd;
		n = strchr(cmd, '\n');
		n[0] = '\0';
		i++; 
	}
	args[i] = '\0';
}

int command(int input, int first, int last)
{
	int pfd[2];
	
	pipe(pfd);	
	pid_t pid = fork();
	int status;
	if (pid == 0)
	{
		if (input == 0 && first == 1 && last == 0)
		{
				dup2(pfd[1], STDOUT_FILENO);
		}
		else if (input != 0 && first == 0 && last == 0)
		{
			dup2(input, STDIN_FILENO);
			dup2(pfd[1], STDOUT_FILENO);
		} 
		else
		{
			dup2(input, STDIN_FILENO);
		}
		//printf("%s\n",args[0]);
		if (execvp(args[0], args) == -1)
			_exit(EXIT_FAILURE); 
	}
	else
	{
		if(input != 0) 
			close(input);	
		close(pfd[1]);
 
		if (last == 1)
			close(pfd[0]);
		wait(&status);
	}
	return pfd[0];
}

int exe(char* cmd, int input, int first, int last)
{
	parse(cmd);
	if (args[0] != NULL)
	{
		if (strcmp(args[0], "exit") == 0) 
			exit(0);
		n ++;
		return command(input, first, last);
	}
	return 0;
}

int main()
{
	char line[BUFF];
	while(1)
	{
		printf("VIsh$>");
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
			input = exe(cmd, input, first, 0);
 
			cmd = next + 1;
			next = strchr(cmd, '|'); 
			first = 0;
		}
		
		input = exe(cmd, input, first, 1);
		
		n = 0;
	}
	return 0;
	
}
