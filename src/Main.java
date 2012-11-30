import java.io.FileNotFoundException;


import org.apache.log4j.BasicConfigurator;

import data.QueryData;
import data.QueryResultData;
import data.RDFData;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		BasicConfigurator.configure();
//		Logger.getLogger("org.apache").setLevel(Level.INFO);
		
		RDFData rdfGenerator = new RDFData();
		QueryResultData queryResultHandler = new QueryResultData();
		
		rdfGenerator.generateFOAFFile();
		queryResultHandler.runQuery(QueryData.createQueryRequest(QueryData.prefixes, QueryData.currentQuery), rdfGenerator.getFoafModel());
	}
}