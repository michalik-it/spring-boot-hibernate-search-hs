package netgloo.search;

import netgloo.models.User;
import netgloo.parser.CustomQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class SearchService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @PersistenceContext
    private EntityManager entityManager;
  
  
    public List<User> search(String query) throws ParseException {

        Query luceneQuery = this.parseToLuceneQuery(query);
        List<User> results = this.search(luceneQuery);
        return results;
    }
    
    public List<User> search(Query luceneQuery) throws ParseException {
        
        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
        
        javax.persistence.Query fullTextQuery =
                fullTextEntityManager.createFullTextQuery(luceneQuery);
  
        @SuppressWarnings("unchecked")
        List<User> results = fullTextQuery.getResultList();
        
        logger.info("\n\nQUERY: " + luceneQuery.toString() + "\nRESULTS(cnt:" + results.size() + "): " + results.toString() + "\n");
        
        return results;
    }

    private Query parseToLuceneQuery(String query) throws ParseException {
        
        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
        
        SearchFactory searchFactory = fullTextEntityManager.getSearchFactory();
        Query luceneQuery = null;
        CustomQueryParser parser =
                new CustomQueryParser(Version.LUCENE_36,"name", searchFactory.getAnalyzer(User.class));

        luceneQuery = parser.parse(query);
        return luceneQuery;
    }
    
    public FullTextEntityManager getFullTextEntityManager() {
        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
        return fullTextEntityManager;
    }

} 
