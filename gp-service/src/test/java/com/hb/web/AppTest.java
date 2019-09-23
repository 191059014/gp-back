package com.hb.web;

import static org.junit.Assert.assertTrue;

import com.hb.facade.entity.HotNewsDO;
import com.hb.unic.base.tool.DbTools;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    public static void main(String[] args) {
        String sql = DbTools.generateTable(HotNewsDO.class, null);
        System.out.println(sql);
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
