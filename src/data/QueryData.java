package data;

public class QueryData {

	public static String prefixes = "" +
			"prefix foaf: <http://xmlns.com/foaf/0.1/> " +
			"prefix : <"+RDFData.MY_NAMESPACE+">";

	public static String queryName = "SELECT ?name WHERE { :Magnus_Stuhr foaf:name ?name }";
	public static String queryHomepage = "SELECT ?name WHERE { :Magnus_Stuhr foaf:homepage ?homepage }";
	public static String queryNameAndHomepage = "SELECT ?name ?homepage WHERE { :Magnus_Stuhr foaf:name ?name; foaf:homepage ?homepage.}";
	public static String queryFriend = "SELECT ?myFriend WHERE { :Magnus_Stuhr foaf:knows ?myFriend }";
	
	public static String describeMyResourceQuery = "DESCRIBE :Magnus_Stuhr";
	
	public static String currentQuery = queryFriend;
	
	public static StringBuffer createQueryRequest(String prefixes, String queryString) {
		StringBuffer queryRequest = new StringBuffer();
		queryRequest.append(prefixes);
		queryRequest.append(queryString);
		return queryRequest;
	}
}