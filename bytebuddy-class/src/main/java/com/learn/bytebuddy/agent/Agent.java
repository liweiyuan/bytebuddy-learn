package com.learn.bytebuddy.agent;

import com.learn.bytebuddy.interceptor.SampleMethodInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

/**
 * @Author :lwy
 * @Date : 2018/10/19 10:46
 * @Description :
 */
public class Agent {

    public static void premain(String arguments, Instrumentation instrumentation) {
        System.out.println("\n---------------------------------this is an bytebuddy sample ---------------------------------------");

        AgentBuilder agentBuilder = new AgentBuilder.Default();
        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder,
                                                    TypeDescription typeDescription,
                                                    ClassLoader classLoader,
                                                    JavaModule module) {
                String className = typeDescription.getCanonicalName();
                System.out.println("++++++++ class name = " + className);

                //匹配任意方法
                builder = builder.method(ElementMatchers.any())
                        .intercept(MethodDelegation.to(new SampleMethodInterceptor()));
                return builder;
            }
        };
        //添加需要扫描的包
        agentBuilder = agentBuilder.type(ElementMatchers.<TypeDescription>nameStartsWith("com.learn.bytebuddy.sample")).transform(transformer);

        //add 监听器
        AgentBuilder.Listener listener=new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
                //System.err.println("listener Discovery");
            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
                //System.err.println("listener Transformation");
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {
                //System.err.println("listener Ignored");
            }

            @Override
            public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {
                //System.err.println("listener Error");
            }

            @Override
            public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
                //System.err.println("listener Complete");
            }
        };

        agentBuilder.with(listener).installOn(instrumentation);

    }
}
