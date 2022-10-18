package online.flowerinsnow.gsscrollable.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
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

    private Property entrySensitivity;
    private Property entryDisableInCreative;

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

        this.entrySensitivity = this.configuration.get(C.configSensitivity, C.configConfig, 10, "滚动灵敏度");
        this.sensitivity = this.entrySensitivity.getInt();

        this.entryDisableInCreative = configuration.get(C.configDisableInCreative, C.configConfig, true, "在创造模式禁用");
        this.disableInCreative = this.entryDisableInCreative.getBoolean();
    }

    public void save() {
        this.entrySensitivity.set(this.sensitivity);
        this.entryDisableInCreative.set(this.disableInCreative);
        this.configuration.save();
    }
}
