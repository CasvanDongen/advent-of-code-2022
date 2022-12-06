package nl.casvandongen.adventofcode.challenges;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import nl.casvandongen.adventofcode.annotations.DailyChallenge;
import nl.casvandongen.adventofcode.type.Pair;
import nl.casvandongen.adventofcode.utils.Input;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@DailyChallenge(day = 5)
public class Day5 implements Challenge
{
    private static final Pattern INSTRUCTION_REGEX = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");

    @Override
    public void part1()
    {
        Pair<String> input = input();

        Grid grid = grid(Lists.newArrayList(input.first().split("\r\n")));

        List<Move> moves = Arrays.stream(input.second().split("\r\n")).map(Move::from).toList();
        grid.apply(moves);

        log.info("After the rearrangement procedure completes, what crate ends up on top of each stack using the CrateMover 9001? {}", grid.answer());
    }

    @Override
    public void part2()
    {
        Pair<String> input = input();

        Grid grid = grid(Lists.newArrayList(input.first().split("\r\n")));

        List<Move> moves = Arrays.stream(input.second().split("\r\n")).map(Move::from).toList();
        grid.apply(moves, true);

        log.info("After the rearrangement procedure completes, what crate ends up on top of each stack using the CrateMover 9001? {}", grid.answer());
    }

    private Pair<String> input()
    {
        List<String> parts = Input.readString("day05_1.txt", "\r\n\r\n").toList();
        return new Pair<>(parts.get(0), parts.get(1));
    }

    private Grid grid(List<String> structure)
    {
        Collections.reverse(structure);

        Grid grid = new Grid();

        structure.remove(0);
        structure.forEach(line -> {
            for (int i = 1; i < line.length(); i += 4)
            {
                int index = (i - 1) / 4;
                Stack stack = grid.stacks.computeIfAbsent(index, (s) -> new Stack());

                char letter = line.charAt(i);
                if (letter == ' ')
                {
                    continue;
                }

                stack.crates.add(String.valueOf(letter));
            }
        });

        return grid;
    }

    private record Move(int count, int from, int to)
    {
        static Move from(String input)
        {
            Matcher matcher = INSTRUCTION_REGEX.matcher(input);
            matcher.find();
            return new Move(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)) - 1, Integer.parseInt(matcher.group(3)) - 1);
        }
    }

    private static class Stack
    {
        List<String> crates = new ArrayList<>();

        List<String> get(int count, boolean pickupAll)
        {
            if (count >= crates.size())
            {
                count = crates.size();
            }

            List<String> pickedUpCrates = crates.subList(crates.size() - count, crates.size());
            crates = crates.subList(0, crates.size() - count);

            if (pickupAll)
            {
                return pickedUpCrates;
            }

            Collections.reverse(pickedUpCrates);
            return pickedUpCrates;
        }

        public void add(List<String> crates)
        {
            this.crates.addAll(crates);
        }
    }

    private static class Grid
    {
        Map<Integer, Stack> stacks = new HashMap<>();

        public void apply(List<Move> moves)
        {
            apply(moves, false);
        }


        public void apply(List<Move> moves, boolean moveAll)
        {
            moves.forEach(move -> stacks.get(move.to).add(stacks.get(move.from).get(move.count, moveAll)));
        }

        public String answer()
        {
            return stacks.values()
                    .stream()
                    .map(stack -> stack.crates.get(stack.crates.size() - 1))
                    .collect(Collectors.joining());
        }
    }

}
