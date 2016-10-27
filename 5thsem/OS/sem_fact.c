#include <stdio.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <signal.h>
#include <sys/sem.h>

long long int fact(int n)
{
	long long int sum = 1, i;
	for(i = 1; i <= n; i++)
		sum = sum * i;
	return sum;
}
struct sembuf psembuf, vsembuf;

union semun
{
	int val;
};

int main()
{
	pid_t pid2;
	int i;
	int shmid = shmget(123, sizeof(int), IPC_CREAT | 0666);
	int *a = (int*)shmat(shmid, NULL, 0);
	int semid;
	a[0] = 0;
	semid = semget(1059, 1, IPC_CREAT|0666);
	union semun sem;
	sem.val = 1;
	semctl(semid, 0, SETVAL, sem);
	
	if(semid < 0)
	{
		perror("semget\n");
		_exit(1);
	}
	
		for(i = 1; i <= 15; i++)
		{	
			pid2 = fork();
			if(pid2 < 0)
			{
				perror("cannot fork\n");
				_exit(1);
			}		
			if(pid2 == 0)				
			{					// wait on the semaphore until semaphore value is zero
				psembuf.sem_op = -1;
				psembuf.sem_flg = 0;
				psembuf.sem_num = 0;
				vsembuf.sem_op = 1;
				vsembuf.sem_flg = 0;
				vsembuf.sem_num = 0;
				semop(semid, &psembuf, 1);
				a[0]++;
				printf("Child process %d of parent %d\n",getpid(), getppid());
				printf("value incremented to %d\n", a[0]);	
				printf("Factorial of %d is %lld\n",a[0], fact(a[0]));
				semop(semid, &vsembuf, 1);	//increment the resource count
				_exit(0);
				
			}
			/*else         			
			{
				semop(semid, &psembuf, 1);
				printf("Factorial of %d is %lld\n",a[0], fact(a[0]));
				semop(semid, &vsembuf, 1);
			}*/
		}
	shmdt((void*)a);
	shmctl(shmid, IPC_RMID, NULL);
	semctl(semid, 1, IPC_RMID);
	_exit(0);
}
	
