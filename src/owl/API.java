
package owl;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nonnull;
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
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyIRIMapper;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLClassAtom;
import org.semanticweb.owlapi.model.SWRLObjectPropertyAtom;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.PriorityCollection;

public class API {
    
    public void restrictions() throws OWLOntologyCreationException, OWLOntologyStorageException{
        File file = new File("C:\\Users\\Rômulo Ferreira\\Desktop\\APi2.owl");
        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        String base = "http://org.semanticweb.restrictionexample";
        OWLOntology ont = man.createOntology(IRI.create(base));
        // Add an axiom to state that all Heads have parts that are noses.
// First we need to obtain references to our hasPart property and our Nose class
OWLDataFactory factory = man.getOWLDataFactory();
OWLObjectProperty hasPart = factory.getOWLObjectProperty(IRI.create(base + "#hasPart"));
OWLClass nose = factory.getOWLClass(IRI.create(base + "#Nose"));
// Now create a restriction to describe the class of individuals that have at least one
// part that is a kind of nose
OWLClassExpression hasPartSomeNose =
factory.getOWLObjectSomeValuesFrom(hasPart, nose);
// Obtain a reference to the Head class so that we can specify that Heads have noses
OWLClass head = factory.getOWLClass(IRI.create(base + "#Head"));
// We now want to state that Head is a subclass of hasPart some Nose
OWLSubClassOfAxiom ax = factory.getOWLSubClassOfAxiom(head, hasPartSomeNose);
// Add the axiom to our ontology
AddAxiom addAx = new AddAxiom(ont, ax);
man.applyChange(addAx);
                man.saveOntology(ont, IRI.create(file.toURI()));
		man.saveOntology(ont, new SystemOutDocumentTarget());
    }


    private void assertNotNull(OWLOntology o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
