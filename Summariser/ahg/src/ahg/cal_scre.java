package ahg;


import java.util.Hashtable;
import java.util.LinkedList;

public class cal_scre 
{
	public class val
	{
		int vv1;
		int vv2;
		val(int v1, int v2)
		{
			this.vv1=v1;
			this.vv2=v2;
		}
		public void uval(int v1, int v2)
		{
			this.vv1=v1;
			this.vv2=v2;
		}
	}//end of class values
	public double cs_scr(String t1, String t2)
	{
		double sscr=0.0000000;
		//1. Identify distinct words from both documents
		String [] stxt1 =t1.split(" ");
		String [] stxt2 = t2.split(" ");
		Hashtable<String, val> wfrq = new Hashtable<String, cal_scre.val>();

		LinkedList<String> d_wrd = new LinkedList<String>();
		
		//prepare word frequency vector by using Text1
		for(int i=0;i<stxt1.length;i++)
		{
			String tmp = stxt1[i].trim();
			if(tmp.length()>0)
			{
				if(wfrq.containsKey(tmp))
				{
					val v1 = wfrq.get(tmp);
					int f1 = v1.vv1+1;
					int f2 = v1.vv2;
					v1.uval(f1, f2);
					wfrq.put(tmp, v1);
				}
				else
				{
					val v1 = new val(1, 0);
					wfrq.put(tmp, v1);
					d_wrd.add(tmp);
				}
			}
		}
		
		//prepare word frequency vector by using Text2
		for(int i=0;i<stxt2.length;i++)
		{
			String tmp = stxt2[i].trim();
			if(tmp.length()>0)
			{
				if(wfrq.containsKey(tmp))
				{
					val v1 = wfrq.get(tmp);
					int f1 = v1.vv1;
					int f2 = v1.vv2+1;
					v1.uval(f1, f2);
					wfrq.put(tmp, v1);
				}
				else
				{
					val v1 = new val(0, 1);
					wfrq.put(tmp, v1);
					d_wrd.add(tmp);
				}
			}
		}
		
		//calculate the cosine similarity score.
		double vab = 0.0000000;
		double va = 0.0000000;
		double vb = 0.0000000;
		
		for(int i=0;i<d_wrd.size();i++)
		{
			val v11 = wfrq.get(d_wrd.get(i));
		
			double f1 = (double)v11.vv1;
			double f2 = (double)v11.vv2;
			//System.out.println(Distinct_words_text_1_2.get(i)+"#"+freq1+"#"+freq2);
			
			vab=vab+(f1*f2);
			
			va = va + f1*f1;
			vb = vb + f2*f2;
		}

		//System.out.println("VectAB "+VectAB+" VectA_Sq "+VectA_Sq+" VectB_Sq "+VectB_Sq);
		sscr = ((vab)/(Math.sqrt(va)*Math.sqrt(vb)));
		
		return(sscr);
	}
}