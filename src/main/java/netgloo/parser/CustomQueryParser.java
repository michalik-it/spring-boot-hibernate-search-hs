package netgloo.parser;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomQueryParser extends QueryParser {

    private static String[] INTFIELD_NAMES = {"yeari", "group.yearb", "userProject.projects.yearb"};
    private static List<String> INTFIELD_NAMES_LIST =
            new ArrayList<String>(Arrays.asList(INTFIELD_NAMES));

    public CustomQueryParser(Version v, String f, Analyzer a) {
        super(v, f, a);
    }

    public Query getRangeQuery(String field, String part1, String part2, boolean inclusive)
            throws ParseException {

        if (field.equals("customYearb"))
            field = "userProject.projects.yearb";

        if (INTFIELD_NAMES_LIST.contains(field)) {
            return NumericRangeQuery.newIntRange(field, Integer.parseInt(part1),
                    Integer.parseInt(part2), inclusive, inclusive);
        }
        return (TermRangeQuery) super.newRangeQuery(field, part1, part2, inclusive);
    }

    public Query newTermQuery(Term term) {

        if (term.field().equals("customYearb"))
            term = new Term("userProject.projects.yearb", term.text());

        if (INTFIELD_NAMES_LIST.contains(term.field())) {
            try {
                return this.getRangeQuery(term.field(), term.text(), term.text(), true);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return super.newTermQuery(term);
    }
}
