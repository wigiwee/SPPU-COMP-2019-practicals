#include <map>
#include <iostream>
#include <string>


using namespace std;

int main()
{
	char cityname[20];
	int ch;
	
	map<string,int> obj;		// map declaration

								//database inside map container
	obj["pune"] = 101234;
	obj["mumbai"] = 20123;
	obj["nashik"] = 200000;
	obj["loni"] = 10000;
	obj["rahata"] = 25000;
	obj["kopargaon"] = 30000;
	obj["shirdi"] = 40000;
	obj["sangamner"] = 54000;
	obj["ahmednagar"] = 70000;
	obj["rahuri"] = 15000;
	
	do
	{
	
	cout<<"\n enter city name: ";
	cin>>cityname;
	
	cout<<"population of "<<cityname<<" is="<<obj[cityname];
	
	cout<<"\n do you want to continue? (press 1 to continue or 0 to exit)";
	cin>>ch;
	}while(ch==1);
	return 0;
}


