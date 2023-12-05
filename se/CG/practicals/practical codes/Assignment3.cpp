#include<stdio.h>
#include<conio.h>
#include<graphics.h>
int main(){
int gd = DETECT,gm;
int x ,y ,radius=100;
initgraph(&gd, &gm, "C:\\TC\\BGI");
x = getmaxx()/2;
y = getmaxy()/2;
outtextxy(220,75, "Circle inside a Rectangle");
circle(x, y, radius);
rectangle(420, 340, 218, 138);
outtextxy(220, 400, "Press any key to continue...");
getch();
closegraph();
return 0;
}