package com.learn.bytebuddy.agent;

import com.learn.bytebuddy.interceptor.ConstructorInterceptor;
import com.learn.bytebuddy.interceptor.StaticMethodInterceptor;
import com.learn.bytebuddy.interceptor.TwoArgumentsMethodInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

/**
 * @Author :lwy
 * @Date : 2018/10/19 14:28
 * @Description :
 */
public class Agent {


    public static void premain(String arguments, Instrumentation instrumentation) {


        //
        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {

                String className = typeDescription.getCanonicalName();
                System.out.println("the className: " + className);

                //方法参数长度为1的任何方法
                //builder=builder.method(ElementMatchers.named("setName").and(ElementMatchers.takesArguments(1)))
                //       .intercept(Advice.to(StaticMethodInterceptor.class));

                //获取属性值
                //builder=builder.method(ElementMatchers.takesArguments(2))
                //       .intercept(Advice.to(TwoArgumentsMethodInterceptor.class));

                //拦截构造方法，不可获取非static的属性
                builder = builder.visit(Advice.to(ConstructorInterceptor.class).on(ElementMatchers.isConstructor()));
                return builder;
            }
        };

        new AgentBuilder.Default()
                .with(new AgentListener())
                //错误输出
                //.with(AgentBuilder.Listener.StreamWriting.toSystemError())
                .ignore(ElementMatchers.<TypeDescription>none().and(ElementMatchers.<TypeDescription>nameStartsWith("net.bytebuddy")))
                .type(ElementMatchers.<TypeDescription>nameStartsWith("com.learn.bytebuddy.sample"))
                .transform(transformer)
                .installOn(instrumentation);
    }

    static class AgentListener implements AgentBuilder.Listener {

        @Override
        public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

        }

        @Override
        public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
            //修改后的类输出
            WeavingClassLog.INSTANCE.log(typeDescription, dynamicType);
        }

        @Override
        public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {

        }

        @Override
        public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {
            throwable.printStackTrace();
        }

        @Override
        public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

        }
    }
}
