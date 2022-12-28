package base;

import meta.Page;
import meta.PageElement;
import pages.AllPages;

import java.util.Arrays;
import java.util.HashSet;

import static java.lang.String.format;
import static org.junit.Assert.*;

public class DuplicateChecker {
    public static void checkPagesAndElementsDuplicates() {
        HashSet<Object> pagesSet = new HashSet<>();
        Class<?> clazz = AllPages.class;
        Arrays.asList(clazz.getDeclaredFields()).forEach(page->{
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
        HashSet<Object> fieldsSet = new HashSet<>();
        HashSet<Boolean> isLoadedTypeSet = new HashSet<>();
        Class<? extends BasePage> clazz = page.getClass();
        Arrays.asList(clazz.getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            fieldsSet.add(field.getAnnotation(PageElement.class).value());
            isLoadedTypeSet.add(field.getAnnotation(PageElement.class).isLoaded());
        });
        assertFalse(format("На странице [%s] не описан ни один элемент", clazz.getName()), fieldsSet.isEmpty());
        assertEquals(format("На странице [%s] нет элемента с идентификатором загрузки [isLoaded = true]", clazz.getName()), 2, isLoadedTypeSet.size());
        assertEquals(format("На странице [%s] присутствуют аннотации дубликаты", clazz.getName()), fieldsSet.size(), clazz.getDeclaredFields().length);
    }
}
