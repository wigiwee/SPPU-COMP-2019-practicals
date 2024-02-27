#include<iostream>

using namespace std;

class publication
	{
		public:
			
		char title[25];
		float price;
		
		void getdata()
			{
				cout<<"\n enter title of the book: ";
				cin>>title;
				
				cout<<"\n enter price of the book: ";
				cin>>price;
			}
	};
	
	
	class book : public publication
		{
			int page_count;
			
			public:
				void getdata()	
					{
						cout<<"\n enter number of pages for the book:";
						cin>>page_count;	
					}	
				
				void display(publication p)	
					{
						cout<<"\n book information...";
						cout<<"\n Title: "<<p.title;
						cout<<"\n Price: "<<p.price;
						cout<<"\n Number of Pages: "<<page_count;
					}
		};
		
	class tape : public publication
		{
			float playing_time;
			
			public:
				void getdata()	
					{
						cout<<"\n enter playing time in minutes for cassette:";
						cin>>playing_time;	
					}	
				
				void display(publication p)	
					{
						cout<<"\n book information...";
						cout<<"\n Title: "<<p.title;
						cout<<"\n Price: "<<p.price;
						cout<<"\n Play Time of Cassette: "<<playing_time;
					}	
		};
		
		int main()
			{
				int ch;
				publication p;
				book b;
				tape t;
				
				while(1)
					{
						cout<<"\n 1. enter publication info.";
						cout<<"\n 2. enter book info.";
						cout<<"\n 3. enter cassette info.";
						cout<<"\n 4. exit";
						cout<<"\n Enter your Choice: ";
						cin>>ch;
						
						switch(ch)
							{
								case 1:
										p.getdata();
										break;	
								case 2: 
										b.getdata();
										b.display(p);
										break;
								case 3:
										t.getdata();
										t.display(p);
										break;
								case 4:
										exit(0);						
							}	
					}	
			}	
	
