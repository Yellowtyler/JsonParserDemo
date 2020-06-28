package com.vtb.jsonparser.core.util;

import com.vtb.jsonparser.core.entities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

public class Integrator {

    private static final Logger logger = LogManager.getLogger(JsonConverter.class);

    public Integrator() {
    }

    public Object integrate(Object obj) {
        Object oj = null;
        if (obj.getClass().getSimpleName().equalsIgnoreCase("Root")) {
            ArrayList<Object> arrayList = new ArrayList<>();
            arrayList.add(integrateRoot(obj, null, arrayList));
        }
        return integrateRoot(obj, null, null);
    }

    private Object integrateRoot(Object obj, Object newObj, ArrayList<Object> arrayList) {
        for (Field other : obj.getClass().getDeclaredFields()) {
            other.setAccessible(true);
            Object o;
            try {
                o = other.get(obj);
                if (o instanceof Collection) {
                    for (Object ob : ((Collection) o).toArray()) {
                        integrateRoot(ob, null, arrayList);
                    }
                }
                if (newObj == null) newObj = createClass(obj.getClass().getSimpleName());
                Class newClass = newObj.getClass();
                String[] str = other.getName().split("\\s.");
                Field field = newClass.getDeclaredField(str[0]);
                field.setAccessible(true);
                field.set(newObj, o);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                logger.warn(e.getMessage());
            }
        }
        return newObj;
    }

    private Object createClass(String nameClass) {
        if (nameClass.equalsIgnoreCase("Teams"))
            return new Teams();
        if (nameClass.equalsIgnoreCase("Team"))
            return new Team();
        if (nameClass.equalsIgnoreCase("Student"))
            return new Student();
        if (nameClass.equalsIgnoreCase("Task"))
            return new Task();
        if (nameClass.equalsIgnoreCase("Label"))
            return new Label();
        return null;
    }
}
