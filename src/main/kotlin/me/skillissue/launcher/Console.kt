package me.skillissue.launcher

import org.jline.terminal.TerminalBuilder
import org.jline.utils.NonBlockingReader

object Console
{
	private var currentChannel: ConsoleChannel = consoleChannel
		set(value)
		{
			field = value
			Shell.clear()
		}
	private var open = true
	private var input = ""
	private val terminal = TerminalBuilder.builder().build()
	private val reader: NonBlockingReader

	init
	{
		terminal.enterRawMode()
		reader = terminal.reader()
	}

	fun startRender()
	{
		while (open)
		{
			currentChannel.displayMessages()

		}
	}

	fun changeChannel(channel: ConsoleChannel)
	{
		currentChannel = channel
	}

	fun close()
	{
		open = false
		terminal.close()
		reader.close()
	}
}