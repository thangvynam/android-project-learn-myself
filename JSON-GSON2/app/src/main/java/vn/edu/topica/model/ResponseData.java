package vn.edu.topica.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 8/11/2017.
 */

public class ResponseData {
    private ArrayList<Result> results;
    public List<Result> getResults() {
        return results;
    }
    public void setResults(List<Result> results) {
        this.results = (ArrayList<Result>) results;
    }
}
