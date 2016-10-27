#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/sem.h>
#include <stdio.h>

union semun
{
	int val;
};

int main()
{
	int semid;
	semid = semget(1059, 1, IPC_CREAT | 0666);
	union semun a;
	a.val = 1;
	semctl(semid, 0, SETVAL, a);
}
