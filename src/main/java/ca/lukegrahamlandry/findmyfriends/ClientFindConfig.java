package ca.lukegrahamlandry.findmyfriends;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;

public class ClientFindConfig {
    public static final ForgeConfigSpec client_config;

    public static final ForgeConfigSpec.ConfigValue<String> textContent;
    public static final ForgeConfigSpec.IntValue textColor;

    static {
        final ForgeConfigSpec.Builder clientBuilder = new ForgeConfigSpec.Builder();

        clientBuilder.comment("Find My Friends client side settings")
                .push("client");

        textContent = clientBuilder
                .comment("Text displayed after the distance")
                .define("textContent", " blocks away");

        textColor = clientBuilder
                .comment("Text color (decimal)")
                .defineInRange("textColor", 257276, 0, Integer.MAX_VALUE);

        client_config = clientBuilder.build();
    }

    public static void init(){
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, client_config);
        CommentedFileConfig file = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().resolve(ModMain.MOD_ID + ".toml").toString())).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        client_config.setConfig(file);
    }

    public static double getFakeNameDisplayDistance() {
        return 8;
    }
}