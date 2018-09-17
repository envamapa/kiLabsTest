package com.mx.envamapa.app.wundertest.domain.manager;

import com.mx.envamapa.app.wundertest.domain.taskInterface.Task;

import javax.inject.Inject;

/**
 * Created by enya on 17/09/18.
 */

public class TaskManager {

    private static TaskManager taskManager;

    @Inject
    public TaskManager(){
    }

    public void execute(Task task, Object object){
        task.execute(object);
    }

}