package me.skillissue.launcher

object Shell
{
	fun execute(command: String): Process
	{
		return Runtime.getRuntime().exec(command)
	}

	fun clear()
	{
		if (System.getProperty("os.name").contains("Windows"))
			Runtime.getRuntime().exec("cls")
		else
			print("\u001b[H\u001b[2J")
	}
}