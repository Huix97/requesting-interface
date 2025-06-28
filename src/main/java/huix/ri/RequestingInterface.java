package huix.ri;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(RequestingInterface.MOD_ID)
public class RequestingInterface {

    public static final String MOD_ID = "ri";
    public static final Logger LOGGER = LogUtils.getLogger();

    public RequestingInterface(IEventBus modEventBus) {

    }


}
