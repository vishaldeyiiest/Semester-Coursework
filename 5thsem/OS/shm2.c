#include <stdio.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <signal.h>

long long int fact(int n)
{
	long long int sum = 1, i;
	for(i = 1; i <= n; i++)
		sum = sum * i;
	return sum;
}


int main()
{
	pid_t pid2;
	int i;
	int shmid = shmget(123, sizeof(int), IPC_CREAT | 0666);
	int *a = (int*)shmat(shmid, NULL, 0);
	a[0] = 0;
	
		for(i = 1; i < 10; i++)
		{
			pid2 = fork();
			if(pid2 < 0)
			{
				perror("cannot fork\n");
				_exit(1);
			}		
			else if(pid2 == 0)
			{
				a[0]++;
				printf("Child process %d of parent %d\n",getpid(), getppid());
				printf("value incremented to %d\n", a[0]);
				_exit(0);		
			}
			else
			{
				printf("Factorial of %d is %lld\n",a[0], fact(a[0]));
			}
		}
	shmdt((void*)a);
	shmctl(shmid, IPC_RMID, NULL);
		
	_exit(0);
}
	
