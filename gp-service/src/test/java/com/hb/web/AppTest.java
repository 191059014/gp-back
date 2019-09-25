package com.hb.web;

import static org.junit.Assert.assertTrue;

import com.hb.facade.entity.HotNewsDO;
import com.hb.unic.base.tool.DbTools;
import com.hb.unic.util.util.RandomUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static void main(String[] args) {
        String sql = DbTools.generateTable(HotNewsDO.class, null);
        System.out.println(sql);
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testRandom() {
        Set<Integer> set = new HashSet<>();
        while (set.size() < 6) {
            set.add(RandomUtils.getRandomBetween(1, 16));
        }
        System.out.println(set);
    }
}
