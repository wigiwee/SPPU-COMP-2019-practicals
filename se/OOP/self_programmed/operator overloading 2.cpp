#include <iostream>
#include <stdlib.h>

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
	complex operator + (complex Cnumber)
	{
		complex temp(0,0);
		temp.real = this->real + Cnumber.real;
		temp.imag = this->imag +Cnumber.imag;
		return temp;
		
	}
	complex operator - (complex Cnumber)
	{
		complex temp(0,0);
		temp.real = this->real - Cnumber.real;
		temp.imag = this->imag - Cnumber.imag;
		return temp;
		 
	}
	complex operator *(complex Cnumber)
	{
		complex temp(0,0);
		temp.real = this->real *Cnumber.real-(this->imag *Cnumber.imag);
		temp.imag = this->real *Cnumber.imag + (Cnumber.real *this->imag);
		
		return temp;
		
	}

friend ostream& operator <<(ostream& out,complex C);
friend istream& operator >>(istream& in,complex& C);
	
};

ostream& operator <<(ostream& dout,complex C)
{
	dout<<C.real<<"+"<<C.imag<<"i"<<endl;
	
	return dout;
	
}
istream& operator >>(istream& din, complex& C)
{
	cout<<"Enter real part:"<< endl;
	din >> C.real;
	cout<<"Enter imaginary part:"<<endl;
	din>>C.imag;
	
	return din;
	
}



int main()
{
	complex c1(0,0);
	complex c2(0,0);
	complex c3(0,0);		
	
	
	cin>>c1;
	cout<<"\n First complex number is: ";
	cout<<c1;
	
	cin>>c2; 
	cout<<"\n Second complex number is: ";
	cout<<c2;
	
	cout<< " \n Additon of the two given complex nubmer is:";
	c3 = c1+c2;
	cout<<c3;
	
	cout<< " \n Subtraction of the two given complex nubmer is:";
	c3 = c1-c2;
	cout<<c3;
	
	cout<< " \n Multiplication of the two given complex nubmer is:";
	c3 = c1*c2;
	cout<<c3;
	
	
	
	
}
