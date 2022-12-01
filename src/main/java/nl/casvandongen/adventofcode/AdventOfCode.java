package nl.casvandongen.adventofcode;

import io.github.classgraph.*;
import lombok.extern.slf4j.Slf4j;
import nl.casvandongen.adventofcode.annotations.DailyChallenge;
import nl.casvandongen.adventofcode.challenges.Challenge;
import org.apache.commons.cli.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AdventOfCode
{
    private static final String CHALLENGES_PACKAGE = "nl.casvandongen.adventofcode.challenges";

    private static final Map<Integer, Challenge> challenges = new HashMap<>();

    static
    {
        try (ScanResult scanResult = new ClassGraph().enableAnnotationInfo().enableMethodInfo().acceptPackages(CHALLENGES_PACKAGE).scan())
        {
            for (ClassInfo classInfo : scanResult.getClassesWithAnnotation(DailyChallenge.class))
            {
                AnnotationInfo annotationInfo = classInfo.getAnnotationInfo(DailyChallenge.class);
                DailyChallenge annotation = (DailyChallenge) annotationInfo.loadClassAndInstantiate();

                MethodInfoList constructorInfoList = classInfo.getConstructorInfo();
                if (constructorInfoList.size() != 1 || constructorInfoList.get(0).getParameterInfo().length != 0)
                {
                    log.error("Unsupported challenge constructor found [{}].", classInfo.getName());
                    continue;
                }

                try
                {
                    Challenge challenge = (Challenge) AdventOfCode.class.getClassLoader().loadClass(classInfo.getName()).getDeclaredConstructor().newInstance();
                    challenges.put(annotation.day(), challenge);
                } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                         IllegalAccessException | NoSuchMethodException e)
                {
                    log.error("Could not instantiate challenge [{}: {}]", classInfo.getName(), e.getMessage());
                }
            }
        }

    }

    public static void main(String[] args) throws ParseException
    {
        Options opts = new Options();
        opts.addOption("d", "day", true, "Select the day");

        CommandLineParser commandParser = new DefaultParser();
        CommandLine cli = commandParser.parse(opts, args);

        String day = cli.getOptionValue("d", String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)));

        Challenge challenge = getChallenge(Integer.valueOf(day));
        if (challenge == null)
        {
            log.error("Challenge for day {} not found.", day);
            System.exit(1);
        }

        challenge.part1();
        challenge.part2();
    }

    private static Challenge getChallenge(Integer key)
    {
        return challenges.get(key);
    }
}
