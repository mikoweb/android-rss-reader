package app.rssreader.application.logic.viewmodel;

import java.util.HashMap;

import app.rssreader.application.logic.viewmodel.exception.NotFoundViewModelException;

public class ViewModelMap {
    private static final HashMap<Class, Object> hashMap = new HashMap<>();

    public static Object get(Class className) throws NotFoundViewModelException {
        if (!has(className)) {
            throw new NotFoundViewModelException("Not found view model: " + className.toString());
        }

        return hashMap.get(className);
    }

    public static void init(Object object) {
        if (!hashMap.containsKey(object.getClass())) {
            hashMap.put(object.getClass(), object);
        }
    }

    public static boolean has(Class className) {
        return hashMap.containsKey(className);
    }
}
