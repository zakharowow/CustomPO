package base;

import meta.Page;
import meta.PageElement;
import pages.AllPages;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import static java.lang.String.format;
import static org.junit.Assert.*;

public class DuplicateChecker {
    public static void checkPagesAndElementsDuplicates() {
        HashSet<Object> pagesSet = new HashSet<>();
        Class<?> clazz = AllPages.class;
        Arrays.asList(clazz.getDeclaredFields()).forEach(page -> {
            page.setAccessible(true);
            String annotationValue = page.getAnnotation(Page.class).value();
            try {
                checkPageElementsDuplicates((BasePage) page.get(clazz.newInstance()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            pagesSet.add(annotationValue);
        });
        assertFalse(format("В классе [%s] не описана ни одна страница", clazz.getName()), pagesSet.isEmpty());
        assertEquals(format("В классе [%s] присутствуют аннотации дубликаты", clazz.getName()), pagesSet.size(), clazz.getDeclaredFields().length);
    }

    private static void checkPageElementsDuplicates(BasePage page) {
        Class<? extends BasePage> clazz = page.getClass();
//        Class<?> parent = clazz.getSuperclass();
//        int parentElements = 0;
//        if (!parent.equals(Object.class)) {
//            parentElements = parent.getDeclaredFields().length;
//            Arrays.asList(parent.getDeclaredFields()).forEach(field -> {
//                field.setAccessible(true);
//                fieldsSet.add(field.getAnnotation(PageElement.class).value());
//            });
//        }
//        Arrays.asList(clazz.getDeclaredFields()).forEach(field -> {
//            field.setAccessible(true);
//            fieldsSet.add(field.getAnnotation(PageElement.class).value());
//            isLoadedTypeSet.add(field.getAnnotation(PageElement.class).isLoaded());
//        });
        HashMap<String, Object> mainMap = getPageFieldsSet(clazz);
        HashMap<String, Object> parentMap = getPageFieldsSet(clazz.getSuperclass());

        HashSet<Object> fieldsSet = new HashSet<>((HashSet<?>) mainMap.get("Элементы"));
        HashSet<Object> isLoadedTypeSet = new HashSet<>((HashSet<?>) mainMap.get("isLoaded"));
        fieldsSet.addAll((HashSet<?>) parentMap.get("Элементы"));

        assertFalse(format("На странице [%s] не описан ни один элемент", clazz.getName()), fieldsSet.isEmpty());
        assertTrue(format("На странице [%s] нет элемента с идентификатором загрузки [isLoaded = true]", clazz.getName()), isLoadedTypeSet.contains(true));
        assertEquals(format("На странице [%s] присутствуют аннотации дубликаты", clazz.getName()), fieldsSet.size(),
                clazz.getDeclaredFields().length + (int) parentMap.get("Количество родительских элементов"));
    }

    private static HashMap<String, Object> getPageFieldsSet(Class<?> clazz) {
        HashMap<String, Object> result = new HashMap<>();
        HashSet<Object> fieldsSet = new HashSet<>();
        HashSet<Object> isLoadedTypeSet = new HashSet<>();

        if (!clazz.equals(Object.class)) {
            result.put("Количество родительских элементов", clazz.getDeclaredFields().length);
        }
        Arrays.asList(clazz.getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            fieldsSet.add(field.getAnnotation(PageElement.class).value());
            isLoadedTypeSet.add(field.getAnnotation(PageElement.class).isLoaded());
        });
        result.put("Элементы", fieldsSet);
        result.put("isLoaded", isLoadedTypeSet);
        return result;
    }
}
