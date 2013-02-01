package control;

import java.io.FileNotFoundException;
import java.util.HashSet;

import com.hp.hpl.jena.sparql.vocabulary.FOAF;

import data.QueryData;
import data.QueryResultData;
import data.RDFData;

public class ActionController {
	
	public ActionController() throws FileNotFoundException {
		
		RDFData rdfData = new RDFData();
		QueryResultData queryResultData = new QueryResultData();
		
		rdfData.generateFOAFFile();
		queryResultData.runQuery(QueryData.createQueryRequest(QueryData.prefixes, QueryData.currentQuery), rdfData.getFoafModel());

		HashSet<String[]> tripleSet = new HashSet<String[]>();
		tripleSet.add(rdfData.createRDFTriple(RDFData.MY_NAMESPACE+RDFData.localName, FOAF.name.getURI(), null));
		tripleSet.add(rdfData.createRDFTriple(RDFData.MY_NAMESPACE+RDFData.localName, FOAF.homepage.getURI(), null));
		
		queryResultData.findTripleResourcesFromModel(rdfData.getFoafModel(), tripleSet);
	}
}