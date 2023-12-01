#include<iostream>
#include<stdlib.h>
using namespace std;

class complex
{
 private:
 float real;
 float imag;

 public:
 	
 complex(int real, int imag)
 {
  this->real = real; 
  this->imag = imag;
 }
 
void display()
{
	if (this->imag>=0){
	
  cout<< this->real<<"+"<<this->imag<<"i";
	}else{
		cout<< this->real<<"-"<<-this->imag<<"i";
	}
}

complex sq(complex c)
{
  complex csq(0,0);
  csq = c * c;
  return csq;
  
}

complex operator +(complex cnumber)
{
 complex temp(0,0);
 temp.real = this->real + cnumber.real;
 temp.imag = this->imag + cnumber.imag;
 return temp;   
}

complex operator *(complex cnumber)
{
 complex temp(0,0);
 temp.real = this->real*cnumber.real - this->imag*cnumber.imag;
 temp.imag = this->real*cnumber.imag + this->imag*cnumber.real;
 return temp;
}

complex operator -(complex cnumber)
{
  complex temp(0,0);
  temp.real = this->real- cnumber.real;
  temp.imag = this->imag - cnumber.imag;
  return temp;
}

complex operator /(complex cnumber)
{
  complex temp(0,0);
  complex firstcomplex(0,0);
  firstcomplex.real = this->real;
  firstcomplex.imag = this->imag;
  
  complex rcomplex(cnumber.real, -cnumber.imag);
  complex dcomplex(0,0);
  dcomplex = cnumber *  rcomplex;
  complex ncomplex(0,0);
  ncomplex = rcomplex * firstcomplex;
  temp.real = ncomplex.real / dcomplex.real;
  temp.imag = ncomplex.imag/ dcomplex.real;
  

  
  return temp;
}

friend ostream& operator <<(ostream& out,complex C);
friend istream& operator >>(istream& in,complex& C);
};

ostream& operator<<(ostream& dout,complex C)
{
	if(C.imag>=0){
		dout<< C.real<<"+"<<
  	C.imag<<"i"<< endl;
	}else{
		dout<< C.real<<
  	C.imag<<"i"<< endl;
	}
 
 return dout;
}

istream& operator >>(istream& din,complex& C)
{
 cout<<"\nEnter a real part"<< endl;
 din>>C.real;

 cout<<"\nEnter a imaginary part"<< endl;
 din>>C.imag;
 return din;
}

int main()
{
 complex c1(0,0);    // 0 + 0i
 complex c2(0,0);	 // 0 + 0i
 complex c3(0,0); 	 // 0 + 0i 


 cin>>c1;
 cout<<"\n First Complex number is: ";
 cout<< c1;

 cin>>c2;
 cout<<"\n Second Complex number is: ";
 cout<< c2;


 c3=c1+c2;
 cout<<"\n addition of two complex numbers: ";
 cout<< c3;

 c3=c1*c2;
 cout<<"\n multiplication of two complex numbers: ";
 cout<<c3;

 c3=c1-c2;
 cout<<"\n subtraction of two complex numbers: ";
 cout<< c3;
 

 c3=c1/c2;
 cout<<"\n division of two complex numbers: ";
 cout<< c3;


 c3=c3.sq(c1);
 cout<<"\n square of first complex numbers: ";
 cout<< c3;
  c3=c3.sq(c2);
 cout<<"\n square of second complex numbers: ";
 cout<< c3;
  
 return 0;
}

