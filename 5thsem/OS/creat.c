#include <fcntl.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/ioctl.h>
#include <err.h>
int main()
{
	int fd;
	char *buf1 = "abcdefghij";
	char *buf2 = "jenjwjeiii";
	/*printf("User ID: %d\n",getuid());
	printf("Effective User ID: %d\n",geteuid());
	printf("Group ID: %d\n",getgid());
	printf("Effective Group ID: %d\n",getegid());
	*/
	printf("Process ID: %d\n",getpid());
	printf("Parent Process ID: %d\n",getppid());
	
	if((fd = creat("file.hole", 0)) < 0)
		printf("creat error\n");
	printf("%d",access("file.hole",0));
	if(write(fd, buf1,10) != 10)
		printf("buf1 write error\n");
	if(lseek(fd, 16384, SEEK_SET) == -1)
		printf("lseek error\n");
	if(write(fd, buf2, 10) != 10)
                printf("buf2 write error\n");

}
