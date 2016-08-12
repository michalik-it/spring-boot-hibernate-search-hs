package netgloo.controllers;

import netgloo.models.User;
import netgloo.search.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryParser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private SearchService userSearch;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/search")
    public String search(String q, Model model) throws ParseException {
        List<User> searchResults = new ArrayList<User>();
        if (StringUtils.isNotBlank(q))
            searchResults = userSearch.search(q);
        model.addAttribute("searchResults", searchResults);
        return "search";
    }


}
