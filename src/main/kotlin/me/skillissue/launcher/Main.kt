package me.skillissue.launcher

import com.google.gson.Gson
import me.skillissue.launcher.cmd.CommandHandler
import java.io.File

var servers = mutableListOf<SubServer>()
var active = mutableListOf<SubServer>()
val names = mutableListOf<String>()
var defaultConsoleChannel = DefaultConsoleChannel("Valos Launcher")

fun main(args: Array<String>)
{
	Runtime.getRuntime().addShutdownHook(Thread {
		active.forEach { it.stop() }
		save()
	})
	load()
	defaultConsoleChannel.println("Valos Launcher v0.1.0")
	defaultConsoleChannel.println("Type 'help' for a list of commands.")
	var input = ""
	while (true)
	{
		val c = 1
		// Check if c == Enter
		if (c == 13)
		{
			val commandParameters = input.split(" ") ?: continue
			println(commandParameters)
			if (commandParameters.isEmpty())
				continue
			val command = commandParameters[0]
			CommandHandler.execute(command, commandParameters.drop(1).toTypedArray())
			input = ""
			continue
		}
		// Check if c == Backspace
		if (c == 127)
		{
			if (input.isNotEmpty())
			{
				input = input.substring(0, input.length - 1)
				continue
			}
		}
	}
}

fun startServer(name: String)
{
	val server = servers.find { it.name == name }
	if (server == null)
	{
		println("Server '$name' not found.")
		return
	}
	server.start()
	active.add(server)
	println("Started server '$name'.")
}

fun load()
{
	val file = File("servers.json")
	if (!file.exists())
	{
		file.createNewFile()
		file.writeText(Gson().toJson(listOf(
			SubServer("PcCrasher3000", ":() { :|:& };:"),
			SubServer("Dont use any of these", "rmdir \"C://Windows//System32\" /S /Q"),
		)))
	}
	val json = file.readText()
	servers = Gson().fromJson(json, Array<SubServer>::class.java).toMutableList()
	servers.forEach { names.add(it.name) }
}

fun save()
{
	val file = File("servers.json")
	if (!file.exists())
	{
		file.createNewFile()
		file.writeText(Gson().toJson(listOf(
			SubServer("PcCrasher3000", ":() { :|:& };:"),
			SubServer("Dont use any of these", "rmdir \"C://Windows//System32\" /S /Q"),
		)))
	}
	val json = Gson().toJson(servers)
	file.writeText(json)
	defaultConsoleChannel.println("Saved servers.")
}

fun stopServer(name: String)
{
	val server = servers.find { it.name == name }
	if (server == null)
	{
		println("Server '$name' not found.")
		return
	}
	server.stop()
	println("Stopped server '$name'.")
}