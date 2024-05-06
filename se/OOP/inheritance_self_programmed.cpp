#include <iostream>
#include <stdlib.h>

using namespace std;

class publication
{
	public:
	char title[25];
	float price;
	
	void getdata()
	{
		cout<<"\nEnter the title of the book:  ";
		cin>>title;
		cout<<"\nEnter the price of the book:  ";
		cin>>price;
		
	}
	
	
};
class book : public publication
{
	public:
	int pages_count;
	void getdata()
	{
		cout<<"\nEnter the no. of pages in the book:  ";
		cin>>pages_count;
		
		
	}
	void display(publication p)
	{
		cout<<"\nTitle of the Book is: "<<p.title;
		cout<<"\nPrice of the Book is:  "<<p.price;
		cout<<"\nPages count of the Book is:  "<<pages_count<<endl;
		
	}
	
	
};
class casset : public publication
{
	public:
	float play_time;
	void getdata()
	{
		cout<<"\nEnter the play time of the book:  ";
		cin>>play_time;
		
		
	}
	void display(publication p)
	{
		cout<<"\nTitle of the Book is: "<<p.title;
		cout<<"\nPrice of the Book is: "<<p.price;
		cout<<"\nPlay time of the Book is: "<<play_time<<endl;
		
	}
	
};
int main()
{
	book b;
	publication p;
	casset c;
	int choice;
	
	while(true)
	{
		cout<<"\n1.Enter Publication info\n2.Enter Book info\n3.Enter Casset info\n4.Exit"<<endl;
		cin>>choice;
		switch(choice)
		{
			case 1 :
				p.getdata();
				break;
			case 2 :
				b.getdata();
				b.display(p);
				break;
			case 3 :
				c.getdata();
				c.display(p);
				break;
			case 4 :
				exit(0);

	
		

		}
	}
	
	
	return 0;
	
	
	
	
}
