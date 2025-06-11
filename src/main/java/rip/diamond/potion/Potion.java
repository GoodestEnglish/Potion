package rip.diamond.potion;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import revxrsal.commands.Lamp;
import revxrsal.commands.velocity.VelocityLamp;
import revxrsal.commands.velocity.VelocityVisitors;
import revxrsal.commands.velocity.actor.VelocityCommandActor;
import rip.diamond.potion.command.DisguiseCommand;
import rip.diamond.potion.command.UndisguiseCommand;
import rip.diamond.potion.database.PotionInMemoryDatabase;
import rip.diamond.potion.listener.PotionListener;

import java.util.List;

@Plugin(
        id = "potion",
        name = "Potion",
        version = "1.0.0",
        description = "暱稱插件",
        authors = {"GoodestEnglish"}
)
public class Potion {

    public static Potion INSTANCE;

    public final ProxyServer server;
    public final ComponentLogger logger;
    public Lamp<VelocityCommandActor> lamp;
    public final PotionInMemoryDatabase database = new PotionInMemoryDatabase();

    @Inject
    public Potion(ProxyServer server, ComponentLogger logger) {
        INSTANCE = this;

        this.server = server;
        this.logger = logger;
        this.lamp = VelocityLamp.builder(this, server).build();

        registerCommands();
    }

    @Subscribe
    public void onProxyInitialize(ProxyInitializeEvent event) {
        registerListeners();
    }

    private void registerListeners() {
        List.of(
                new PotionListener(this)
        ).forEach(listener -> server.getEventManager().register(this, listener));

        lamp.accept(VelocityVisitors.brigadier(server));
    }

    private void registerCommands() {
        List.of(
                new DisguiseCommand(this),
                new UndisguiseCommand(this)
        ).forEach(lamp::register);
    }

}
