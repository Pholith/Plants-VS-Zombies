package zombies;


import java.util.*;

import base.Vector2;

public class ScreenDoorZombie extends ProtectedZombie {

	public ScreenDoorZombie(Vector2 position) {
		super(100, position, "zombies/screendoor.png", 5f, 1f, 300);
		
		}
	@Override
    public String name() {return "ScreenDoorZombie";}

	@Override
	public boolean haveScreenDoor() {
		return true;
	}
}