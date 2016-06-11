/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owl;

/**
 *
 * @author Rômulo Ferreira
 */

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.swing.JOptionPane;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.io.SystemOutDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyIRIMapper;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLClassAtom;
import org.semanticweb.owlapi.model.SWRLObjectPropertyAtom;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.DefaultPrefixManager;
import org.semanticweb.owlapi.util.PriorityCollection;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.semanticweb.owlapi.vocab.OWL2Datatype;


public class API2 {
    
    
    OWLDataFactory df = OWLManager.getOWLDataFactory();
    OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
   
    
    public void PropertyAssertions() throws OWLOntologyCreationException, OWLOntologyStorageException{
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
//String base = "http://www.semanticweb.org/ontologies/individualsexample";
String base = "http://www.semanticweb.org/owlapi/ontologies/ontology#";
OWLOntology ontologia = manager.createOntology(IRI.create(base));
OWLDataFactory factory = manager.getOWLDataFactory();
File file = new File("C:\\Users\\Rômulo Ferreira\\Desktop\\APi2.owl");
// We would like to state that matthew has a father who is peter.
// We need a subject and object - matthew is the subject and peter is the object.
// We use the data factory to obtain references to these individuals
// We want to state that matthew has a father who is peter.
    String animal = JOptionPane.showInputDialog("Digite um animal: ");
		String tipo = JOptionPane.showInputDialog("Digite um tipo do animal: ");
		
		
		PrefixManager pm = new DefaultPrefixManager(base);
		
		OWLClass clsAMethodA = factory.getOWLClass(":" + animal, pm);
		OWLDeclarationAxiom declarationAxiom = factory.getOWLDeclarationAxiom(clsAMethodA);
		
		manager.addAxiom(ontologia, declarationAxiom);
		
		OWLClass clsAMethodB = factory.getOWLClass(":" + tipo, pm);
		declarationAxiom = factory.getOWLDeclarationAxiom(clsAMethodB);
		
		manager.addAxiom(ontologia, declarationAxiom);
                
		
		
		//manager.saveOntology(ontologia, IRI.create(file.toURI()));
		//manager.saveOntology(ontologia, new SystemOutDocumentTarget());
		
		
		
		
		
		animal = JOptionPane.showInputDialog("Digite outro animal: ");
                String afirmacao = JOptionPane.showInputDialog("Digite a afirmação: ");
		tipo = JOptionPane.showInputDialog("Digite um tipo do animal: ");		
		
                
                String Sanimal[] = animal.split("AND");
                System.out.println(Sanimal[0]);
                System.out.println(Sanimal[1]);
                
		
		clsAMethodA = factory.getOWLClass(IRI.create(base  + animal));
		//declarationAxiom = factory.getOWLDeclarationAxiom(clsAMethodA);
		
		//manager.addAxiom(ontologia, declarationAxiom);
		
		clsAMethodB = factory.getOWLClass(IRI.create(base + tipo));
		//declarationAxiom = factory.getOWLDeclarationAxiom(clsAMethodB);
		
		//manager.addAxiom(ontologia, declarationAxiom);
		
		
		OWLAxiom axiom = factory.getOWLSubClassOfAxiom(clsAMethodB, clsAMethodA);
		AddAxiom addAxiom = new AddAxiom(ontologia, axiom);
		// Use the manager to apply the change
		manager.applyChange(addAxiom);
		manager.saveOntology(ontologia, IRI.create(file.toURI()));
		manager.saveOntology(ontologia, new SystemOutDocumentTarget());
                
                // ----------------Property Assertions-----------
                OWLIndividual matthew = factory.getOWLNamedIndividual(
                        IRI.create(base + animal));
                OWLIndividual peter = factory.getOWLNamedIndividual(
                        IRI.create(base + tipo));
                OWLObjectProperty hasFather = factory.getOWLObjectProperty(
                        IRI.create(base + "#é_um    "));
                OWLObjectPropertyAssertionAxiom assertion =
                        factory.getOWLObjectPropertyAssertionAxiom(null, matthew, peter);
                AddAxiom addAxiomChange = new AddAxiom(ontologia, assertion);
                manager.applyChange(addAxiomChange);
                OWLClass personClass = factory.getOWLClass(IRI.create(base + tipo));
                OWLClassAssertionAxiom ax = factory.getOWLClassAssertionAxiom(
                        personClass, matthew);
                manager.addAxiom(ontologia , ax);
                manager.saveOntology(ontologia, IRI.create(file.toURI()));
		manager.saveOntology(ontologia, new SystemOutDocumentTarget());
                
                /*/------------------Restrictions--------------------
                OWLObjectProperty hasPart = factory.getOWLObjectProperty(IRI.create(base + "#hasPart"));
                OWLClass clsA = factory.getOWLClass(IRI.create(base + "#asas"));
                OWLClassExpression hasPartSomeAsa =
                    factory.getOWLObjectSomeValuesFrom(hasPart, clsA);
                OWLClass pena = factory.getOWLClass(IRI.create(base + "#pena"));
                
                OWLSubClassOfAxiom ax = factory.getOWLSubClassOfAxiom(pena, hasPartSomeAsa);
                // Add the axiom to our ontology
                AddAxiom addAx = new AddAxiom(ontologia, ax);
                manager.applyChange(addAx);*/
// Save our ontology
/*File file = new File("C:\\Users\\Rômulo Ferreira\\Desktop\\APi2.owl");		
		manager.saveOntology(ont, IRI.create(file.toURI()));
*/
    }
    }

