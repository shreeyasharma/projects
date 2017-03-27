package ahg;


public class BTexter {

	void summarizer()
	{
		String Src_F = "D:\\my.txt";
		String SF_F = "D:\\22.txt";
		String out = "D:\\cc.txt";
		//String TargetFile2 = "D:\\b.txt";

	//	String Index_Dir_Path = "D:\\Summarization\\DBpedia_Final_Indexes\\";
		//int total_document_count = 2153000;//count of total DBpedia texts
		String Stp_f = "D:\\stopwords.txt";
		String M_f = "D:\\en-sent.bin";

                //Apply Document preprocessing
		summarize_text dc= new summarize_text();
		dc.summarize_text1(Src_F, SF_F, M_f, Stp_f);

		//Apply document summarization, using Traditional Pagerank
              rank_page s = new rank_page();
		s.calpr(SF_F, out);

    
		System.out.println("Operation Complete");
	}

}
