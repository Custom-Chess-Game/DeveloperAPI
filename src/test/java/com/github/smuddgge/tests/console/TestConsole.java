package com.github.smuddgge.tests.console;

import com.github.smuddgge.console.Console;
import com.github.smuddgge.console.ConsoleColour;
import com.github.smuddgge.results.ResultChecker;
import org.junit.jupiter.api.Test;

public class TestConsole {

    @Test
    public void testPrint() {
        Console.print(ConsoleColour.GREEN + "Test");
    }
}
