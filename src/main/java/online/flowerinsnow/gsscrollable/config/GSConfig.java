package online.flowerinsnow.gsscrollable.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.gsscrollable.C;

import java.io.File;

@SideOnly(Side.CLIENT)
public class GSConfig {
    public int sensitivity;
    public boolean disableInCreative;
    private final File file;
    private Configuration configuration;

    public GSConfig(File file) {
        this.file = file;

        reload();
    }

    public File getFile() {
        return file;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void reload() {
        this.configuration = new Configuration(this.file);
        this.sensitivity = configuration.getInt(C.configSensitivity, C.configConfig, 5, 1, 100, "滚动灵敏度");
        this.disableInCreative = configuration.getBoolean(C.configDisableInCreative, C.configConfig, true, "在创造模式禁用");
    }

    public void save() {
        this.configuration.save();
    }
}
