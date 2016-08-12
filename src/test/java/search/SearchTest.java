package search;

import static org.junit.Assert.assertTrue;

import netgloo.Application;
import netgloo.models.User;
import netgloo.search.SearchService;
import org.apache.lucene.queryParser.ParseException;
// import org.apache.lucene.queryparser.classic.ParseException; //hs5
// import org.apache.lucene.queryparser.classic.QueryParser; //hs5
import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles(profiles="test")
@Transactional
public class SearchTest {


    @Autowired
    private SearchService searchService;

    @Test
    public void testStringTerm() throws ParseException {

        String text = "name:Amy";

        List<User> results = searchService.search(text);
        assertTrue(results.size() > 0);

    }
    
    //NUMERIC
    @Test
    public void testIntegerTerm() throws ParseException {

        String text = "yeari:1999";

        List<User> results = searchService.search(text);
        assertTrue(results.size() > 0);

    }

    @Test
    public void testIntegerRange() throws ParseException {

        String text = "yeari:[2 TO 1999]";

        List<User> results = searchService.search(text);
        assertTrue(results.size() > 0);

    }

    @Test
    public void testComplexQuery() throws ParseException {

        String text = "yeari:[3 TO 1999] AND name:AMY";

        List<User> results = searchService.search(text);
        assertTrue(results.size() > 0);

    }
    
    //DATES
    @Test
    public void testDateTerm() throws ParseException {

        //birthDay is using MINUTE resolution
        String text = "birthDay:201608091002";

        List<User> results = searchService.search(text);
        assertTrue(results.size() > 0);

    }
    
    @Test
    public void testDateRange() throws ParseException {

        //birthDay is using MINUTE resolution
        String text = "birthDay:[201608091002 TO 20160810]";
        List<User> results = searchService.search(text);
        assertTrue(results.size() > 0);

    }
    
    @Test
    public void testOrQuery() throws ParseException {

        //birthDay is using MINUTE resolution
        String text = "name:Henderson OR name:Clark";
        List<User> results = searchService.search(text);
        assertTrue(results.size() == 2);

    }
    
    @Test
    public void testOrAndQuery() throws ParseException {

        //birthDay is using MINUTE resolution
        String text = "name:Henderson OR name:Clark AND email:aclark@yahoo.com";
        List<User> results = searchService.search(text);
        assertTrue(results.size() == 1);

    }
    
    @Test
    public void testOrAndWithBracketsQuery() throws ParseException {

        //birthDay is using MINUTE resolution
        String text = "name:Henderson OR (name:Clark AND email:aclark@yahoo.com)";
        List<User> results = searchService.search(text);
        assertTrue(results.size() == 2);

    }

    @Test
    public void testIntegerTermInRelatedEntity() throws ParseException {

        String text = "userProject.projects.yearb:2999";

        List<User> results = searchService.search(text);
        assertTrue(results.size() > 0);
    }

    @Test
    public void testIntegerRengeInRelatedEntity() throws ParseException {

        String text = "userProject.projects.yearb:[2998 TO 2999]";

        List<User> results = searchService.search(text);
        assertTrue(results.size() > 0);
    }
    
    @Test
    public void testAndTermInRelatedEntities() throws ParseException {

        String text = "userProject.projects.yearb:2999 AND group.name:spec";

        List<User> results = searchService.search(text);
        assertTrue(results.size() == 1);
    }
    
    @Test
    public void testOrTermInRelatedEntities() throws ParseException {

        String text = "userProject.projects.name:Projektten OR group.name:spec";

        List<User> results = searchService.search(text);
        assertTrue(results.size() == 4);
    }


    //using QueryBuilder
    @Test
    public void testIntegerSearchHibernateQuery() throws ParseException {

        QueryBuilder b = searchService.getFullTextEntityManager().getSearchFactory()
                .buildQueryBuilder().forEntity(User.class).get();

        Query luceneQuery =
                b.range().onField("yeari").from(2).to(2000).excludeLimit().createQuery();

        List<User> results = searchService.search(luceneQuery);
        assertTrue(results.size() > 0);

    }
  
    @Test
    public void testDateSearchHibernateQuery() throws ParseException {

        QueryBuilder b = searchService.getFullTextEntityManager().getSearchFactory().buildQueryBuilder()
                .forEntity(User.class).get();


        Calendar calFrom = Calendar.getInstance();
        calFrom.set(2016, 0, 1);

        Calendar calTo = Calendar.getInstance();
        calTo.set(Calendar.YEAR, 2016);
        calTo.set(Calendar.MONTH, 7);
        calTo.set(Calendar.DAY_OF_MONTH, 9);
        calTo.set(Calendar.HOUR_OF_DAY, 10);
        calTo.set(Calendar.MINUTE, 2);

        // String dateStr = DateTools.dateToString(calTo.getTime(), DateTools.Resolution.MINUTE); //UTC, GMT !!!

        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmm");
        String dateStr = sdf.format(calTo.getTime());

        Query luceneQuery =
                b.keyword().onField("birthDay").ignoreFieldBridge().matching(dateStr).createQuery();

        List<User> results = searchService.search(luceneQuery);
        
        assertTrue(results.size() > 0);

    }
    
    @Test
    public void testDateSearchHibernateRangeQuery() throws ParseException {

        QueryBuilder b = searchService.getFullTextEntityManager().getSearchFactory().buildQueryBuilder()
                .forEntity(User.class).get();


        Calendar calFrom = Calendar.getInstance();
        calFrom.set(2016, 0, 1);

        Calendar calTo = Calendar.getInstance();
        calTo.set(Calendar.YEAR, 2016);
        calTo.set(Calendar.MONTH, 7);
        calTo.set(Calendar.DAY_OF_MONTH, 9);
        calTo.set(Calendar.HOUR_OF_DAY, 10);
        calTo.set(Calendar.MINUTE, 3);

        // String dateStr = DateTools.dateToString(calTo.getTime(), DateTools.Resolution.MINUTE); //UTC, GMT !!!

        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmm");
        String dateToStr = sdf.format(calTo.getTime());
        String dateFromStr = sdf.format(calFrom.getTime());

        Query luceneQuery = b.range().onField("birthDay").ignoreFieldBridge()
                .from(dateFromStr)
                .to(dateToStr)
                .excludeLimit().createQuery();
       
        List<User> results = searchService.search(luceneQuery);

        assertTrue(results.size() > 0);

    }



}
