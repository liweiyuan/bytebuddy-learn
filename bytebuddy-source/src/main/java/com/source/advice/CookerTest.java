package com.source.advice;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class CookerTest {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        new ByteBuddy()
                .redefine(Cooker.class)
                .visit(Advice.withCustomMapping()
                        .bind(TestAnnotation.class, "demo")
                        .to(AdviceLogic.class).on(ElementMatchers.not(ElementMatchers.isConstructor())))
                .make()
                .saveIn(new File("./ttt.class"));
                //.load(ClassLoadingStrategy.BOOTSTRAP_LOADER, ClassLoadingStrategy.Default.WRAPPER)
                //.getLoaded();


        //Object Cooker = cookerType.getDeclaredConstructor().newInstance();
        //cookerType.getDeclaredMethod("hello").invoke(Cooker);
        //cookerType.getDeclaredMethod("taste", String.class).invoke(null, "pototo");
        //cookerType.getMethod("makeFood", String.class, int.class, Double[].class).invoke(Cooker, "pototo", 1, new Double[]{1.0, 2.0});

        //cookerType.getDeclaredMethod("getInstance").invoke(Cooker);
    }
}
