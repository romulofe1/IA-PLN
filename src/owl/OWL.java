
package owl;

import java.io.File;
import java.util.Scanner;
import javax.annotation.Nonnull;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.io.SystemOutDocumentTarget;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDataUnionOf;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDatatypeDefinitionAxiom;
import org.semanticweb.owlapi.model.OWLDatatypeRestriction;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyIRIMapper;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.PriorityCollection;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLFacet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap; 


/**
 *
 * @author Rômulo Ferreira
 */
public class OWL {
    
    	
	public static List<Tree> Teste(String texto) {
		
		List<Tree> lista = new LinkedList<Tree>();
		// creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER, parsing, and coreference resolution 
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		Annotation document = new Annotation(texto);
		
		
		pipeline.annotate(document);
		
		
		// these are all the sentences in this document
		// a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);

		for(CoreMap sentence: sentences) {
		  
		  // this is the parse tree of the current sentence
		  Tree tree = sentence.get(TreeAnnotation.class);
		  tree.pennPrint();
		  lista.add(tree);
		  
		  
		}		
		return lista;
		
		
	}
	
	public static List<Tree> buscarElementoPOS(Tree tree, String tag) {
		List<Tree> lista = new LinkedList<Tree>();
		
		for(Tree t : tree.children()) {
			if(t.value().equalsIgnoreCase(tag)) {
				lista.add(t);
			} else {
				lista.addAll(buscarElementoPOS(t, tag));
			}
		}
		
		
		return lista;
		
	}

	public static void main(String[] args) throws IOException {
		
		for(Tree t : Teste("cat eats meat and fish")) {
			for(Tree t2 : buscarElementoPOS(t, "NN"))				
				System.out.println(t2.children()[0].value());
		}
		

	}
    
        

     /*public static void main(String[] args) throws OWLOntologyCreationException, OWLException{
     
         OWLOntologyManager user = OWLManager.createOWLOntologyManager();
         API ap = new API();
         API2 ap2 = new API2();
         //System.out.println(ap.KOALA_IRI);
         ap.restrictions();
         //ap.testIndividualAssertions();
         //ap2.testeSalvarOntology(cls,ind);
         //ap2.PropertyAssertions();
         //ap2.Restrictions();

         //ap2.AddAxioms();
         
         
         

         
      
}

    private void assertNotNull(OWLOntology o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
}
