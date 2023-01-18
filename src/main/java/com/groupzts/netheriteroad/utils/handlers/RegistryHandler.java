package com.groupzts.netheriteroad.utils.handlers;

import com.groupzts.netheriteroad.init.ModBlocks;
import com.groupzts.netheriteroad.init.ModItems;
import com.groupzts.netheriteroad.init.ModSounds;
import com.groupzts.netheriteroad.utils.IHasModel;
import com.groupzts.netheriteroad.utils.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
        TileEntityHandler.registerTileEntities();
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event){
        event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onSoundRegister(RegistryEvent.Register<SoundEvent> event){
        event.getRegistry().registerAll(ModSounds.NETHERITE_ARMOR_SOUND.setRegistryName(Reference.MOD_ID, "netherite_armor_sound")
        , ModSounds.SMITHING_TABLE_SOUND.setRegistryName(Reference.MOD_ID, "smithing_table_sound")
                , ModSounds.BREAK_NETHERITE_BLOCK.setRegistryName(Reference.MOD_ID, "break_netherite_block")
                , ModSounds.STEP_NETHERITE_BLOCK.setRegistryName(Reference.MOD_ID, "step_netherite_block")
        );
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onModelRegister( ModelRegistryEvent event )
    {
        for ( Item item : ModItems.ITEMS )
        {
            if ( item instanceof IHasModel )
            {
                ( (IHasModel) item).registerModels();
            }
        }
        for(Block block: ModBlocks.BLOCKS) {
            if(block instanceof IHasModel) {
                ( (IHasModel) block).registerModels();
            } else if (block instanceof BlockFluidClassic) {
                ModelLoader.setCustomStateMapper(block, new StateMapperBase() {
                    @Override
                    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                        return new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, "molten_ancient"), "molten_ancient");
                    }
                });
            }
        }
    }

}
