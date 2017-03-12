package ahg;
import java.io.*;
import java.util.Scanner;
public class Search {
	int Search1(String text)
	{  Search tv=new Search();
	char[] tt;
	 int m=0,l=0;
	tt=new char[1000000];
	try{
	    FileInputStream fin=new FileInputStream("D:\\m.txt");
	    
	    while((m=fin.read())!=-1){


	   tt[l++]=(char)m;
	}
	    fin.close();
	  }catch(Exception e){System.out.println(e);}
	Scanner reader=new Scanner(System.in);
		//String text;
	//	System.out.println("Enter Word");
		// reader=new Scanner(System.in);
	//text=reader.next();

		tv.KMP(tt,text.toCharArray(),l,0);
		return 1;

	}

	  public boolean KMP(char []text, char []pattern,int l,int ex){

	        int lps[] = computeTemporaryArray(pattern);
	        int i=0;
	        int j=0,p=0,pp=0;

		i=ex;
	if(ex<=l)
	{
	        while(i < l && j < pattern.length){
	            if(text[i] == pattern[j]){
	                i++;
	                j++;
					p=i;
	            }else{
	                if(j!=0){
	                    j = lps[j-1];
	                }else{
	                    i++;
	                }
	            }
	        }
	        if(j == pattern.length){
				pp=p-j;
				System.out.println("hii"+pp);
				//v
				try
				 {
					 FileWriter frw = new FileWriter("D:\\ab.txt",true);
					 BufferedWriter brw = new BufferedWriter(frw);
					 
						 
						 brw.write(""+pp+" "); //It will write original sentence and cleaned sentence with a seperator 
						// brw.newLine();
					 
					 brw.close();
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }
				
				KMP(text,pattern,l,p);
				
				
				
			
				//System.out.println("hii"+p);

	            pp=1;
	}}
	if(pp==1)
		return true;
	        return false;
	    }

		  private int[] computeTemporaryArray(char pattern[]){
	        int [] lps = new int[pattern.length];
	        int index =0;
	        for(int i=1; i < pattern.length;){
	            if(pattern[i] == pattern[index]){
	                lps[i] = index + 1;
	                index++;
	                i++;
	            }else{
	                if(index != 0){
	                    index = lps[index-1];
	                }else{
	                    lps[i] =0;
	                    i++;
	                }
	            }
	        }
	        return lps;
	    }


	}



