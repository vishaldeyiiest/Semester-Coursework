#include <stdio.h>
#include <sys/shm.h>
#include <stdlib.h>
#define key 123
#define size 1024
int main(int argc, char *argv[])
{
	int *a;
	int k;
	int shmid = shmget(key, size, 0666);
	a = (int*)shmat(shmid, NULL, 0);
	
	int row = atoi(argv[1]);
	int col = atoi(argv[2]);
	
	int sum = 0;
	int l = a[0], m = a[1], n = a[2];
	for(k = 0; k < l; k++)
	{
		sum += a[row*m + k + 3] * a[k*n + col + l*m + 3];
	}
	a[row*n + col + l*m + m*n + 3] = sum;
	shmdt((void*)a);
	return 0;
}
