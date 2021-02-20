package com.source.description;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;

import java.lang.reflect.Method;

public class TypeVariableSourceTest {
    public <T extends Food> T makeFood(T food) {

        return food;
    }

    public Object makeSauce() {

        class Sauce {
            public void print() {
            }
        }

        return new Sauce();
    }

    // 食物
    public static class Food {

    }

    public static void main(String[] args) throws Exception {


        Method makeFood = TypeVariableSourceTest.class.getDeclaredMethod("makeFood", Food.class);


        MethodDescription.ForLoadedMethod makeFoodBD = new MethodDescription.ForLoadedMethod(makeFood);
        System.out.println("Enclosing 外围的包裹类" + makeFoodBD.getEnclosingSource().toString());
        TypeDescription.ForLoadedType sauceBD = new TypeDescription.ForLoadedType(new TypeVariableSourceTest().makeSauce().getClass());
        System.out.println("Enclosing 外围的包裹方法" + sauceBD.getEnclosingSource());


    }

}
