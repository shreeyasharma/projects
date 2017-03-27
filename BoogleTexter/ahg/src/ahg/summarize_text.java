package ahg;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.LinkedList;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class summarize_text 
{
	//Open NLP based sentence and word extraction
		public void summarize_text1(String Src_File, String SF_File, String M_file, String Stp_file)
		{
			Hashtable<String, Integer> stpwrd= new Hashtable<String, Integer>();
			try
			{
				FileReader a1 = new FileReader(Stp_file);
				BufferedReader b1 =new BufferedReader(a1);
				for(String line1=b1.readLine();line1!=null;line1=b1.readLine())
				{
					String tmp=line1.trim();
					if(tmp.length()>0)
					{
						if(stpwrd.containsKey(tmp)==false)
						{
							stpwrd.put(tmp, 1);
						}
					}
				}
				b1.close();
			 }
			 catch(Exception e)
			 {	
				 e.printStackTrace();
			 }
			
			//Read the file
			String Doctxt = "";//store the complete document text
			try
			{
				File M_File = new File(M_file);
				String MInFile = M_File.getAbsolutePath();
				InputStream modelIn = new FileInputStream(MInFile);
		        SentenceModel mod1 = null;
		       // model = new SentenceModel(modelIn);
		        
		        try 
		        {
		           mod1 = new SentenceModel(modelIn);  
		        }
		        catch (IOException e) 
		        {
		          e.printStackTrace();
		        }
		        finally 
		        {
		          if (modelIn != null) 
		          {
		            try 
		            {
		              modelIn.close();
		            }
		            catch (IOException e) 
		            {
		            	e.printStackTrace();
		            }
		          }
		        }
		        SentenceDetectorME sDetect = new SentenceDetectorME(mod1);
		      
				//read the complete file
				try
				{
					FileReader a1 = new FileReader(Src_File);
					BufferedReader b1 = new BufferedReader(a1);
					String S="";
					for(S=b1.readLine();S!=null;S=b1.readLine())
					{
						Doctxt=Doctxt+" "+S;
					}
					b1.close();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				//Separate sentences
				String sent[] = sDetect.sentDetect(Doctxt.trim());
				
				//write original file to text
				 try
				 {
					 FileWriter aw = new FileWriter(SF_File);
					 BufferedWriter bw = new BufferedWriter(aw);
					 for(int j=0;j<sent.length;j++)
					 {
						 String fstring = txt_summ(sent[j].trim(), stpwrd);
						 bw.write(sent[j].trim()+"#"+fstring); //It will write original sentence and cleaned sentence with a seperator 
						 bw.newLine();
					 }
					 bw.close();
				 }
				 catch(Exception e)
				 {
					 e.printStackTrace();
				 }

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}//end of function

		// Apply sentence by sentence cleaning
		public String txt_summ(String Sent1, Hashtable<String, Integer> stp1)
		{
			String rtxt = "";
			lemmatization sl = new lemmatization();
			LinkedList<String> lemtxt = new LinkedList<String>();
			lemtxt = sl.lemm(Sent1);
			
			//Now clean the text
			for(int i=0;i<lemtxt.size();i++)
			{
				String tmp = lemtxt.get(i).toString().trim().toLowerCase();
				if(tmp.length()>0)
				{
					if(stp1.containsKey(tmp)==false)
					{
						if((tmp.matches("[a-z]+"))||(tmp.matches("[0-9]"))||(tmp.matches("[a-z]+[0-9]+"))||(tmp.matches("[0-9]+[a-z]+")))
						{
							rtxt=rtxt+" "+tmp;
						}
					}
				}

			}
						
			return(rtxt.trim());
		}//End of public String text_Cleaning(String Sentence1, LinkedList<String> stopwords1)
		
	/*	public String Basic_Sentence_Cleaning(String Sentence1)
		{
			String Partially_cleaned_Sent = "";
			char [] char_array = Sentence1.toCharArray();
			String tmp_sentence="";
			for(int i=0;i<char_array.length;i++)
			{
				//if((Character.isAlphabetic(char_array[i])) ||(Character.isDigit(char_array[i])) || (Character.isSpaceChar(char_array[i])))
				if(((Character.toString(char_array[i]).matches("[a-z]")))||((Character.toString(char_array[i]).matches("[A-Z]"))) ||(Character.isDigit(char_array[i])) || (Character.isSpaceChar(char_array[i])))
				{
					tmp_sentence = tmp_sentence + char_array[i];
				}
				if((char_array[i]=='-')||(char_array[i]=='.')||(char_array[i]=='_')||(char_array[i]==':')||(char_array[i]==',')||(char_array[i]=='\'')||(char_array[i]=='"')||(char_array[i]==')')||(char_array[i]=='(')||(char_array[i]==']')||(char_array[i]=='['))
				{
					tmp_sentence = tmp_sentence + char_array[i];
				}
			}
			String [] tmp_Sentence1 = tmp_sentence.split(" ");
			//remove additional spaces
			LinkedList<String> Sent_words = new LinkedList<String>();
			for(int j=0;j<tmp_Sentence1.length;j++)
			{
				String tmp_wd = tmp_Sentence1[j].trim();
				if(tmp_wd.length()>0)
				{
					Sent_words.add(tmp_wd);
				}
			}
			
			for(int i=0;i<Sent_words.size();i++)
			{
				Partially_cleaned_Sent=Partially_cleaned_Sent+" "+Sent_words.get(i).toString();
			}
			
			return(Partially_cleaned_Sent.trim());
		}*/
}
