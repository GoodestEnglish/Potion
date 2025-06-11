package rip.diamond.potion.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.PlayerChooseInitialServerEvent;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.util.GameProfile;
import lombok.RequiredArgsConstructor;
import rip.diamond.potion.Potion;
import rip.diamond.potion.database.PotionInMemoryDatabase;
import rip.diamond.potion.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.UUID;

@RequiredArgsConstructor
public class PotionListener {

    private final Potion plugin;

    @Subscribe
    public void onPlayerJoin(PlayerChooseInitialServerEvent event) throws Exception {
        Player player = event.getPlayer();
        UUID uniqueId = player.getUniqueId();
        PotionInMemoryDatabase database = plugin.database;

        database.originalProfiles.put(uniqueId, player.getGameProfile());

        GameProfile disguisedProfile = database.disguisedProfiles.get(uniqueId);

        if (disguisedProfile != null) {
            Field field = ReflectionUtil.getCachedField(player.getClass(), "profile");
            field.set(player, disguisedProfile);
        }
    }

}
