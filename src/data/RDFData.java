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
	public static String MY_NAMESPACE = "http://localhost:8080/semwebworkshop/person/";
	public String localName = "Magnus_Stuhr";

	public void generateFOAFFile() throws FileNotFoundException {

		Resource myResource = ResourceFactory.createResource(MY_NAMESPACE + localName);
		Resource myFriendResource1 = ResourceFactory.createResource(MY_NAMESPACE + "Ola_Nordmann");
		Resource myFriendResource2 = ResourceFactory.createResource(MY_NAMESPACE + "Per_Spelleman");
		Resource homePageResource = ResourceFactory.createResource("http://www.computas.com");

		Literal myName = ResourceFactory.createPlainLiteral("Magnus Stuhr");

		Statement foafName = ResourceFactory.createStatement(myResource, FOAF.name, myName);
		Statement foafHomepage = ResourceFactory.createStatement(myResource, FOAF.homepage, homePageResource);
		Statement rdfType = ResourceFactory.createStatement(myResource, RDF.type, FOAF.Person);
		Statement foafKnows = ResourceFactory.createStatement(myResource, FOAF.knows, myFriendResource1);
		Statement foafKnows2 = ResourceFactory.createStatement(myResource, FOAF.knows, myFriendResource2);

		model.add(foafName);
		model.add(foafHomepage);
		model.add(rdfType);
		model.add(foafKnows);
		model.add(foafKnows2);
		
		model.write(new FileOutputStream(new File("rdf_data/"+localName+".ttl")), "Turtle");
	}
	
	public Model getFoafModel() {
		return model;
	}
}