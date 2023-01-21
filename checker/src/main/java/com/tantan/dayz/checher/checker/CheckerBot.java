package com.tantan.dayz.checher.checker;

import com.tantan.dayz.checher.checker.api.Controller;
import com.tantan.dayz.checher.checker.beans.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class CheckerBot extends ListenerAdapter {

    public static void main(String[] args) throws InterruptedException, IOException {
        Controller controller = new Controller();
        controller.test();
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        JDA bot = context.getBean(JDA.class);
        bot.addEventListener(new CheckerBot());
        bot.updateCommands().addCommands(
                Commands.slash("checking", "Search profile using steam id").addOption(OptionType.STRING, "id", "Paste steam id", true)).queue();


    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        switch (event.getName()) {
            case "checking": // 2 stage command with a button prompt
                event.reply("Steam id - " + event.getOption("id").getAsString()).setEphemeral(false).queue();
                break;
            default:
                event.reply("I can't handle that command right now :(").setEphemeral(false).queue();
        }
    }
}
