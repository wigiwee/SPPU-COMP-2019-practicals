
#include<iostream>
#include<fstream>
#include<iomanip>
using namespace std;
void addstudent()
{
    ofstream f("db.txt",ios::app);
    string rn,div,name,add;
    cout<<"\n enter the roll no of student:";
    cin>>rn;
    cout<<"\n enter the division of student:";
    cin>>div;
    cout<<"\n enter the name of student:";
    cin>>name;
    cout<<"\n enter the address of student:";
    cin>>add;
    f<<setw(20)<<rn<<setw(20)<<setw(20)<<div<<setw(20)<<name<<setw(20)<<add<<endl;
    f.close();
    cout<<"\n student added successfully!!!";


}
void printstudent()
{
    ifstream f("db.txt");
    string line;
    cout<<"\n printing data of student:";
    while(getline(f,line))
    {
        cout<<line<<endl;
    }
    cout<<"printing is completed!!";
    f.close();
}
void searchstudent()
{
    ifstream f("db.txt");
    string line,rn;
    cout<<"\n enter the roll to be search:";
    cin>>rn;
    bool found=false;
    while(getline(f,line))
    {
        if(line.find (rn)!=string::npos)
        {
            cout<<"\n student found "<<endl;
            cout<<"student details!!!"<<endl;
            cout<<line<<endl;
            found=true;
            break;
        }
    }
    f.close();
    if(!found)
    {
        cout<<"\n student not found"<<endl;
    }
}
void deletestudent()
{
    ifstream f("db.txt");
    string line,rn;
    string filedata;
    cout<<"\n enter the roll to be deleted:";
    cin>>rn;

    while(getline(f,line))
    {
        if(line.find(rn)==string::npos)
        {
            filedata += line;
            filedata += "\n";
        }
    }
    f.close();
    ofstream f1("db.txt", ios::out);
    f1<<filedata;
    f1.close();
}
int main()
{
    ofstream f("db.txt",ios::out);
   f<<left<<setw(20)<<"ROLL NO"<<setw(20)<<"DIV"<<setw(20)<<"NAME"<<setw(20)<<"ADDRESS"<<endl;
   f.close();
   int ip;
   while(ip!=-1)
   {
       cout<<"\n enter your choice \n 1.add student\n 2.delete student\n 3.search student\n 4.print data\n.5.Exit";
       cin>>ip;
       switch(ip)
       {
       case 1:
        addstudent();
        break;
       case 2:
        deletestudent();
        break;
       case 3:
        searchstudent();
        break;
       case 4:
        printstudent();
        break;
       case 5:
        return 0;
        break;
        default:
        cout<<"\n please ReEnter your choice";
        break;
       }


   }

}
