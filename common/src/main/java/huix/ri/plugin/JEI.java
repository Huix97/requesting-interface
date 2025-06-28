package huix.ri.plugin;

import huix.ri.RequestingInterface;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

@JeiPlugin
public class JEI implements IModPlugin {

    private static IJeiRuntime jeiInstance;

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath("", "jei");
    }

    @Override
    public void onRuntimeAvailable(@NotNull IJeiRuntime runtime) {
        jeiInstance = runtime;
    }

    @Override
    public void onRuntimeUnavailable() {
        jeiInstance = null;
    }

    public static IJeiRuntime instance() {
        return jeiInstance;
    }
}
