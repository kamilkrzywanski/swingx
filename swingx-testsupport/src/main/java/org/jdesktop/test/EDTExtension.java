package org.jdesktop.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.swing.SwingUtilities;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;

/**
 * Runs each test on the Swing event dispatch thread.
 */
public class EDTExtension implements InvocationInterceptor {

    @Override
    public <T> T interceptTestClassConstructor(Invocation<T> invocation,
            ReflectiveInvocationContext<Constructor<T>> invocationContext,
            ExtensionContext extensionContext) throws Throwable {
        return onEdt(invocation);
    }

    @Override
    public void interceptBeforeAllMethod(Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext) throws Throwable {
        onEdt(invocation);
    }

    @Override
    public void interceptBeforeEachMethod(Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext) throws Throwable {
        onEdt(invocation);
    }

    @Override
    public void interceptTestMethod(Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext) throws Throwable {
        onEdt(invocation);
    }

    @Override
    public void interceptTestTemplateMethod(Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext) throws Throwable {
        onEdt(invocation);
    }

    @Override
    public void interceptAfterEachMethod(Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext) throws Throwable {
        onEdt(invocation);
    }

    @Override
    public void interceptAfterAllMethod(Invocation<Void> invocation,
            ReflectiveInvocationContext<Method> invocationContext,
            ExtensionContext extensionContext) throws Throwable {
        onEdt(invocation);
    }

    private static <T> T onEdt(Invocation<T> invocation) throws Throwable {
        if (SwingUtilities.isEventDispatchThread()) {
            return invocation.proceed();
        }
        Object[] value = new Object[1];
        Throwable[] thrown = new Throwable[1];
        SwingUtilities.invokeAndWait((Runnable) () -> {
            try {
                value[0] = invocation.proceed();
            } catch (Throwable t) {
                thrown[0] = t;
            }
        });
        if (thrown[0] != null) {
            throw thrown[0];
        }
        @SuppressWarnings("unchecked")
        T result = (T) value[0];
        return result;
    }
}
