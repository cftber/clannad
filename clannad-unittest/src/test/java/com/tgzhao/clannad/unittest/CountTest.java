package com.tgzhao.clannad.unittest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tgzhao on 2016/8/30.
 */
public class CountTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        assertEquals(3, new Count().add(1, 2));
    }
}