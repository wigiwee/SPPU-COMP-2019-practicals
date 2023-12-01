#include<iostream>
using namespace std;

//	template <typename T>	//function
template <class T>		//class

void sort()
{
    int i, j;
    T temp;		//variable for swapping of values
    T n[5];		//array
    
    cout<<"\n Enter five numbers: ";
    for(i=0;i<5;i++)
    {
   	 	cin>>n[i];			//to store values in an array
    }
    
    for(i=0;i<4;i++)
    {
   	 for(j=i;j<5;j++)
   	 {
   		 if(n[i]>n[j])
   		 {
   			 temp=n[i];
   			 n[i]=n[j];
   			 n[j]=temp;
   		 }
   	 }
    }
    
    cout<<"\n The array in the sorted order is: "<<endl;
    for(i=0;i<5;i++)
    {
   	 cout<<"\t"<<n[i];		//printing sorted array
    }
}


int main()
{
    int choice;
    char ans;
    do
    {
     cout<<"\n select which datatype you want to sort-";	
   	 cout<<"\n 1. Integer  sort. \n 2. Float sort. \n 3. double sort.";
   	 cout<<"\n enter your choice : ";
   	 cin>>choice;
   	
	  switch(choice)
   	 {
   		 case 1 : sort<int>();
   				  break;
   		 case 2 : sort<float>();
   				  break;
   		 case 3 : sort<double>();
   		 		  break;
   		 case 4 : cout<<"\n Invalid choice.";
   				  break;				  
   			 }
   			 cout<<"\n Do u wish to continue (Y/N)?";
   			 cin>>ans;
   		 }while(ans=='Y' || ans=='y');
   		 return 0;
   	 }
