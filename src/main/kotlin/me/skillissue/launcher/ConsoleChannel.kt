package me.skillissue.launcher

class ConsoleChannel(private val owner: String): IConsoleChannel
{

	private var messages = mutableListOf<String>()

	fun displayMessages()
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

	fun println(message: String)
	{
		messages.add(message)
	}

	fun println(message: Any)
	{
		messages.add(message.toString())
	}
}