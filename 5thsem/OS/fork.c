#include <unistd.h>
int main()
{
	pid_t pid = fork();
	setresuid(0, 0, 0);
	if(pid == 0)
	{
		if(seteuid(1000) == -1)
			perror("setuid");;
		printf("User id: %d %d", getuid(), geteuid());
	}
	else
		sleep(10);
}
