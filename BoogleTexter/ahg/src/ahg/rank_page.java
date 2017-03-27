package ahg;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.scoring.PageRank;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.EdgeType;

public class rank_page 
{
	double [][] wgh = new double[10000][10000];
	//calculate score of all sentences of Appended_textAB.txt
	public void calpr(String SFile, String TFile)
	{
		LinkedList<String> B = new LinkedList<String>();//contains the original text # Lemmantized text
		LinkedList<String> A = new LinkedList<String>();//contains lemmantize text only
		try
		{
			FileReader a1 = new FileReader(SFile);
			BufferedReader b1 = new BufferedReader(a1);
			for(String line1 = b1.readLine();line1!=null;line1=b1.readLine())
			{
				String [] tmpA = line1.trim().split("#");
				if(tmpA.length==2)
				{
					B.add(line1);
					A.add(tmpA[1].trim());
				}
			}
			b1.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//calculate similarity score and fill the matrix
		cal_scre cs = new cal_scre();
		
		//initialize the weight of the matrix to zero
		for(int i=0;i<2000;i++)
    	{
    		for(int j=0;j<2000;j++)
    		{
    			wgh[i][j]=0.0000000;
    		}
    	}
		
		int rsize = A.size();
		if(A.size()>=2000)
		{
			rsize=2000;
		}
		
		for(int i=0;i<rsize;i++)
		{
			for(int j=0;j<rsize;j++)
			{
				if(i!=j)
				{
					double twt = cs.cs_scr(A.get(i), A.get(j));
					wgh[i][j] = (twt);
				}
				else
				{
					wgh[i][j]=0.0000000;
				}
			}
		}
		
		//--------------
		int msize = rsize;    
    
    	Graph<Integer, String> gph = new DirectedSparseGraph<Integer, String> ();
    	for(int i=0;i<2000;i++)
    	{
    		gph.addVertex(i);
    	}
        //prepare bi-directed or undirected graph
    	for(int r=0;r<2000;r++)
    	{
    		for(int c=0;c<2000;c++)
    		{
    			if(wgh[r][c]>0.0000000)
    			{
    				String tedge = r+"->"+c;
    				gph.addEdge(tedge, r, c, EdgeType.DIRECTED);
    				String tedge1 = c+"->"+r;
    				gph.addEdge(tedge1, c, r, EdgeType.DIRECTED);
    			}
    		} 
    	}
       
    	Transformer<String, Double> ewgh = 
               
    			new Transformer<String, Double>()
               {
            @Override
                    public Double transform(String e) 
                    {
                        String[] split = e.split("->");           
                        return wgh[Integer.parseInt(split[0])][Integer.parseInt(split[1])];
                    }           
               };
                       
       PageRank<Integer, String> pp = new PageRank<Integer, String>(gph, ewgh, 0.85); 
       pp.setMaxIterations(30); 
       pp.evaluate();
       //Now write the ranked list to the file
       LinkedList<Double> prs = new LinkedList<Double>();
       for(int m=0;m<msize;m++)
       {
    	   prs.add(pp.getVertexScore(m));
       }
       srlist(B, prs, TFile);
	}//End of public void CalculatePR_Score(String SourceFile, String TargetFile)
		
	//Function to print the sorted list to file
	public void srlist(LinkedList<String> l1, LinkedList<Double> l1wgh, String Tname)
	{
		String [] txt1 = new String[l1.size()];
		double [] twgh = new double[l1.size()];
		for(int i=0;i<l1.size();i++)
		{
			//String [] tmp_list = list1.get(i).split("#");
			txt1[i] = ""+i+"#"+l1.get(i);
			twgh[i] = l1wgh.get(i);
		}
		int n = l1.size();
		//Now sort the data in descending order of the extracted weight
	    for (int c = 0; c < ( n - 1 ); c++) 
	    {  
	    	for (int d = 0; d < n - c - 1; d++) 
	    	{
	          if (twgh[d] < twgh[d+1]) /* For descending order use < */
	          {
	            double swap       = twgh[d];
	            String tmp1 = txt1[d];
	            
	            twgh[d]   = twgh[d+1];
	            txt1[d]  = txt1[d+1];
	            
	            twgh[d+1] = swap;
	            txt1[d+1] = tmp1;
	          }
	    	}
	    }
	    //Now print the data to file
	    try
	    {
	    	FileWriter aw1 = new FileWriter(Tname);
	    	BufferedWriter bw1 = new BufferedWriter(aw1);
	    	for(int i=0;i<n;i++)
	    	{
	    		bw1.write(""+twgh[i]+"#"+txt1[i]);
	    		bw1.newLine();
	    	}
	    	bw1.close();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }    	
	}
	
		
	//Extracting few top sentences in the form of ordered summary
	public void ESen(String RFile, String SFile, int ssize)
	{
		//read ranked files and store it in a list
		LinkedList<String> sent = new LinkedList<String>();
		try
		{
			FileReader a1 = new FileReader(RFile);
			BufferedReader b1 = new BufferedReader(a1);
			for(String line1 = b1.readLine();line1!=null;line1=b1.readLine())
			{
				String tmp = line1.trim();
				sent.add(tmp);
			}
			b1.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(ssize>=sent.size())
		{
			ssize=sent.size();
		}
		//Now extract the required number of sentences to work on it
		String [] reqsent = new String[ssize];
		//int [] sent_order = new int [sum_size];
		for(Integer i= 0;i<ssize;i++)
		{
			String [] tl = sent.get(i).toString().split("#");
			//sent_order[i] = Integer.parseInt(tmp_line[1].trim());
			reqsent[i] = tl[0].trim();
		}
		
		//Now sort the entry and present the result
		/*
		int n = sum_size;
		//Now sort the data in descending order of the extracted weight
	    for (int c = 0; c < ( n - 1 ); c++) 
	    {  
	    	for (int d = 0; d < n - c - 1; d++) 
	    	{
	          if (sent_order[d] > sent_order[d+1]) // For descending order use < 
	          {
	            int swap       = sent_order[d];
	            String tmp_dt = req_sentences[d];
	            
	            sent_order[d]   = sent_order[d+1];
	            req_sentences[d]  = req_sentences[d+1];
	            
	            sent_order[d+1] = swap;
	            req_sentences[d+1] = tmp_dt;
	          }
	    	}
	    }   
	    */
	    //Now write the summary to file
	    try
	    {
	    	FileWriter aw1 = new FileWriter(SFile);
	    	BufferedWriter bw1 = new BufferedWriter(aw1);
	    	for(int i=0;i<ssize;i++)
	    	{
	    		bw1.write(reqsent[i]);
	    		bw1.newLine();
	    	}
	    	bw1.close();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
		
	}//End of public void Extract_Summ_Sentences(String RankedFile, String SummFile, int sum_size)
	
	public String RF(String SFile)
	{
		String s="";
		try
		{
			FileReader a1= new FileReader(SFile);
			BufferedReader b1 = new BufferedReader(a1);
			for(String line1 = b1.readLine();line1!=null;line1=b1.readLine())
			{
				s= s+"\n"+line1+"\n";
			}
			b1.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return(s);
	}
}
