package com.evan.slammod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeHooks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class SlamHandler {

	public double [] v = {0,0,0,0,0};
	public double dx = 0;
	public double dz = 0;
	public int hitCounter = 0;

	@SubscribeEvent
	public void TickPlayer(TickEvent.PlayerTickEvent event) {


		EntityPlayer player = event.player;
		double max = v[0];
		for (int i = 0; i < v.length; i++) {
			if (v[i] > max) {
				max = v[i];
			}
		}
		if (player.isSprinting()){



			if (hitCounter==0){
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1,5));
			}
			else if (hitCounter>0){
				hitCounter--;
			}
			if (player.isCollidedHorizontally && max > 0.0671 & hitCounter == 0){
				hitCounter = 100;
				player.clearActivePotions();
				player.performHurtAnimation();
				player.playSound("game.neutral.hurt.fall.big", 1.0F, 1.0F);
				float health = player.getHealth();
				float damage = ForgeHooks.onLivingHurt(player, DamageSource.generic, 5);
				player.setHealth(health - damage);
				player.func_110142_aN().func_94547_a(DamageSource.generic, health, damage);
				player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id,100,5));
				player.addPotionEffect(new PotionEffect(Potion.confusion.id,100,5));
			}



		}else{
			player.clearActivePotions();
		}
		dx = player.motionX;
		dz = player.motionZ;
		v[4] = v[3];
		v[3] = v[2];
		v[2] = v[1];
		v[1] = v[0];
		v[0] = dx*dx+dz*dz;
	}
}
