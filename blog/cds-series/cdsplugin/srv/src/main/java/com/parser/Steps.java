package com.parser;

import com.parser.Step;

import java.util.List;

class Steps {
    List<Step> steps;

    public Steps() {
    }

    public Step last(){
        return steps.get(steps.size()-1);
    }

    public List<Step> toList() {
        return steps;
    }
}