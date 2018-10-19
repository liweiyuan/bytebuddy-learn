package com.learn.bytebuddy.agent;

import com.learn.bytebuddy.interceptor.SampleConstructorInterceptor;
import com.learn.bytebuddy.interceptor.SampleConstructorStringInterceptor;
import com.learn.bytebuddy.interceptor.SampleMethodInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

/**
 * @Author :lwy
 * @Date : 2018/10/19 12:54
 * @Description :
 */
public class Agent {

    public static void premain(String arguments, Instrumentation instrumentation) {

        AgentBuilder agentBuilder = new AgentBuilder.Default();
        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule module) {
                String canonicalName = typeDescription.getCanonicalName();

                System.out.println("the className : " + canonicalName);

                //构造器嵌入
                builder = builder.constructor(ElementMatchers.takesArguments(0))//默认无参数的构造器
                        .intercept(SuperMethodCall.INSTANCE.andThen(MethodDelegation.to(new SampleConstructorInterceptor())));

                //有参构造器
                builder = builder.constructor(ElementMatchers.takesArguments(String.class))
                        .intercept(SuperMethodCall.INSTANCE.andThen(MethodDelegation.to(new SampleConstructorStringInterceptor())));

                //拦截某方法
                builder = builder.method(ElementMatchers.<MethodDescription>named("sayHello"))
                        .intercept(MethodDelegation.to(new SampleMethodInterceptor()));
                return builder;
            }
        };

        //匹配net.beeapm.bytebuddy.hello.sample包的里的类
        agentBuilder = agentBuilder.type(ElementMatchers.nameStartsWith("com.learn.bytebuddy.sample")).transform(transformer);

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {

            @Override
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {

                //修改后的类输出
                WeavingClassLog.INSTANCE.log(typeDescription, dynamicType);
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }
        };

        agentBuilder.with(listener).installOn(instrumentation);

    }
}
