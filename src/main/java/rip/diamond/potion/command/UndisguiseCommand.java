package rip.diamond.potion.command;

import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.util.GameProfile;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.CommandPlaceholder;
import revxrsal.commands.velocity.actor.VelocityCommandActor;
import revxrsal.commands.velocity.annotation.CommandPermission;
import rip.diamond.potion.Potion;

@Command("undisguise")
@CommandPermission("potion.command.undisguise")
@RequiredArgsConstructor
public class UndisguiseCommand {

    private final Potion plugin;

    @CommandPlaceholder
    public void root(VelocityCommandActor actor, String name) {
        Player player = actor.requirePlayer();
        GameProfile disguisedProfile = player.getGameProfile().withName(name);

        plugin.database.disguisedProfiles.remove(player.getUniqueId());
        player.sendMessage(Component
                .text("You have successfully undisguised!", NamedTextColor.GREEN)
                .appendNewline()
                .append(Component.text("Please re-login to be proceed", NamedTextColor.GRAY))
        );
    }

}
