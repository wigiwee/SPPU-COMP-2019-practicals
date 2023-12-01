#include<iostream>
#include<fstream>
#include<stdlib.h>

using namespace std;

	class emp
	{
		char name[20];
		int id;
		float salary;
		
	   public:
		void accept()
		{
			cin>>name>>id>>salary;
		}
	
		void display()
		{
			cout<<"\n"<<name<<"\t"<<id<<"\t"<<salary;
	
		}
	};


int main()
{
	emp e[3];
	fstream f;
	int i,n;
	
	//f.open("D:\\inputfile.txt");

	f.open("D:\\inputfile.txt",ios::in);
	for(i=0;i<n;i++)
	{
		f.read((char*) &e[i],sizeof(e[i]));
		e[i].display();
	}
	
	cout<<"\n how many records you want to insert: ";
	cin>>n;
	
	cout<<"\n enter name,emp_id,salary: ";
	
	for(i=0;i<n;i++)
	{
		cout<<"\n enter information of employee "<<i+1<<" ";
		e[i].accept();
		f.write((char*) &e[i],sizeof(e[i]));
	}
	f.close();
	
	f.open("D:\\inputfile.txt",ios::in);
	
	cout<<"\n employee information is as follows: ";
	
	for(i=0;i<n;i++)
	{
		f.read((char*) &e[i],sizeof(e[i]));
		e[i].display();
	}
	f.close();
	return 0;
}
