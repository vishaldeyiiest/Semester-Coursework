#include <stdio.h>
#include <unistd.h>

int main()
{
	int pid;
	pid = fork();
	if(pid > 0)
	{
		for(;;);
		
	}
	else
		printf("%d\n",getppid());
}
