#include<iostream>
#include<string.h>
using namespace std;

class info1;
class info {
		friend class info1;
	public:
		char name[20],clas[5],div[2],dob[12],bloodg[2],addr[30];
		int rno;
		long int t_no,d_no;
		static int count;
	public:

		info() {				//default constructor

			name[10]='a';
			clas[5]='A';
			div[2]='a';
			dob[12]='a';
			bloodg[2]='a';
			addr[30]='a';
			rno=000;
			t_no=0000000000;
			d_no=0000000000;

			char* name=new char[50];
			char* clas=new char[50];
			char* div=new char[50];
			char* dob=new char[50];
			char* bloodg=new char[50];
			char* addr=new char[50];
			int* rno=new int[50];
			long int* t_no=new long int[50];
			long int* d_no=new long int[50];

		}

		info(int r,long int t) {			//parameterized constructor
			rno=r;
			t_no=t;
		}

		~info() {					//destructor

		}

		static void showcount() {
			cout<<"*****welcome you are in static member funcation:*****"<<"\n\n";
			cout<<"number of students:"<<count<<"\n";

		}
		void getdata();
		void display11();
		void display();
};


int info ::count;

class info1 {
	public:
		void display11(info a) {
			cout<<"****welcome you are in friend class:****"<<"\n\n";
			cout<<"name"<<a.name<<"\n";

		}
};

void info ::getdata() {

	cout<<"enter name of Student:"<<"\n";
	cin>>name;
	cout<<"enter roll no:"<<"\n";
	cin>>rno;
	cout<<"enter class of Student:"<<"\n";
	cin>>clas;
	cout<<"enter division of Student:"<<"\n";
	cin>>div;
	cout<<"enter DOB of Student:"<<"\n";
	cin>>dob;
	cout<<"enter blood group of Student:"<<"\n";
	cin>>bloodg;
	cout<<"enter Address of Student:"<<"\n";
	cin>>addr;
	cout<<"enter telephone no:"<<"\n";
	cin>>t_no;
	cout<<"enter driving licence no:"<<"\n";
	cin>>d_no;

	count++; // static member data accessed
}

void info::display() {
	cout<<"\t\t\t*****INFORMATION OF STUDENTS ******"<<"\n\n\n";

	cout<<"\n  NAME :\t"<<name<<"\n Roll No :\t"<<rno<<"\n Class :\t"<<clas<<"\n Division :\t"<<div<<"\n DOB :\t"<<dob<<"\n Blood Group :\t"<<bloodg<<"\n Address :\t"<<addr<<"\n Telephonr No : \t"<<t_no<<"\n Driving Liceence NO :\t"<<d_no<<"\n \n";
}

void info::display11() {
	cout<<"****welcome you are in copy constructor****"<<"\n\n";
	cout<<"Roll No"<<rno<<"\n";
}

inline void display111(int r,long int q,long int m) {
	cout<<"****welcome you are in inline function:****"<<"\n\n";
	cout<<"Roll no "<<r<<"\n P NO"<<q<<" \n D L No"<<m<<"\n";
}


int main() {
	int n=0,i,choice;
	int r1=125;
	char abc[50];
	long int r2=1900,r3=170;
	info i1[30],i2(1999,140);
	info1 p[30];
	cout<<"WELCOME"<<"\n";

	do {

		cout<<"1.INSERT A STUDENTS RECORD IN DB\n2.DISPLAY STUDENTS RECORD\n3.SEARCH A STUDENT BY NAME\n4.EXIT PROGRAM"<<"\n";
		cout<<"enter your choice"<<"\n";
		cin>>choice;
		switch(choice) {
			case 1:
				cout<<"enter how many students you want to add:\n"<<"\n";
				cin>>n;
				for(i=1; i<=n; i++) {
					i1[i].getdata();

				}
				break;
			case 2:
				if(n==0) {
					cout<<"\n Sorry,No data Present. PLZ Insert Data Into DataBase \n  \n";
				} else {

					for(i=1; i<=n; i++) {
						i1[i].display(); //to display getdata information
						p[i].display11(i1[i]); //cpy constructor
					}
					info ::showcount(); // Static function call
					i2.display11();
					display111(r1,r2,r3); // inline function call
				}
				break;
			case 3:
				cout <<"enter name of Student you WANT TO search:"<<"\n";
				cin >>abc;
				for(i=1; i<=n; i++) {
					if( strcmp(abc,i1[i].name)==0 ) {
						i1[i].display();
					} else {
						cout<<"NO MATCH FOUND..."<<"\n";
					}
				}
				break;
			case 4:
				break;


		}

	} while(choice!=4);
	return 0;
}






