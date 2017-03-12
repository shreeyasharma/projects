package ahg;




import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class lemmatization
{

    protected StanfordCoreNLP ppl;

    public lemmatization() 
    {
        // Create StanfordCoreNLP object properties, with POS tagging
        // (required for lemmatization), and lemmatization
        Properties pr;
        pr = new Properties();
        pr.put("annotators", "tokenize, ssplit, pos, lemma");

        /*
         * This is a pipeline that takes in a string and returns various analyzed linguistic forms. 
         * The String is tokenized via a tokenizer (such as PTBTokenizerAnnotator), 
         * and then other sequence model style annotation can be used to add things like lemmas, 
         * POS tags, and named entities. These are returned as a list of CoreLabels. 
         * Other analysis components build and store parse trees, dependency graphs, etc. 
         * 
         * This class is designed to apply multiple Annotators to an Annotation. 
         * The idea is that you first build up the pipeline by adding Annotators, 
         * and then you take the objects you wish to annotate and pass them in and 
         * get in return a fully annotated object.
         * 
         *  StanfordCoreNLP loads a lot of models, so you probably
         *  only want to do this once per execution
         */
        this.ppl = new StanfordCoreNLP(pr);
    }

    public LinkedList<String> lemm(String dtext)
    {
    	LinkedList<String> le = new LinkedList<String>();
        // Create an empty Annotation just with the given text
        Annotation doc1 = new Annotation(dtext);
        // run all Annotators on this text
        this.ppl.annotate(doc1);
        // Iterate over all of the sentences found
        List<CoreMap> sent = doc1.get(SentencesAnnotation.class);
        for(CoreMap sentence: sent) 
        {
            // Iterate over all tokens in a sentence
            for (CoreLabel token: sentence.get(TokensAnnotation.class)) 
            {
                // Retrieve and add the lemma for each word into the
                // list of lemmas
                le.add(token.get(LemmaAnnotation.class));
            }
        }
        return le;
    }

}