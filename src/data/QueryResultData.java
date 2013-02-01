package data;

import java.util.HashSet;

import query.SPARQLQueryer;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.FileUtils;

public class QueryResultData {

	private SPARQLQueryer sparqlQueryer;

	public QueryResultData() {
		sparqlQueryer = new SPARQLQueryer();
	}

	public void runQuery(StringBuffer queryRequest, Model model) {

		Query query = QueryFactory.create(queryRequest.toString()) ;

		if (query.isSelectType()) {
			resultsSelectQuery(query, model);
		}
		else if (query.isDescribeType()) {
			runDescribeQuery(query, model);
		}
	}

	public void resultsSelectQuery(Query query, Model model) {

		ResultSet resultSet = sparqlQueryer.runSelectQuery(query, model);

		if (resultSet.hasNext()) {

			while (resultSet.hasNext()) {
				QuerySolution soln = resultSet.nextSolution();

				for (String resultVariable : resultSet.getResultVars()) {
					if (FileUtils.isURI(soln.get(resultVariable).toString())) {
						Resource resource = soln.getResource(resultVariable) ; // Get a result variable - must be a resource
						System.out.println(resultVariable+" : "+resource.getURI());
					}
					else {
						Literal literal = soln.getLiteral(resultVariable);
						System.out.println(resultVariable+" : "+literal.getString());
					}
				}
			}
		}
		else {
			System.out.println("No results");
		}
	}
	public void runDescribeQuery(Query query, Model model) {

		Model resultModel = sparqlQueryer.runDescribeQuery(query, model);
		System.out.println(resultModel);
	}

	public void findTripleResourcesFromModel(Model model, HashSet<String[]> tripleSet) {

		for (String[] triple : tripleSet) {

			String subjectString = triple[0];
			String predicateString = triple[1];
			String objectString = triple[2];

			StmtIterator it = model.listStatements(ResourceFactory.createResource(subjectString), ResourceFactory.createProperty(predicateString), (RDFNode)null);

			while (it.hasNext()) {

				Statement stmt = it.next();

				if (subjectString == null) {
					System.out.println(stmt.getSubject().getURI());
				}
				if (predicateString == null) {
					System.out.println(stmt.getPredicate().getURI());
				}
				if (objectString == null) {
					if (FileUtils.isURI(stmt.getObject().toString())) {
						System.out.println(stmt.getObject().asResource());
					}
					else {
						System.out.println(stmt.getObject().asLiteral());
					}
				}
			}
		}
	} 
}