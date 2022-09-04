package util

interface IMessage {
    fun execute(invoke: String, args: List<String>, interaction: Discord.Message)
    fun getName(): String
    fun getHelp(): String
}