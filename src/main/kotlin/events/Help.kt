package events

import util.Discord
import util.IMessage
import util.commandsObj
import util.prefix

class Help : IMessage {
    override fun execute(invoke: String, args: List<String>, interaction: Discord.Message) {
        val embed = Discord.RichEmbed()

        commandsObj.forEach {
            embed.addField(
                "$prefix${it.key}",
                "${it.value.getHelp()}".trimIndent(), true
            )
        }

        interaction.channel.send(embed)
    }

    override fun getName() = "help"
    override fun getHelp() = "Shows a list of the commands"
}