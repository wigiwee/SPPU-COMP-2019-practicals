#include<iostream>
using namespace std;
template <class T>
void sort(){
	int i , j;
	T temp;
	T n[5];
	
	cout<<"\nEnter five numbers:\n";
	for(i = 0 ; i < 5 ; i++){
		cin>>n[i];
	}
	
	for(i=0; i<4 ; i++){
		for(j=i; j<5; j++){
			if (n[i]>n[j]){
				temp = n[i];
				n[i] = n[j];
				n[j] = temp;
			}
		}
	}
	cout<<"\nThe numbers in sorted order are: \n";
	for (i=0 ; i<5; i++){
		cout<<"\t"<<n[i];
	}
	cout<<"\n";
	
}
int main(){
	int choice;
	while (true){
		cout<<"\nSelect which datatype you want to sort:";
		cout<<"\n1. Integer  2. Float  3.Double";
		cout<<"\nEnter your choice: ";
		cin>>choice;
		if (choice == 1){
			sort<int>();
		}
		if (choice == 2){
			sort<float>();
		}
		if (choice == 3){
			sort<double>();
		}
	}
	return 0;
}
