package com.nagarro.utils.reporting;

import java.util.HashMap;
import java.util.Map;

public class ScenarioResults {

    int passCount;
    int failCount;
    int warningCount;

    public ScenarioResults(){
        this.passCount = 0;
        this.failCount = 0;
        this.warningCount = 0;
    }

    public Map<Object, Object> getResult(){
        Map<Object, Object> result = new HashMap<>();
        result.put("Passed",this.passCount);
        result.put("Failed",this.failCount);
        result.put("Warning",this.warningCount);
        return result;

    }

}
