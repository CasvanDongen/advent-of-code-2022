package nl.casvandongen.adventofcode.utils;

import com.google.common.collect.Lists;
import nl.casvandongen.adventofcode.type.Location;
import nl.casvandongen.adventofcode.type.Location3D;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Input
{
    private static final String DEFAULT_LINE_DELIMITER = "\r\n";

    public static String readAsString(String name)
    {
        try
        {
            return Files.readString(Path.of("src/main/resources/input", name), Charset.defaultCharset());
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }
    }

    public static Stream<String> readString(String name)
    {
        return readString(name, DEFAULT_LINE_DELIMITER);
    }

    public static Stream<String> readString(String name, String lineDelimiter)
    {
        return Arrays.stream(readAsString(name).split(lineDelimiter));
    }

    public static Stream<List<String>> readStringGrouped(String name, int size)
    {
        return Lists.partition(Arrays.asList(readAsString(name).split(DEFAULT_LINE_DELIMITER)), size).stream();
    }

    public static Stream<Long> readNumber(String name)
    {
        return readNumber(name, DEFAULT_LINE_DELIMITER);
    }

    public static Stream<Long> readNumber(String name, String lineDelimiter)
    {
        return readString(name, lineDelimiter).mapToLong(Long::parseLong).boxed();
    }

    public static Stream<Location> readLocation(String name, String delimiter)
    {
        return readLocation(name, DEFAULT_LINE_DELIMITER, delimiter);
    }

    public static Stream<Location> readLocation(String name, String lineDelimiter, String delimiter)
    {
        return readString(name, lineDelimiter).map(line -> {
            String[] v = line.split(delimiter);
            return new Location(Long.parseLong(v[0]), Long.parseLong(v[1]));
        });
    }

    public static Stream<Location3D> readLocation3D(String name, String delimiter)
    {
        return readLocation3D(name, DEFAULT_LINE_DELIMITER, delimiter);
    }

    public static Stream<Location3D> readLocation3D(String name, String lineDelimiter, String delimiter)
    {
        return readString(name, lineDelimiter).map(line -> {
            String[] v = line.split(delimiter);
            return new Location3D(Long.parseLong(v[0]), Long.parseLong(v[1]), Long.parseLong(v[2]));
        });
    }

}