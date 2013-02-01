package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.RDF;

public class RDFData {

	public Model model = ModelFactory.createDefaultModel();
	public static String MY_NAMESPACE = "http://localhost:8080/semwebworkshop/person/"; // Ikke endre denne
	public static String localName = "Magnus_Stuhr"; //Navnet ditt med underscore

	public void generateFOAFFile() throws FileNotFoundException {

		Resource myResource = ResourceFactory.createResource(MY_NAMESPACE + localName); //Ikke endre denne
		Resource myFriendResource1 = ResourceFactory.createResource(MY_NAMESPACE + "Superman"); //Endre vennen din sitt navn i fnuttene.
		Resource myFriendResource2 = ResourceFactory.createResource(MY_NAMESPACE + "Batman"); //Endre vennen din sitt navn i fnuttene.
		Resource homePageResource = ResourceFactory.createResource("http://www.computas.com"); //Endre hjemmesiden din
		Resource birthPlaceResource = ResourceFactory.createResource("http://dbpedia.org/resource/Bergen"); //Endre fødebyen din

		Literal myName = ResourceFactory.createPlainLiteral("Magnus Stuhr"); //Endre navnet ditt
		Literal email = ResourceFactory.createPlainLiteral("Magnus.Stuhr@computas.com"); //Endre e-mailen din
		
		Property birthPlacePredicate = ResourceFactory.createProperty("http://dbpedia.org/ontology/birthPlace"); //Ikke endre denne

		Statement foafName = ResourceFactory.createStatement(myResource, FOAF.name, myName);
		Statement foafHomepage = ResourceFactory.createStatement(myResource, FOAF.homepage, homePageResource);
		Statement foafMbox = ResourceFactory.createStatement(myResource, FOAF.mbox, email);
		Statement rdfType = ResourceFactory.createStatement(myResource, RDF.type, FOAF.Person);
		Statement foafKnows = ResourceFactory.createStatement(myResource, FOAF.knows, myFriendResource1);
		Statement foafKnows2 = ResourceFactory.createStatement(myResource, FOAF.knows, myFriendResource2);
		Statement birthPlace = ResourceFactory.createStatement(myResource, birthPlacePredicate, birthPlaceResource);

		model.add(foafName);
		model.add(foafHomepage);
		model.add(foafMbox);
		model.add(rdfType);
		model.add(foafKnows);
		model.add(foafKnows2);
		model.add(birthPlace);
		
		model.write(new FileOutputStream(new File("/home/cx/semantic_web_workshop/semweb-workshop/rdf_data/"+localName+".ttl")), "Turtle");
	}
	
	public Model getFoafModel() {
		return model;
	}
	
	public String[] createRDFTriple(String subject, String predicate, String object) {
		String[] triple = new String[3];
		triple[0] = subject;
		triple[1] = predicate;
		triple[2] = object;
		return triple;
	}
}