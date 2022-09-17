package com.github.smuddgge.tests.console;

import com.github.smuddgge.console.Console;
import com.github.smuddgge.console.ConsoleColour;
import org.junit.jupiter.api.Test;

public class TestConsole {

    @Test
    public void testPrint() {
        Console.log(ConsoleColour.GREEN + "Test");
    }
}
