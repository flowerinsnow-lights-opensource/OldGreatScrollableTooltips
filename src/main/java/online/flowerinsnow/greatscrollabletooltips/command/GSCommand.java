package online.flowerinsnow.greatscrollabletooltips.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableToolTips;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("NullableProblems")
@SideOnly(Side.CLIENT)
public class GSCommand extends CommandBase {
    @Override
    public String getName() {
        return "greatscrollabletooltips";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "gscommand.usage";
    }

    @Override
    public List<String> getAliases() {
        return new ArrayList<>(Collections.singletonList("gs"));
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1 && "reload".equalsIgnoreCase(args[0])) {
            GreatScrollableToolTips.getConfig().reload();
            sender.sendMessage(new TextComponentTranslation("reload.success"));
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("sensitivity")) {
                try {
                    int value = Integer.parseInt(args[1]);
                    if (value < 1 || value > 100) {
                        throw new NumberFormatException();
                    }
                    GreatScrollableToolTips.getConfig().sensitivity = value;
                    GreatScrollableToolTips.getConfig().save();
                    sender.sendMessage(new TextComponentTranslation("sensitivity.change", value));
                } catch (NumberFormatException ex) {
                    throw new WrongUsageException("gscommand.sensitivity.onlyint");
                }
            } else if (args[0].equalsIgnoreCase("disableincreative")) {
                if ("true".equalsIgnoreCase(args[1])) {
                    GreatScrollableToolTips.getConfig().disableInCreative = true;
                    GreatScrollableToolTips.getConfig().save();
                    sender.sendMessage(new TextComponentTranslation("disableincreative.change.enabled"));
                } else if ("false".equalsIgnoreCase(args[1])) {
                    GreatScrollableToolTips.getConfig().disableInCreative = false;
                    GreatScrollableToolTips.getConfig().save();
                    sender.sendMessage(new TextComponentTranslation("disableincreative.change.disabled"));
                } else {
                    throw new WrongUsageException("gscommand.disableincreative.onlyboolean");
                }
            } else {
                throw new WrongUsageException("gscommand.usage");
            }
        } else {
            throw new WrongUsageException("gscommand.usage");
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos) {
        if (args.length == 1) {
            List<String> list = new ArrayList<>(Arrays.asList("sensitivity", "disableincreative", "reload"));
            list.removeIf(s -> !s.toLowerCase().startsWith(args[0].toLowerCase()));
            return list;
        } else if (args.length == 2) {
            if ("disableincreative".equalsIgnoreCase(args[0])) {
                List<String> list = new ArrayList<>(Arrays.asList("true", "false"));
                list.removeIf(s -> !s.toLowerCase().startsWith(args[0].toLowerCase()));
                return list;
            }
        }
        return Collections.emptyList();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
