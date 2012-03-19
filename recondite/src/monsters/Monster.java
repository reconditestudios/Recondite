package monsters;

public interface Monster {
	public static int currentHealth = 0;
	public static int currentAC = 0;

	public void attack();

	public void getHurt(int damage);

	public Boolean isDead();
}
