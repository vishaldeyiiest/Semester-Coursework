#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#define key 123
#define size 1024
int main()
{
	int l, m, n, temp;
	printf("Enter # of rows and columns of A\n");
	scanf("%d %d",&l, &m);
	printf("Enter # of rows and columns of B\n");
	scanf("%d %d",&temp, &n);
	if(temp != m)
	{
		printf("Multiplication not possible\n");
		_exit(0);
	}
	int i, j;
	int *a, *b;
	int shmid1, shmid2, shmid3;
	shmid1 = shmget(key, l*m*sizeof(int), IPC_CREAT | 0666);
	a = (int*)shmat(shmid1, NULL, 0);
	printf("Enter elements of A\n");
	for(i = 0; i < l; i++)
	{
		for(j = 0; j < m; j++)
			scanf("%d", &a[i*m + j]);
	}
	printf("Enter elements of B\n");
	shmid2 = shmget(key, m*n*sizeof(int), IPC_CREAT | 0666);
	b = (int*)shmat(shmid2, NULL, 0);
	for(i = 0; i < m; i++)
	{
		for(j = 0; j < n; j++)
			scanf("%d", &b[i*n + j]);
	}
	printf("matrix A:\n");
	for(i = 0; i < l; i++)
	{
		for(j = 0; j < m; j++)
			printf("%d ", a[i*m + j]);
		printf("\n");
	}
	printf("matrix B:\n");
	for(i = 0; i < m; i++)
	{
		for(j = 0; j < n; j++)
			printf("%d ", b[i*n + j]);
		printf("\n");
	}
	
	int *c;
	pid_t pid;
	char id1[101] = {'\0'};
	sprintf(id1, "%d",shmid1);
	char id2[101] = {'\0'};
	sprintf(id2, "%d",shmid2);
	char row[101] = {'\0'};
	char col[101] = {'\0'};
	char rowm[101] = {'\0'};
	char rown[101] = {'\0'};
	sprintf(rowm, "%d", m);
	sprintf(rown, "%d", n);
	shmid3 = shmget(key, l*n*sizeof(int), IPC_CREAT | 0666);
	c = (int*)shmat(shmid3, NULL, 0);
	char id3[101] = {'\0'};
	sprintf(id3, "%d",shmid3);

	for(i = 0; i < l; i++)
	{
		for(j = 0; j < n; j++)
		{
			int status;
			pid = fork();
			sprintf(row, "%d", i);
			sprintf(col, "%d", j);
			char *args[] = {"./compute", id1, id2, id3, row, col, rowm, rown, (char *)NULL};
			if(pid == 0)
			{
				execvp("./compute",args);
				perror("execvp");
				_exit(EXIT_FAILURE);
			}
			else
				wait(&status);
		}
	}
	
	printf("matrix C: \n");
	for(i = 0; i < l; i++)
	{
		for(j = 0; j < n; j++)
			printf("%d ", c[i*n+j]);
		printf("\n");
	}
	shmdt((void*) a);
	shmdt((void*) b);
	shmdt((void*) c);
	shmctl(shmid1, IPC_RMID, NULL);
	shmctl(shmid2, IPC_RMID, NULL);
	shmctl(shmid3, IPC_RMID, NULL);
	return 0;
}
			
