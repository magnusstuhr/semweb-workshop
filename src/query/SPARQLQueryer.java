package query;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;

public class SPARQLQueryer {
	
	public ResultSet runSelectQuery(Query query, Model model) {
		QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
		ResultSet resultSet = qexec.execSelect();
		return resultSet;
	}
	
	public Model runDescribeQuery(Query query, Model model) {
		QueryExecution qexec = QueryExecutionFactory.create(query, model) ;
		Model resultModel = qexec.execDescribe();
		return resultModel;
	}
}

