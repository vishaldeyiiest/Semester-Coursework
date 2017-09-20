#include <bits/stdc++.h>
#define Max 10
using namespace std;

typedef struct Student
{
	
		int roll;
		string name;
		string surname;
		long int mob;
		double marks;
}Student;

void swap_obj(Student &a, Student &b)
{
	Student temp;
	temp.roll = a.roll;
	temp.name = a.name;
	temp.surname = a.surname;
	temp.mob = a.mob;
	temp.marks = a.marks;
	a.roll = b.roll;
        a.name = b.name;
        a.surname = b.surname;
        a.mob = b.mob;
        a.marks = b.marks;
	b.roll = temp.roll;
        b.name = temp.name;
        b.surname = temp.surname;
        b.mob = temp.mob;
        b.marks = temp.marks;
}
 
int main()
{
	ifstream fp("students.txt");
	string line;
	int good[5];
	int max = 8;
	double avg = 0;
	int i = 0;
	Student a[10];
	while(getline(fp, line))
	{
		
		istringstream is(line);
		//string name, int roll, int mob,	double marks;
		is >> a[i].roll, is >> a[i].name, is >> a[i].surname, is >> a[i].mob, is >> a[i].marks;
		//cout <<  a[i].roll<< " " << a[i].name<< " "<< a[i].surname<< " "<< a[i].mob << " "<<a[i].marks;
		avg += a[i].marks;
		if(a[i].marks > a[max].marks)
			max = i;
		i++;
	}
	cout << "Topper details: "<<endl;
	cout << "Roll No : " << a[max].roll << endl;
	cout << "Name: "<<a[max].name<<" "<<a[max].surname << endl;
	cout << "Mobile: " << a[max].mob<<endl;
	cout << "Marks : " <<a[max].marks<<endl<<endl;
	cout << "Average Marks: "<<avg/Max << endl;
	fp.close();
	for(int i = 0; i < Max - 1; i++)
	{
		for(int j = 0; j < Max - i - 1; j++)
		{
			if(a[j+1].marks > a[j].marks)
			{
				swap_obj(a[j+1], a[j]);
			}
		}
	}
	cout <<endl;
	cout << "Top 5 students: "<<endl; 
	for(int i = 0; i < 5; i++)
	{
		cout << i+1 <<" Student :"<<endl;
		cout << "Roll No: " << a[i].roll << "\t";
        	cout << "Name: "<<a[i].name<<" "<<a[i].surname << "\t";
        	cout << "Mobile: " << a[i].mob<<"\t";
        	cout << "Marks : " <<a[i].marks<<endl;
	} 	
}
