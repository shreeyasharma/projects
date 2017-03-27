#include<iostream>
#include<windows.h>
#include<stdio.h>
#include <stdlib.h>
#include<string>                  //contains strcmp(),strcpy(),strlen(),etc
#include<ctype.h>                   //contains toupper(), tolower(),etc
#include<dos.h>                     //contains _dos_getdate
#include<time.h>
#include<conio.h>
#include<fstream>
using namespace std;
class nonmetal;
class metal;
class metalloid;
class element
{ protected:
    int i2;
    char nam2[5];
    char ch1[20];
    char blo[20];
    nonmetal *nm;
    metal *m;
    metalloid *md;

public:
    element()
    {}
    void full(char name[10],int j);
    void comparerad(char name[][10],int n);
   //virtual void covalency()=0;
//virtual void caloxipot()=0;
//virtual void calradius()=0;

};
/*void Addelement()
{
    cout<<"enter the name and type of the element";
    cin>>addel>>type;
    if(strcmp)



}*/
struct ele
{
 char name[10];
int a_no;
char ch1[10];
char ch[10];
int a_mass;
int g_no;
char state[10];
};
void config(int a_no,char ch[3],int a_mass,int g_no,int p_no)
{
    //cout<<"\n";
    int j=0;
    int a[6]={2,8,8,18,18,32};
   for(int i=0;i<p_no;i++)
   {
       if(a_no>=a[i])
       {
           cout<<"\n";
           cout<<a[i]<<"\t";
           j=j+a[i];
   }
   }
   int w=a_no-j;
if(w!=0)
{

    cout<<w;
}
cout<<"\n";
//cout<<p_no<<ch;
int k=0,l;
char str1[1];
itoa(p_no,str1,10);
str1[1]=ch[0];
//str1[2]='/0';
//cout<<str1;
//std::string s = std::to_string(5);
char str[][3]={"1S","2S","2P","3S","3P","4S","3D","4P","5S","4D","5P","6P","4F","5D","6P","4F","5D","6P","7S"};
int b[16]={2,2,6,2,6,2,10,6,2,10,6,6,14,10,6,2};
int m=0;
int h=0;
for(h=0;h<16;h++)
{
//printf("%s",str[h]);
    //cout<<"\n check"<<str1<<str[h]<<strcmp(str1,str[h]);
  if(strcmp(str1,str[h])!=0)
    { //cout<<"hello";
        cout<<str[h]<<b[h]<<"  ";
        k=h+1;
         m=m+b[h];


    }
else{break;}

}  cout<<str[k]<<a_no-m;}

//cout<<str[k+1]<<a_no-m;
//}
class s_block
{
    int n_elements;
    int p_no1;
    int g_no1;
    //string e_name;
    //string propname;
    element *e;
public:
    s_block()
{
    cout<<"\n\n\n\t\t\t*******IN S_block*******";
  n_elements=13;
   p_no1=7;
   g_no1=2;
   cout<<"\n\tnumberof elements-> "<<n_elements;
   cout<<"\n\tnumber of periods-> "<<p_no1;
   cout<<"\n\tnumber of groups-> "<<g_no1;
}

};
class p_block
{
    int n_elements;
    int p_no1;
    int g_no1;
    //string e_name;
    //string propname;
    element*e;
public:
    p_block()
{
    cout<<"\n\n\n\t\t\t*******IN P block*******";
  n_elements=37;
   p_no1=7;
   g_no1=6;
  cout<<"\n\tnumberof elements-> "<<n_elements;
   cout<<"\n\tnumber of periods-> "<<p_no1;
   cout<<"\n\tnumber of groups-> "<<g_no1;
}

};
class d_block
{
       int n_elements;
    int p_no1;
    int g_no1;
    //string e_name;
    //string propname;
    element*e;
public:
    d_block()
{
    cout<<"\n\n\n\t\t\t*******IN d block*******";
  n_elements=38;
   p_no1=4;
   g_no1=10;
   cout<<"\n\tnumberof elements-> "<<n_elements;
   cout<<"\n\tnumber of periods-> "<<p_no1;
   cout<<"\n\tnumber of groups-> "<<g_no1;
}


};
class f_block
{
    int n_elements;
    int p_no1;
    int g_no1;
   // string e_name;
    //string propname;
    element *e;
public:
    f_block()
{
    cout<<"\n\n\n\t\t\t*******IN f block*******";
  n_elements=30;
   p_no1=2;
   g_no1=1;
   cout<<"\n\tnumberof elements-> "<<n_elements;
   cout<<"\n\tnumber of periods-> "<<p_no1;
   cout<<"\n\tnumber of groups-> "<<g_no1;
}


};
class metal:public element
{
 int i;
char ch[20];
char ch1[20];
int a_no,a_mass;
char blo[20];
int g_no;
int p_no;
int s_no;
struct ele a;
   //  friend void radius(int i2,char nam2[10],char ch1[10],char blo[10]);
public:
    metal()
    {

    }
    struct ele search1(int index,char symbol[20],char name[20],char state[20],int j)
    {
int i;
FILE *fp;
struct ele a;
fp=fopen("metal.txt","r");
int i1;
while(i1!=1)
{
   // cout<<"hii";
fscanf(fp,"%d %s %s %d %d %s %d %d",&a_no,ch1,blo,&g_no,&p_no,ch,&a_mass,&s_no);
if(a_no==index)

{i1=1;
    if(j==0)
    {


    cout<<"\n\n\t\t Details of "<<name;
    cout<<"\nAtomic no. of Element->"<<a_no;
    cout<<"\n symbol->"<<ch1;
cout<<"\nBlock name->"<<ch;
cout<<"\n Atomic mass->"<<a_mass;
cout<<"\n group no->"<<g_no;
cout<<"\n type of element->"<<state;
i1=1;
    }
    else if(j==1)
    {
        config(a_no,ch,a_mass,g_no,p_no);
        i1=1;
    }
    else if(j==3)
    {
        strcpy(a.name,name);
        a.a_no=a_no;
        a.g_no=g_no;
    strcpy(a.state,state);
    strcpy(a.ch1,ch1);
    strcpy(a.ch,ch);
    a.a_mass=a_mass;
    return a;
    cout<<a_mass;
    }
}
else{i++;}
    }
    }

    };


class nonmetal:public element
{
 int i;
 int s_no;
char ch[20];
char ch1[20];
int a_no,a_mass;
char blo[20];
int g_no;
int p_no;
 //friend void radius(int i2,char nam2[10],char ch1[10],char blo[10]);
public:
    void search1(int index,char symbol[20],char name[20],char state[20],int j)
    {
int i;
int s_no;
FILE *fp;
fp=fopen("nonmetal.txt","r");
int i1;
while(i1!=1)
{
fscanf(fp,"%s %d %d %s %d %s %d %d",ch1,&a_no,&a_mass,ch,&g_no,blo,&p_no,&s_no);

if(a_no==index)

{
    if(j==0)
    {


    cout<<"\n\n\t\t Details of "<<name;
    cout<<"\nAtomic no. of Element->"<<a_no;
    cout<<"\n symbol->"<<ch1;
cout<<"\nBlock name->"<<ch;
cout<<"\n Atomic mass->"<<a_mass;
cout<<"\n group no->"<<g_no;
cout<<"\n type of element->"<<state;
i1=1;
    }
    else if(j==1)
    {
        //cout<<"hii";
        config(a_no,ch,a_mass,g_no,p_no);

        i1=1;
    }


}
else{
        i++;
}
    }
    }
    };
class metalloid:public metal,public nonmetal
{
int i;
char ch[20];
char ch1[20];
int a_no,a_mass;
char blo[10];
int g_no;
int p_no;
int s_no;

    //string mluse;
//    friend void radius(int i2,char nam2[10],char ch1[10],char blo[10]);
public:

    void search1(int index,char sym[20],char name[20],char state[20],int j)
    {
FILE *fp;
fp=fopen("metalloid.txt","r");
int i1;
//cout<<"hii";
while(i1!=1)
{
fscanf(fp,"%s %s %d %d %s %d %d %d",ch1,ch,&a_no,&a_mass,blo,&g_no,&p_no,&s_no);
if(a_no==index)

{
    if(j==0)
    {


    cout<<"\n\n\t\t Details of "<<name;
    cout<<"\nAtomic no. of Element->"<<a_no;
    cout<<"\n symbol->"<<ch;
cout<<"\nBlock name->"<<blo;
cout<<"\n Atomic mass->"<<a_mass;
cout<<"\n group no->"<<g_no;
cout<<"\n type of element->"<<state;
i1=1;
}
else if(j==1)
    {
        config(a_no,ch,a_mass,g_no,p_no);
        i1=1;
    }
}
else{i++;}
    }
    fclose(fp);
    }
        };



void radius(char m[][10],int *a,char symbol[][10],char state[][10])
{

    char ch1[10];int a_no,a_mass,g_no,s_no,p_no,serial[10];
    char ch[10], blo[10];
    int h;FILE*fp,*fp1,*fp2;
    int i1,i11,i111;
    struct ele e;
    for(int i=0;i<3;i++)
    { h=0;
     i1=0;
   metal m;//fp= fopen("metal.txt","r");
   fp1=fopen("nonmetal.txt","r");
   fp2=fopen("metalloid.txt","r");

        if(strcmp(state[i],"Metal")==0)
        {
         int   t=a[i];
         char sym[10],m1[10],state1[10];
         strcpy(sym,symbol[i]);
         //m1=m[i];
         strcpy(state1,state[i]);
      e=m.search1(t,sym,m1,state1,3);
      cout<<e.a_mass;
        }
/*{

fscanf(fp,"%d %s %s %d %d %s %d %d",&a_no,ch1,blo,&g_no,&p_no,ch,&a_mass,&s_no);
if(a_no==a[i])
{i1=1;
serial[i]=s_no;
fclose(fp);
//cout<<a_no;
}}
        }
else
    {
        h++;
    }*/


         /*if(strcmp(state[i],"Non-metal")==0)
        {
            while(i1!=1)
{
   // metal m1;
  fscanf(fp1,"%s %d %d %s %d %s %d %d",ch1,&a_no,&a_mass,ch,&g_no,blo,&p_no,&s_no);
if(a_no==a[i])
{i1=1;
serial[i]=s_no;
fclose(fp1);
cout<<a_no;
}}
        }
else
    {
        h++;
    }
}
if(strcmp(state[i],"Metalloid")==0)
        {
            while(i1!=1)
{
   // metal m1;
 fscanf(fp2,"%s %s %d %d %s %d %d",ch1,ch,&a_no,&a_mass,blo,&g_no,&p_no);
if(a_no==a[i])
{i1=1;
serial[i]=s_no;
fclose(fp2);
cout<<a_no;
}}
        }
else
    {
        h++;
    }
}
}*/}}
void element::comparerad(char name[][10],int n)
{
    int m[10];
    char symbol[10][10],state[10][10];
    FILE *fp;
    fp=fopen("classifyfinal.txt","r");
    for(int h=0;h<n;h++)
    {
        for(int i=0;i<50;i++)
        {

fscanf(fp,"%d %s %s %s",&i2,nam2,ch1,blo);


if(!strcmp(name[h],ch1))
{
    m[h]=i2;
   // cout<<nam2;
    //cout<<blo;
    strcpy(symbol[h],nam2);
    strcpy(state[h],blo);
}

     }rewind(fp);   }
radius(name,m,symbol,state);
fclose(fp);


}

void element:: full(char name[10],int j)
{

   FILE *fp;
    fp=fopen("classifyfinal.txt","r");
    for(int i=0;i<50;i++)
        {

fscanf(fp,"%d %s %s %s",&i2,nam2,ch1,blo);


if(!strcmp(name,ch1))
{


if(!strcmp(blo,"Non-metal"))
{
   nonmetal obj;
    obj.search1(i2,nam2,ch1,blo,j);
}
if(!strcmp(blo,"Metal"))
{
    metal obj2;
    cout<<i2;
    obj2.search1(i2,nam2,ch1,blo,j);
}
if(!strcmp(blo,"Metalloids"))
{
    metalloid obj3;
    obj3.search1(i2,nam2,ch1,blo,j);
}
}

        }
fclose(fp);

}

class periodic_table
{
char block_name[10];
int n_element;
int p_no;
int g_no;
s_block obj1;
p_block obj2;
d_block obj3;
f_block obj4;
public:
periodic_table()
{
n_element=118;
p_no=6;
g_no=18;
}
void display()
{
    cout<<"\nno of elements->"<<n_element;
    cout<<"\nno. of total periods->"<<p_no;
    cout<<"\nno. of groups->"<<g_no;
}
};
int main()
{
  element e;  char name[10];
    int i;
char ch[20];
char ch1[20];
char a[10][10];
int a_no,a_mass;
char blo[20];
int g_no;
metal ob;
int j;
cout<<"\n \t\t\t\tEnter the corresponding no";
	cout<<"\n1.Radioactivity";
	cout<<"\n2.Explore";
	cout<<"\n3.physical properties of an element";
	cout<<"\n4.comparison";
	cout<<"\n5.Electron configuration";
	cout<<"\n6.Add a newly Discovered Element";

cin>>i;
switch(i)
{
case 1:
system("cls");


	//gotoxy(5,6);
	//cin>>i;

break;
case 2:


    system("cls");
cout<<"Enter name of atom";
cin>>name;
 e.full(name,0);
    break;
case 3:
    system("cls");
break;
case 4:
    cout<<"\n \t\t\t\tEnter the corresponding no";
	cout<<"\n1.compare the radius";
	cout<<"\n2.compare electron affinity";
	cout<<"\n3.compare elctronegativity";
	cout<<"\n4.compare melting and boiling point";
	cout<<"\n5.compare density";
	cout<<"\n6.exit";
    cin>>j;
switch(j)
{


 case 1:
     int n;
     system("cls");
     cout<<"no. of element";
     cin>>n;
     for(int i=0;i<n;i++)
     {
         cout<<"Element name->";
         cin>>a[i];
     }
  e.comparerad(a,n);
break;

    break;
 case 2:
  //  compareea();
    break;
 case 3:
    //comaprerad();
    break;
 case 4:
    //comparebm();
    break;
 case 5:
    //comparedens();
    break;
}
break;
case 5:

//element e;
    system("cls");
cout<<"Enter name of atom";
cin>>name;
 e.full(name,1);
 break;
}
return 0;
}
