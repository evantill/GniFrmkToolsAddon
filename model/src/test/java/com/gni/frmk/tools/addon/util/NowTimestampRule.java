package com.gni.frmk.tools.addon.util;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.rules.ExternalResource;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Date: 06/04/11
 * Time: 14:06
 *
 * @author: e03229
 */
public class NowTimestampRule extends ExternalResource {

    private static final String NOW_DHMS = "2010-05-03T21:01:59Z";

    private final DateTimeFormatter parser = ISODateTimeFormat.dateTimeNoMillis();
    private final String nowAsString;
    private final Date now;

    public NowTimestampRule() {
        this(NOW_DHMS);
    }

    protected NowTimestampRule(String nowAsString) {
        this.nowAsString = nowAsString;
        now = parser.parseDateTime(nowAsString).toDate();
    }

    protected NowTimestampRule(Date now) {
        this.now = now;
        nowAsString = parser.print(now.getTime());
    }

    public Date now() {
        return now;
    }
}
