#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <stdio.h>
#include <stdlib.h>
#define n 10
union semun
{
	int val;
	ushort *array;
};
struct sembuf p;

int wait_signal(int semid, int sem_num, int sem_op, int sem_flg)
{
	p.sem_num = sem_num;
	p.sem_op = sem_op;
	p.sem_flg = sem_flg;
	if(semop(semid, &p, 1)==0)
		return 0;
	perror("semop\n");
	return 1;
}
int main()
{
	int semid;
	semid = semget(IPC_PRIVATE, 3, IPC_CREAT|0666);
	union semun sem;
	sem.array = (int*)malloc(sizeof(int)*3);
	sem.array[0] = 0;		//mutex
	sem.array[1] = 10;		//empty
	sem.array[2] = 0;		//full
	semctl(semid, 0, SETALL, sem);
	int i;
	int shmid = shmget(123, sizeof(int)*n, IPC_CREAT|0666);
	int *a = (int *)shmat(shmid, NULL, 0);
	pid_t pid = fork();
	if(pid == 0)				//consumer
	{
		for(i = 0; i < n; i++)
		{	
			wait_signal(semid, 2, -1, 0);
			wait_signal(semid, 0, -1, 0);
			printf("Consumer: %d\n", a[i]);
			wait_signal(semid, 0, 1, 0);
			wait_signal(semid, 2, 1, 0);
		}
	}
	else
	{
		for(i = 0; i < n; i++)
		{	
			wait_signal(semid, 1, -1, 0);
			wait_signal(semid, 0, -1, 0);
			printf("Producer: \n");
			a[i] = i;
			wait_signal(semid, 0, 1, 0);
			wait_signal(semid, 1, 1, 0);
		}
	}
}
