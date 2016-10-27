#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <sys/wait.h>
#include <fcntl.h>
#define NORMAL 0
#define OUTPUT_REDIRECTION 1
#define INPUT_REDIRECTION 2
#define PIPE 3
#define BACKGROUND 4

#define BUFF 1024
char *args[BUFF];
char *args1[BUFF];
char *input_file;
char line[BUFF];
char *output_file;
int no = 0;
int argc = 0;
int mode = NORMAL;
char* skipwhite(char *a)			//removes white spaces
{
	while(isspace(*a))
		a++;
	return a;
}

char* newline(char *a)
{
	char *p = a;
	while(*a != '\0')
	{
		if(*a == '\n')
			*a = '\0';
		a++;
	}
	return p;
}

void parse(char *cmd)				//tokenizes multi-argument commands like ls -l
{
	cmd = skipwhite(cmd);
	char* n = strchr(cmd, ' ');
	int i = 0;
	
	//char *next = strchr(cmd, '|');
	
	while(n != NULL)					//for multiple arguments n is not NULL
	{
		if(*cmd == '<')
		{
			cmd = newline(cmd);
			cmd = skipwhite(cmd);
			input_file = cmd+2;
			//printf("%s\n",input_file);
			cmd[0] ='\0';
			mode = INPUT_REDIRECTION;
			break;
		}
		else if(*cmd == '>')
		{
			cmd = newline(cmd);
			cmd = skipwhite(cmd);
			output_file = cmd+2;
			//printf("%s\n",output_file);
			cmd[0] = '\0';
			mode = OUTPUT_REDIRECTION;
			break;
		}
		else 
		{
			args[i] = cmd;
			//printf("%s\n",args[i]);
			n[0] = '\0';
			cmd = skipwhite(n+1);
			n = strchr(cmd, ' ');
			i++;
		}
		
	}
	if(cmd[0] != '\0')
	{
		if(*cmd == '&')
			mode = BACKGROUND;
		else
		{	
			args[i] = cmd;
			n = strchr(cmd, '\n');
			n[0] = '\0';
			i++;
		}
	}
	args[i] = '\0';
	argc = i-1;
}

void handler()
{
	waitpid(-1, 0, WNOHANG);
}

#if 1
void pipe_handling()
{
	int pfd[2]; 
	int pfd2[2];
	
	int num_cmds = 0;
	char *cmd[BUFF];
	pid_t pid;
	int end = 0;
	int i = 0, j = 0, k = 0, l = 0;
	
	while (args[l] != NULL)				//counts the no of pipes
	{
		if (strcmp(args[l],"|") == 0)
			num_cmds++;
		l++;
	}
	num_cmds++;
	
	while (args[j] != NULL && end != 1)
	{
		k = 0;	
		while (strcmp(args[j],"|") != 0)
		{
			cmd[k] = args[j];
			j++;	
			if (args[j] == NULL)
			{	
				end = 1;
				k++;
				break;
			}
			k++;
		}
		cmd[k] = NULL;
		j++;		
		if (i % 2 != 0)
			pipe(pfd); 
		else
			pipe(pfd2);
		pid=fork();
		
		if(pid == 0)
		{
			if(i == 0)							//first command
				dup2(pfd2[1], STDOUT_FILENO);		//write to the read end of the other pipe
			else if(i == num_cmds - 1)			//last command: READ FROM ONE PIPE DEPENDING IF EVEN OR ODD keeping stdout intact
			{
				if(num_cmds % 2 != 0) 
					dup2(pfd[0],STDIN_FILENO);
				else
					dup2(pfd2[0],STDIN_FILENO);
			}
			else  								//middle commands
			{
				if(i % 2 != 0)					
				{
					dup2(pfd2[0],STDIN_FILENO); 
					dup2(pfd[1],STDOUT_FILENO);
				}
				else
				{ 
					dup2(pfd[0],STDIN_FILENO); 
					dup2(pfd2[1],STDOUT_FILENO);					
				} 
			}
			
			execvp(cmd[0],cmd);
			perror("execvp");
			_exit(EXIT_FAILURE);
		}		
		else
		{		
			if (i == 0)
				close(pfd2[1]);
			else if (i == num_cmds - 1)
			{
				if (num_cmds % 2 != 0)					
					close(pfd[0]);
				else					
					close(pfd2[0]);
			}
			else
			{
				if (i % 2 != 0)
				{					
					close(pfd2[0]);
					close(pfd[1]);
				}
				else
				{					
					close(pfd[0]);
					close(pfd2[1]);
				}
			}	
			waitpid(pid, 0, 0);	
		}			
		i++;	
	}
}
#endif

int command()
{
	if(mode == BACKGROUND)
	{
		pid_t pid = fork();
		if(pid == 0)
		{
			execvp(args[0], args);
			perror("execvp");
			_exit(EXIT_FAILURE);
		}
		else
		{
			printf("Process created with pid %d\n",getpid());
			signal(SIGCHLD, handler);
		}
		
	}
	
	else if(mode == INPUT_REDIRECTION)
	{
		//printf("eqwe\n");
		pid_t pid;	
		pid = fork();
		int status;
		if(pid == 0)
		{
			int fd = open(input_file, O_RDONLY);
			dup2(fd, STDIN_FILENO);
			execvp(args[0], args);
			perror("execvp");
			_exit(EXIT_FAILURE);
			close(fd);
		}
		else
			wait(&status);
		
	}
	else if(mode == OUTPUT_REDIRECTION)
	{
		pid_t pid;
		pid = fork();
		int status;
		if(pid == 0)
		{
			//printf("%s %s %s\n",args[0], args[1], args[2]);
			//printf("%s\n",output_file);
			int fd = open(output_file, O_RDWR|O_CREAT, 0600);
			dup2(fd, STDOUT_FILENO);
			execvp(args[0], args);
			perror("execvp");
			_exit(EXIT_FAILURE);
			close(fd);
		}
		else
			wait(&status);
	}
	else if(mode == NORMAL)
	{
		pid_t pid = fork();
		int status;
		if(pid == 0)
		{
			execvp(args[0], args);
			perror("execvp");
		}
		else
			wait(&status);
	}
	else
	{
		pipe_handling();
	}
	return 0;
}
/*
int exe(char* cmd)
{
	parse(cmd);
	if (args[0] != NULL)
	{
		if (strcmp(args[0], "exit") == 0) 
			exit(0);
		if(strcmp(args[0], "cd") == 0)
		{
			chdir(args[1]);
			//_exit(EXIT_FAILURE);
		}
		no++;
		return command();
	}
	return 0;
}
*/
void signal_handler(int sig)
{
	waitpid(-1, 0, 0);
}
int main()
{
	printf("Type exit to terminate the shell\n");
	while(1)
	{
		printf("%s",get_current_dir_name());
		printf(":$> ");
		mode = NORMAL;
		if(!fgets(line, BUFF, stdin))
			return 0;
		if(strcmp(line, "exit\n") == 0)			//exits from shell
			break;
			
		char *cmd = line;
		char *next = strchr(cmd, '|');
		if(next != NULL)
		{
			mode = PIPE;
			/*next = '\0';
			input = exe(cmd, input, first, 0);
			cmd = next + 1;
			next = strchr(cmd, '|');
			first = 0;*/
		}
		parse(cmd);
		if(strcmp(cmd, "\n" ) == 0)
			continue;
		if(strcmp(args[0], "cd") == 0)
		{
			chdir(args[1]);
			continue;
		}
		
		signal(SIGINT, signal_handler);
		command();
		//no = 0;
	}
	
	return 0;
}
		
