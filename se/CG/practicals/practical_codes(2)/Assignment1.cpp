#include<graphics.h>
#include<iostream>
#include<stdlib.h>
using namespace std;
void ffill(int x,int y,int o_col,int n_col)
{

int current = getpixel(x,y);
if(current==o_col)
{
delay(0.5);
putpixel(x,y,n_col);
ffill(x+1,y,o_col,n_col);
ffill(x-1,y,o_col,n_col);
ffill(x,y+1,o_col,n_col);
ffill(x,y-1,o_col,n_col);
}
}
int main()
{
int x1,y1,x2,y2,x3,y3,xavg,yavg;
int gdriver = DETECT,gmode;
initgraph(&gdriver,&gmode,NULL);
cout << " \nEnter the points of triangle\n";
setcolor(YELLOW); // sets the color of the lines of the triangle
cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3;
xavg = (int)(x1+x2+x3)/3;
yavg = (int)(y1+y2+y3)/3;
line(x1,y1,x2,y2);
line(x2,y2,x3,y3);
line(x3,y3,x1,y1);
ffill(xavg,yavg,BLACK,RED);
getch();
return 0;
}
