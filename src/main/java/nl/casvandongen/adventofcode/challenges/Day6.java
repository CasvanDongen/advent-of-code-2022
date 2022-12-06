package nl.casvandongen.adventofcode.challenges;


import lombok.extern.slf4j.Slf4j;
import nl.casvandongen.adventofcode.annotations.DailyChallenge;
import nl.casvandongen.adventofcode.utils.Input;

@Slf4j
@DailyChallenge(day = 6)
public class Day6 implements Challenge
{
    @Override
    public void part1()
    {
        log.info("How many characters need to be processed before the first start-of-packet marker is detected? {}", marker(input(), 4));
    }

    @Override
    public void part2()
    {
        log.info("How many characters need to be processed before the first start-of-message marker is detected? {}", marker(input(), 14));
    }

    public String input()
    {
        return Input.readAsString("day06_1.txt");
    }

    private int marker(String input, int length)
    {
        for (int i = 0; i < input.chars().count(); i++)
        {
            if (input.substring(i, i + length).chars().distinct().count() == length)
            {
                return length + i;
            }
        }

        return -1;
    }
}
