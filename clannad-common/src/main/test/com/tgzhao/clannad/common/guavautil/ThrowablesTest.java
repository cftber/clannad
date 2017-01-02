package com.tgzhao.clannad.common.guavautil;

import com.google.common.base.Throwables;
import org.apache.http.auth.AuthenticationException;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by tgzhao on 17/1/2.
 */
public class ThrowablesTest {

    @Test
    public void testThrowables(){
        try {
            int a = 0;
            int b = 10;
            int c = b/a;
            throw new Exception();
        } catch (Throwable t) {
            System.out.println(Throwables.getRootCause(t));
            String ss = Throwables.getStackTraceAsString(t);
            System.out.println("ss:"+ss);
            Throwables.propagate(t);
        }
    }

    @Test
    public void call() throws IOException {
        try {
            throw new AuthenticationException(); // IOException();
            //throw new IOException();
        } catch (Throwable t) {
            Throwables.propagateIfInstanceOf(t, IOException.class);
            throw  Throwables.propagate(t);
        }
    }
}
