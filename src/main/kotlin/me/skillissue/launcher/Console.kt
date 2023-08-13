package me.skillissue.launcher

import org.jline.terminal.TerminalBuilder
import org.jline.utils.NonBlockingReader

object Console
{
	private var currentChannel: DefaultConsoleChannel = defaultConsoleChannel
		set(value)
		{
			field = value
			Shell.clear()
		}
	private var open = true
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

	fun changeChannel(channel: DefaultConsoleChannel)
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