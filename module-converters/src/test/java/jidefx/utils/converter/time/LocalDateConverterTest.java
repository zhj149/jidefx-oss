/*
 * Copyright (c) 2002-2015, JIDE Software Inc. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package jidefx.utils.converter.time;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class LocalDateConverterTest {

    private LocalDateConverter _converter;

    @Before
    public void setUp() throws Exception {
        _converter = new LocalDateConverter();
        Locale.setDefault(Locale.US);
    }

    @Test
    public void testFromString() throws Exception {
        Assert.assertEquals(LocalDate.of(2000, 1, 1), _converter.fromString("Jan 1, 2000")); //NON-NLS
        Assert.assertEquals(LocalDate.of(2000, 1, 1), _converter.fromString("1/1/00"));
        Assert.assertEquals(LocalDate.of(2003, 1, 2), _converter.fromString("01/02/03"));
        Assert.assertEquals(LocalDate.of(2000, 1, 1), _converter.fromString("1/1/2000"));
        Assert.assertEquals(LocalDate.of(2000, 1, 1), _converter.fromString("January 1, 2000")); //NON-NLS
        Assert.assertEquals(LocalDate.of(2000, 1, 1), _converter.fromString("Jan 01, 2000")); //NON-NLS
        Assert.assertEquals(LocalDate.of(2000, 1, 1), _converter.fromString("January 01, 2000")); //NON-NLS
        Assert.assertEquals(LocalDate.of(2000, 1, 1), _converter.fromString("2000-01-01"));
        Assert.assertEquals(LocalDate.of(2000, 1, 1), _converter.fromString("00-01-01"));
        Assert.assertEquals(LocalDate.of(2003, 2, 1), _converter.fromString("03-02-01"));
        Assert.assertEquals(LocalDate.of(2000, 1, 1), _converter.fromString("20000101"));
        Assert.assertEquals(LocalDate.of(2000, 1, 1), _converter.fromString("1-Jan-2000")); //NON-NLS
    }

    @Test
    public void testToString() throws Exception {
        Assert.assertEquals("Jan 1, 2000", _converter.toString(LocalDate.of(2000, 1, 1)));

        _converter.setDefaultDateTimeFormatter(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        Assert.assertEquals("Saturday, January 1, 2000", _converter.toString(LocalDate.of(2000, 1, 1)));

        _converter.setDefaultDateTimeFormatter(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        Assert.assertEquals("1/1/00", _converter.toString(LocalDate.of(2000, 1, 1)));

        _converter.setDefaultDateTimeFormatter(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        Assert.assertEquals("January 1, 2000", _converter.toString(LocalDate.of(2000, 1, 1)));

        _converter.setDefaultDateTimeFormatter(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        Assert.assertEquals("Jan 1, 2000", _converter.toString(LocalDate.of(2000, 1, 1)));
    }
}
