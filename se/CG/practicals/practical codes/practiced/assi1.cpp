#include <stdlib.h>
#include <iostream>
#include <graphics.h>
#include <conio.h>
using namespace std;
ffill(int x , int y , int o_col , int n_col){
	int current= getpixel(x , y);
	if (current == o_col){
		delay(0.3);
		putpixel(x, y , n_col);
		ffill(x+1 , y , o_col , n_col);
		ffill(x-1 , y , o_col , n_col);
		ffill(x , y+1 , o_col , n_col);
		ffill(x , y-1 , o_col , n_col);
	}
	
}
int main(){
	int gdriver = DETECT , gmode;
	initgraph(&gdriver , &gmode , NULL);
	int x1 , x2 , x3 , y1 , y2 , y3 , xavg , yavg;
	cout<<"Enter the cordinates of triangle:\n";
	cin>>x1>>y1>>x2>>y2>>x3>>y3;
	xavg = (int) (x1 + x2 + x3)/3;
	yavg = (int) ( y1 + y2 + y3)/3;
	setcolor(YELLOW);
	line(x1 , y1 , x2 , y2);
	line(x2 , y2 , x3 , y3);
	line(x1 , y1 , x3 , y3);
	ffill(xavg , yavg , BLACK , RED);
}
