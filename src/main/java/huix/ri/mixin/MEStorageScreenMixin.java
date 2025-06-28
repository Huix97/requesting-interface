package huix.ri.mixin;

import appeng.api.stacks.AEItemKey;
import appeng.client.gui.AEBaseScreen;
import appeng.client.gui.me.common.MEStorageScreen;
import appeng.client.gui.me.common.Repo;
import appeng.client.gui.style.ScreenStyle;
import appeng.helpers.InventoryAction;
import appeng.menu.AEBaseMenu;
import appeng.menu.me.common.GridInventoryEntry;
import appeng.menu.me.common.MEStorageMenu;
import huix.ri.plugin.JEI;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MEStorageScreen.class)
public class MEStorageScreenMixin<C extends MEStorageMenu> extends AEBaseScreen<C> {

    @Final
    @Shadow
    protected Repo repo;

    public MEStorageScreenMixin(C menu, Inventory playerInventory, Component title, ScreenStyle style) {
        super(menu, playerInventory, title, style);
    }


    @Inject(at = @At(value = "HEAD"), method = "mouseClicked", cancellable = true)
    private void injectJEICompact(double xCoord, double yCoord, int btn, CallbackInfoReturnable<Boolean> cir) {
        if (!Minecraft.getInstance().options.keyPickItem.matchesMouse(btn)) return;

        ItemStack itemStack = JEI.instance().getBookmarkOverlay().getItemStackUnderMouse();
        if (itemStack == null) return;

        repo.getAllEntries().stream()
                .filter(GridInventoryEntry::isCraftable)
                .filter(entry -> entry.getWhat().equals(AEItemKey.of(itemStack)))
                .forEach(entry -> {
                    long serial = entry.getSerial();
                    menu.handleInteraction(serial, InventoryAction.AUTO_CRAFT);
                });
    }

}
