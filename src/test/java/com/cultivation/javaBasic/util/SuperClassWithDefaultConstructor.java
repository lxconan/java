package com.cultivation.javaBasic.util;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class SuperClassWithDefaultConstructor {
    private final List<String> logs = new ArrayList<>();

    void addLog(String log) { logs.add(log); }

    public String[] getLogs() { return logs.toArray(new String[0]); }

    public SuperClassWithDefaultConstructor() { addLog("SuperClassWithDefaultConstructor.constructor()"); }
    public SuperClassWithDefaultConstructor(String name) {
        addLog("SuperClassWithDefaultConstructor.constructor(String)");
    }
}

