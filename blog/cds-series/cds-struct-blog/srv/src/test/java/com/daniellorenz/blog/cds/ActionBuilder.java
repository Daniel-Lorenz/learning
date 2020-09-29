package com.daniellorenz.blog.cds;

import java.util.List;

public class ActionBuilder {

    InitialAction<?> initialAction;

    List<IntermediateAction<?,?>> intermediateActions;

    TerminalAction<?> terminalAction;

    ActionBuilder startWith(InitialAction<?> initialAction){
        this.initialAction = initialAction;
        return this;
    }

    ActionBuilder continueWith(IntermediateAction<?,?> intermediateAction){
        intermediateActions.add(intermediateAction);
        return this;
    }

    ActionBuilder terminateWith(TerminalAction<?> terminalAction){
        this.terminalAction = terminalAction;
        return this;
    }

    MultiActionExecutor build(){
        
    }

}
