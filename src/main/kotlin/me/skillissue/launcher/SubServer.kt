package me.skillissue.launcher

import java.nio.channels.Channel

class SubServer(var name: String, var startCommand: String)
{
	@Transient
	var process: SubProcess? = null

	fun start()
	{
		process = SubProcess(Shell.execute(startCommand))
	}

	fun stop()
	{
		process?.stop()
	}

	fun loadConsole()
	{

	}
}