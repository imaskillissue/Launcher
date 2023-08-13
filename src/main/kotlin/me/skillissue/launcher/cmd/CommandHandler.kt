package me.skillissue.launcher.cmd

import me.skillissue.launcher.defaultConsoleChannel
import me.skillissue.launcher.startServer
import kotlin.system.exitProcess

object CommandHandler
{

	val commands = mutableListOf(
		Command("start", "Starts a server.", "start <server>"),
		Command("stop", "Stops a server.", "stop <server>"),
		Command("restart", "Restarts a server.", "restart <server>"),
		Command("list", "Lists all servers.", "list"),
		Command("help", "Shows this help page.", "help"),
		Command("exit", "Exits the launcher.", "exit"),
	)

	init
	{
		commands.find { it.name == "start" }?.serverCompletion(0)
		commands.find { it.name == "stop" }?.serverCompletion(0)
		commands.find { it.name == "restart" }?.serverCompletion(0)

	}

	fun execute(command: String, args: Array<String>)
	{
		when (command)
		{
			"start" -> start(args)
			//"stop" -> stop(args)
			//"restart" -> restart(args)
			//"list" -> list(args)
			"help" -> help(args)
			"exit" -> exit(args)
			else -> defaultConsoleChannel.println("Unknown command: $command")
		}
	}

	fun complete(text: String): Array<String>
	{
		val params = text.split(" ")
		if (params.isEmpty())
			return arrayOf(text)
		if (params.size == 1)
			return commands.filter { it.name.startsWith(params[0]) }.map { it.name }.toTypedArray()
		val command = commands.find { it.name == params[0] } ?: return arrayOf(text)
		return command.complete(params.size - 1, params.last())
	}

	private fun start(args: Array<String>)
	{
		if (args.size != 1)
		{
			defaultConsoleChannel.println("Usage: start <server>")
			return
		}
		val server = args[0]
		defaultConsoleChannel.println("Starting server '$server'...")
		startServer(server)
	}

	private fun exit(args: Array<String>)
	{
		defaultConsoleChannel.println("Exiting...")
		exitProcess(0)
	}

	private fun help(args: Array<String>)
	{
		defaultConsoleChannel.println("Valos Launcher v0.1.0\n" +
				"Type 'help' for a list of commands.\n" +
				"Type 'start <server>' to start all sub servers.\n" +
				"Type 'load <server>' to load sub servers console.\n" +
				"Type 'exec <server> <command>' to execute a command in a sub server.\n" +
				"Type 'exit' to exit the launcher.")
	}
}