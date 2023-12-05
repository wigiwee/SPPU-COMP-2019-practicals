#include<conio.h>
#include<iostream.h>
#include<graphics.h>
#include<stdlib.h>
#include<dos.h>
void main(){
int gd=DETECT,gm;
initgraph(&gd,&gm,"C:\\TurboC3\\BGI");
int xmov,x,y;
//xmov=10;
for(xmov=1;xmov<200;xmov=xmov+5)
{
line(0,400,639,400);
circle(30+xmov,280,20); //head
line(30+xmov,300,30+xmov,350);//body
line(30+xmov,330,70+xmov,330); //hand
if(xmov%2==0)
{
line(30+xmov,350,25+xmov,400); //left leg
line(30+xmov,350,10+xmov,400); //right leg
}
else
{
line(30+xmov,350,25+xmov,400);
delay(25);
}
line(70+xmov,250,70+xmov,330); //umbrella
pieslice(80+xmov,250,180,0,80);
for(int i=0;i<=300;i++)
{
x=random(800);
y=random(800);
outtextxy(x,y,"/");
}
delay(600);
cleardevice();
}
getch();
closegraph();
}