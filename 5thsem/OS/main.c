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
	int *a;
	int shmid;
	shmid = shmget(key, size, IPC_CREAT | 0666);
	a = (int*)shmat(shmid, NULL, 0);
	a[0] = l;
	a[1] = m;
	a[2] = n;
	printf("Enter elements of A\n");
	for(i = 0; i < l; i++)
	{
		for(j = 0; j < m; j++)
			scanf("%d", &a[i*m + j + 3]);
	}
	printf("Enter elements of B\n");
	
	for(i = 0; i < m; i++)
	{
		for(j = 0; j < n; j++)
			scanf("%d", &a[i*n + j + l*m + 3]);
	}
	printf("matrix A:\n");
	for(i = 0; i < l; i++)
	{
		for(j = 0; j < m; j++)		
			printf("%d ", a[i*m + j + 3]);
		printf("\n");
	}
	printf("matrix B:\n");
	for(i = 0; i < m; i++)
	{
		for(j = 0; j < n; j++)
			printf("%d ", a[i*n + j+l*m + 3]);
		printf("\n");
	}
	
	pid_t pid;
	
	char row[101] = {'\0'};
	char col[101] = {'\0'};
	for(i = 0; i < l; i++)
	{
		for(j = 0; j < n; j++)
		{
			int status;
			pid = fork();
			sprintf(row, "%d", i);
			sprintf(col, "%d", j);
			char *args[] = {"./compute", row, col, (char *)NULL};
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
			printf("%d ", a[i*n+j+l*m+m*n+3]);
		printf("\n");
	}
	shmdt((void*) a);
	shmctl(shmid, IPC_RMID, NULL);
	
	return 0;
}
			
