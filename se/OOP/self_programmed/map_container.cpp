#include <iostream>
#include <map>
#include <string>
using namespace std;

int main()
{
	char cityname[20];
	int ch;
	map<string , int> city;
	
	city["Delhi"] = 200000;
	city["Mumbai"] = 1500000;
	city["Loni"] = 15000;
	city["Jaipur"] = 145872;
	city["Ahmednagar"] = 974625;
	city["Pune"] = 78412365;
	
	do{
		
		cout<<"\nEnter the city name: ";
		cin>>cityname;
		cout<<"\nThe population of "<<cityname << " is "<<city[cityname];
		cout<<"\nDo you wish to continue? (0/1): ";
		cin>>ch;
	}while(ch != 0);


	return 0;
}
