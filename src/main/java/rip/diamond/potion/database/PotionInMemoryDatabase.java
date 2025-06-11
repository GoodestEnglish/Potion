package rip.diamond.potion.database;

import com.velocitypowered.api.util.GameProfile;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PotionInMemoryDatabase {

    public final Map<UUID, GameProfile> originalProfiles = new ConcurrentHashMap<>();
    public final Map<UUID, GameProfile> disguisedProfiles = new ConcurrentHashMap<>();

}
