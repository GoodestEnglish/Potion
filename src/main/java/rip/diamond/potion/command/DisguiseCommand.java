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

@Command("disguise")
@CommandPermission("potion.command.disguise")
@RequiredArgsConstructor
public class DisguiseCommand {

    private final Potion plugin;

    @CommandPlaceholder
    public void root(VelocityCommandActor actor, String name) {
        Player player = actor.requirePlayer();
        GameProfile disguisedProfile = player.getGameProfile().withName(name);

        plugin.database.disguisedProfiles.put(player.getUniqueId(), disguisedProfile);
        player.sendMessage(Component
                .text("You successfully disguised as: ", NamedTextColor.GREEN)
                .append(Component.text(name, NamedTextColor.YELLOW))
                .appendNewline()
                .append(Component.text("Please re-login to be proceed", NamedTextColor.GRAY))
        );
    }

}
