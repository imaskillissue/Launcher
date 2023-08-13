package me.skillissue.launcher

class DefaultConsoleChannel(private val owner: String): IConsoleChannel
{

	private var messages = mutableListOf<String>()
	private var input = ""

	override fun displayMessages()
	{
		Shell.clear()
		printLogo()
		kotlin.io.println("${ConsoleColoring.CYAN}Entered console channel of ${ConsoleColoring.GREEN_BOLD}'$owner'" +
				".\n\n${ConsoleColoring.RESET}")
		for (message in messages)
		{
			kotlin.io.println("${ConsoleColoring.GREEN_BOLD}$owner " +
					"${ConsoleColoring.CYAN}> " +
					"${ConsoleColoring.YELLOW}$message${ConsoleColoring.RESET}")
		}
	}

	override fun handleInput(key: Int)
	{
		if (key == 13) // Enter
		{
			messages.add(input)
			input = ""
		}
		else if (key == 127) // Backspace
		{
			if (input.isNotEmpty())
				input = input.substring(0, input.length - 1)
		}
		else
		{
			input += key.toChar()
		}
	}

	fun println(message: String)
	{
		messages.add(message)
	}

	fun println(message: Any)
	{
		messages.add(message.toString())
	}
}