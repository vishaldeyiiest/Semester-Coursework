#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
int main()
{
	#if 0
	int status;
	pid_t pid1, pid2, fpd[2];
	pid1 = fork();
	if(pid1 == 0)
	{
		pipe(fpd);
		pid2 = fork();
		if(pid2 > 0)
		{
			close(fpd[0]);
			dup2(fpd[1], 1);
			close(fpd[1]);
			execlp("ls", "ls",(char*)0);
		}
		else
		{
			close(fpd[1]);
			dup2(fpd[0], 0);
			close(fpd[0]);
			execlp("wc", "wc",(char*)0);
		}
	}
	else
		wait(&status);
	#endif
	if(fork() || fork())
		printf("HELLO\n");
	
}
